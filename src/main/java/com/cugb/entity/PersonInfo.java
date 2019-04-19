package com.cugb.entity;

import java.util.Date;

//用户信息表
public class PersonInfo {
	private Long userId;
	private String name;
	//用户头像
	private String profileImg;
	private String email;
	//性别
	private String gender;
	//状态
	private Integer enableStatus;
	//身份 1表示普通用户，2表示店家，3 表示超级管理员
	private Integer userType;
	private Date createTime;
	private Date updateTime;
	public Date getCreateTime() {
		return createTime;
	}
	public String getEmail() {
		return email;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public String getGender() {
		return gender;
	}
	public String getName() {
		return name;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public Long getUserId() {
		return userId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}
