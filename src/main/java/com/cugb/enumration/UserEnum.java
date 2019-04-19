package com.cugb.enumration;


public enum UserEnum {

	SUCCESS(200,"登录成功"),
	FAIL(400,"登录失败"),
	REGISTER(200,"注册成功"),
	NOT_EXIST(-1001,"用户名不存在"),
	ERROR_PASSWORD(-1002,"密码错误"),
	INNER_ERROR(500,"内部异常"),
	UNAUTHORIZED(-1003,"未授权");
	private int state;
	private String stateInfo;
	private UserEnum(int state,String stateInfo) {
		this.state  = state;
		this.stateInfo = stateInfo;
	}
	public int getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	
}
