package com.cugb.enumration;

public enum ProductEnum {

	SUCCESS("200","成功"),
	FAIL("404","失败"),
	INNER_ERROR("504","机器内部错误"),
	EMPTY("-1002","空值异常"),
	UNAUTHRIZATION("-1001","未认证");
	
	private String state;
	private String stateInfo;
	
	private ProductEnum(String state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public String getState() {
		return state;
	}


	public String getStateInfo() {
		return stateInfo;
	}

}
