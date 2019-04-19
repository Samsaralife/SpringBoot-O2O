package com.cugb.service;

import com.cugb.dto.UserExecution;
import com.cugb.entity.User;

public interface UserService {

	UserExecution<User> loginByUsernameAndPwd(String username,String password);
	UserExecution<User> registerUser(User user);
	User findUserById(Integer id);
	UserExecution<User> updateUser(User user);
	UserExecution<User> deleteUser(Integer id);
}
