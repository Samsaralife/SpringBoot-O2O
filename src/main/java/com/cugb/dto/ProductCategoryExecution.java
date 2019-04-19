package com.cugb.dto;

import java.util.List;

import com.cugb.entity.ProductCategory;
import com.cugb.enumration.ProductCategoryStateEnum;

public class ProductCategoryExecution {
	//状态标识
	private int state;
	//状态信息
	private String stateInfo;
	private List<ProductCategory> productCategoryList;
	
	public ProductCategoryExecution() {
		
	}
	//操作失败的时候使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum) {
		this.state = productCategoryStateEnum.getState();
		this.stateInfo = productCategoryStateEnum.getStateInfo();
	}
	
	//操作成功的时候使用的构造器
	
	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum,List<ProductCategory> productCategoryList) {
		this.state = productCategoryStateEnum.getState();
		this.stateInfo = productCategoryStateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
	}
	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}
	public int getState() {
		return state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
}
