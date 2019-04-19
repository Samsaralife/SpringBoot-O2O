package com.cugb.controller.shopadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cugb.dto.UserExecution;
import com.cugb.entity.User;
import com.cugb.enumration.UserEnum;
import com.cugb.service.UserService;
import com.cugb.utils.CodeUtil;
import com.cugb.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/shopadmin")
public class UserController {

	@Autowired
	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@PostMapping("/login")
	public Map<String, Object> login(HttpServletRequest request)
	{
		
		Map<String, Object> map = new HashMap<>();
		String userStr = HttpServletRequestUtil.getString(request, "userStr");
		User user =null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			user = mapper.readValue(userStr, User.class);
		}catch (Exception e) {
			// TODO: handle exception
			map.put("success", false);
			map.put("errMsg", e.getMessage());
		}
		
		if(user !=null)
		{
			logger.info("=======开始注册用户=======");
			UserExecution<User> userExecution = userService.loginByUsernameAndPwd(user.getUsername(), user.getPassword());
			if(userExecution.getState() == UserEnum.SUCCESS.getState())
			{
				map.put("success", true);
				logger.info("=========用户注册成功=========");
			}else {
				map.put("success", false);
				map.put("errMsg", userExecution.getStateInfo());
				logger.info("=========用户注册失败=========");
			}
		}else {
			map.put("success", false);
			map.put("errMsg", "用户为空");
		}
		return map;
	}
	@PostMapping("/registeruser")
	public Map<String, Object> registerUser(@RequestBody(required=false) User user)
	{
		Map<String, Object> map = new HashMap<>();
		if(user.getUsername()==null&&user.getPassword()==null)
		{
			map.put("success", false);
			map.put("errMsg", "用户为空");
			return map;
		}else
		{
			try {
				UserExecution<User> userExecution= userService.registerUser(user);
				if(userExecution.getState()==UserEnum.REGISTER.getState())
				{
					map.put("success", true);
				}
			}catch (Exception e) {
				// TODO: handle exception
				map.put("success", false);
				map.put("errMsg", "注册失败");
				return map;
			}
		}
		return map;
	}
	
	@GetMapping("/getuserbyid")
	public Map<String, Object> getUserById(HttpServletRequest request)
	{
		Integer id = HttpServletRequestUtil.getInt(request, "id");
		Map<String, Object> map = new HashMap<>();
		if(id!=null && id>0)
		{
			try {
				User user = userService.findUserById(id);
				map.put("success", true);
				map.put("user", user);
				return map;
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
		}else {
			map.put("success", false);
			map.put("errMsg", "用户id不能小于0");
			return map;
		}
	}
	
	@PostMapping("/updateuser")
	public Map<String, Object> updateUser(HttpServletRequest request)
	{
		Map<String, Object> map  = new HashMap<>();
		String userStr = HttpServletRequestUtil.getString(request, "userStr");
		ObjectMapper objectMapper = new ObjectMapper();
		User user = null;
		if(userStr !=null)
		{
			try {
				 user = objectMapper.readValue(userStr, User.class);
			}catch(Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
		}
		if(user != null)
		{
			try {
				UserExecution< User> userExecution = userService.updateUser(user);
				if(userExecution.getState() == UserEnum.SUCCESS.getState())
				{
					map.put("success", true);
					map.put("user", user);
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
		}else
		{
			map.put("success", false);
			map.put("errMsg", "更新失败");
			
		}
		return map;
	}
}
