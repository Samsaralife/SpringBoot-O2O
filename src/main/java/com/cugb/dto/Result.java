package com.cugb.dto;
/**
 * 封装json对象，所有返回结果都使用它
 * @author wangyingbo
 *
 * @param <T>
 */
public class Result<T> {

	private boolean success; //是否成功标记
	private T data;  //成功时返回的数据
	private String errorMsg; // 错误信息
	private int errorCode; //错误码
	//默认
	public Result() {
		
	}
	//成功时的构造器
	public Result(boolean success,T data) {
		this.success = success;
		this.data =data;
	}
	//错误时的构造器
	public Result(boolean success,int errorCode,String errorMsg)
	{
		this.success = success;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
