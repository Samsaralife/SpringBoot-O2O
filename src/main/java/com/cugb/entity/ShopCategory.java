package com.cugb.entity;

import java.util.Date;

//店铺类别
public class ShopCategory {

	private Long shopCategoryId;
	private String shopCategoryName;
	//店铺描述
	private String shopCategoryDesc;
	//店铺图片
	private String shopCategoryImg;
	//店铺权重
	private Integer priority;
	//创建时间
	private Date createTime;
	private Date updateTime;
	//上级ID
	private ShopCategory parent;
	public Date getCreateTime() {
		return createTime;
	}
	public Integer getPriority() {
		return priority;
	}
	public ShopCategory getParent() {
		return parent;
	}
	public String getShopCategoryDesc() {
		return shopCategoryDesc;
	}
	public Long getShopCategoryId() {
		return shopCategoryId;
	}
	public String getShopCategoryImg() {
		return shopCategoryImg;
	}
	public String getShopCategoryName() {
		return shopCategoryName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public void setParent(ShopCategory parent) {
		this.parent = parent;
	}
	public void setShopCategoryDesc(String shopCategoryDesc) {
		this.shopCategoryDesc = shopCategoryDesc;
	}
	public void setShopCategoryId(Long shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}
	public void setShopCategoryImg(String shopCategoryImg) {
		this.shopCategoryImg = shopCategoryImg;
	}
	public void setShopCategoryName(String shopCategoryName) {
		this.shopCategoryName = shopCategoryName;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
