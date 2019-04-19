package com.cugb.entity;
//本地账号

import java.util.Date;

public class LocalAuth {

	private Long localAuthId;
	private String username;
	private String password;
	private Date createTime;
	private Date updateTime;
	private PersonInfo personInfo;
	public Date getCreateTime() {
		return createTime;
	}
	public Long getLocalAuthId() {
		return localAuthId;
	}
	public String getPassword() {
		return password;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public String getUsername() {
		return username;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setLocalAuthId(Long localAuthId) {
		this.localAuthId = localAuthId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
