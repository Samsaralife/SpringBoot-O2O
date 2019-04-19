package com.cugb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cugb.entity.User;

public interface UserMapper {

	User loginByUserNameAndPwd(@Param("username")String username,@Param("password")String password);
	
	List<User> findUserByName(@Param("username")String username);
	
	int InsertUser(User user);
	
	User findUserById(Integer id);
	
	int updateUser(User user);
	
	int deleteUserByid(Integer id);
}
