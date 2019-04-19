package com.cugb.enumration;

public enum ProductCategoryStateEnum {

	SUCCESS(200,"操作成功"),
	FAIL(404,"操作失败"),
	INNER_ERROR(-1001,"内部出错"),
	EMPTY_LIST(-1002,"添加数少于1");
	
	private int state;
	private String stateInfo;
	
	ProductCategoryStateEnum(int state, String stateInfo ) {
		this.state = state;
		this.stateInfo  = stateInfo;
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
	
	
}
