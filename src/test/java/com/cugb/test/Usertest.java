package com.cugb.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cugb.application.Application;
import com.cugb.dao.UserMapper;
import com.cugb.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@MapperScan(basePackages= {"com.cugb.dao"})
public class Usertest {

	@Autowired
	private UserMapper userMapper;
	
	private static final Integer Role =1;
	private static final Logger logger = LoggerFactory.getLogger(Usertest.class);
	@Test
	@Ignore
	public void testLogin() {
		User user = userMapper.loginByUserNameAndPwd("王应博", "123456");
		logger.info("username===="+user.getUsername());
	}
	
	@Test
	public void testInserUser()
	{
		User user = new User();
		user.setId(2);
		user.setUsername("tom");
		user.setPassword("123453");
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setQuestion("Hello World");
		user.setRole(1);
		//int effectNum = userMapper.InsertUser(user);
		int effectNum = userMapper.InsertUser(user);
		assertEquals(1, effectNum);
	}
	
	@Test
	@Ignore
	public void testGetUser()
	{
		User user = userMapper.findUserById(1);
		user.setId(1);
		user.setRole(1);
		int effectNum = userMapper.updateUser(user);
		assertEquals(1, effectNum);
	}
	
	@Test
	public void testDeleteUser()
	{
		int effectNum = userMapper.deleteUserByid(4);
		assertEquals(1, effectNum);
	}
}
