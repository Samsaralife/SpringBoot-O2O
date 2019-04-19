package com.cugb.dto;


import com.cugb.enumration.UserEnum;

public class UserExecution<T> {

	private int state;
	private String stateInfo;
	private T data;
	
	public UserExecution() {
		
	}
	//失败时的构造函数
	public UserExecution(UserEnum userEnum)
	{
		this.state = userEnum.getState();
		this.stateInfo =userEnum.getStateInfo();
	}
	//成功时的构造函数
	public UserExecution(UserEnum userEnum,T data)
	{
		this.state = userEnum.getState();
		this.stateInfo = userEnum.getStateInfo();
		this.data = data;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
