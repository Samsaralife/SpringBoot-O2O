package com.cugb.entity;
//产品

import java.util.Date;

public class ProductCategory {
	private Long productCategoryId;
	private Long shopId;
	private String productCategoryName;
	private Integer priority;
	private Date createTime;
	public Date getCreateTime() {
		return createTime;
	}
	public Integer getPriority() {
		return priority;
	}
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
}
