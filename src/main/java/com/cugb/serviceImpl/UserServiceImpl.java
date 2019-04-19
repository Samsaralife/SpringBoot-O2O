package com.cugb.serviceImpl;

import java.lang.ProcessBuilder.Redirect;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cugb.dao.UserMapper;
import com.cugb.dto.UserExecution;
import com.cugb.entity.User;
import com.cugb.enumration.UserEnum;
import com.cugb.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisTemplate redisTemplate;
	@Override
	public UserExecution<User> loginByUsernameAndPwd(String username, String password) {
		// TODO Auto-generated method stub
		if(username == null || "".equals(username))
		{
			return new UserExecution<User>(UserEnum.INNER_ERROR);
		}else {
			List<User> list = userMapper.findUserByName(username);
			if(list.size()<0)
			{
				return new UserExecution<User>(UserEnum.NOT_EXIST);
			}else {
				User user  = userMapper.loginByUserNameAndPwd(username, password);
				if(user == null)
				{
					return new UserExecution<User>(UserEnum.ERROR_PASSWORD);
				}else {
					return new UserExecution<User>(UserEnum.SUCCESS);
				}
			}
		}
	}
	@Override
	public UserExecution<User> registerUser(User user) {
		// TODO Auto-generated method stub
		if (user != null) {
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			user.setRole(1);
			int effectNum = userMapper.InsertUser(user);
			if(effectNum<0) {
				return new UserExecution<User>(UserEnum.FAIL);
			}
			return new UserExecution<>(UserEnum.REGISTER);
		}
		return new UserExecution<>(UserEnum.FAIL);
	}
	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		
		String key = "user_"+id;
		ValueOperations<String, User> operations = redisTemplate.opsForValue();
		boolean hashkey = redisTemplate.hasKey(key);
		//缓存存在
		if(hashkey)
		{
			User user = operations.get(key);
			System.out.println("=========从缓存中获取========");
			System.out.println(user.getUsername());
			System.out.println("===========================");
			return user;
		}else {
			User user = userMapper.findUserById(id);
			System.out.println("=========从数据库中获取=======");
			System.out.println(user.getUsername());
			System.out.println("============================");
			operations.set(key, user, 5, TimeUnit.MINUTES);
			return user;
		}
		
	}
	@Override
	/**
	 * 更新用户策略 ===>> 先更新数据表，再更新缓存
	 */
	public UserExecution<User> updateUser(User user) {
		// TODO Auto-generated method stub
		if(user !=null && user.getId()!=null)
		{
			try
			{
				//	更新成功
				int effectNum = userMapper.updateUser(user);
				if(effectNum>0)
				{
					
					String key = "user_" + user.getId();
					boolean hashKey = redisTemplate.hasKey(key);
					//缓存存在
					if(hashKey)
					{
						redisTemplate.delete(key);
						System.out.println("=======删除缓存=======");
					}
					return new UserExecution<>(UserEnum.SUCCESS);
				}
				
				return new UserExecution<>(UserEnum.FAIL);
			}catch(Exception e) {
				e.printStackTrace();
				return new UserExecution<>(UserEnum.FAIL);
			}
		}
		return new UserExecution<>(UserEnum.FAIL);
	}
	@Override
	/**
	 * 删除用户策略 ========>先从数据库中删除，然后删除缓存
	 */
	public UserExecution<User> deleteUser(Integer id) {
		// TODO Auto-generated method stub
		if(id==null || id <0)
		{
			return new UserExecution<>(UserEnum.FAIL);
		}else {
			int effectNum = userMapper.deleteUserByid(id);
			//操作成功
			if(effectNum > 0) {
				String key = "user_"+id;
				boolean hashkey = redisTemplate.hasKey(key);
				//如果存在缓存
				if(hashkey)
				{
					redisTemplate.delete(key);
				}
				return new UserExecution<User>(UserEnum.SUCCESS);
			}else
			{
				return new UserExecution<>(UserEnum.FAIL);
			}
		}
	}

}
