package com.cugb.entity;
//微信账号

import java.util.Date;

public class WechatAuth {

	private Long wechatAuthId;
	private String openId;
	private Date createTime;
	private PersonInfo personInfo;
	public Date getCreateTime() {
		return createTime;
	}
	public String getOpenId() {
		return openId;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public Long getWechatAuthId() {
		return wechatAuthId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	public void setWechatAuthId(Long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}
}
