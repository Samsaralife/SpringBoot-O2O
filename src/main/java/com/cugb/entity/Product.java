package com.cugb.entity;
//商品

import java.util.Date;
import java.util.List;

public class Product {
	private Long productId;
	private String productName;
	private String productDesc;
	//简略图
	private String imgAddr;
	//原价
	private String normalPrice;
	//折扣价
	private float promotionPrice;
	private Integer priority;
	private Date createTime;
	private Date updateTime;
	private Integer enableStatus;
	private List<ProductImg> productImgs ;
	private ProductCategory productCategory;
	private Shop shop;
	public Date getCreateTime() {
		return createTime;
	}
	public Integer getEnableStatus() {
		return enableStatus;
	}
	public String getImgAddr() {
		return imgAddr;
	}
	public String getNormalPrice() {
		return normalPrice;
	}
	public Integer getPriority() {
		return priority;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public Long getProductId() {
		return productId;
	}
	public List<ProductImg> getProductImgs() {
		return productImgs;
	}
	public String getProductName() {
		return productName;
	}
	public float getPromotionPrice() {
		return promotionPrice;
	}
	public Shop getShop() {
		return shop;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}
	public void setImgAddr(String imgAddr) {
		this.imgAddr = imgAddr;
	}
	public void setNormalPrice(String normalPrice) {
		this.normalPrice = normalPrice;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setProductImgs(List<ProductImg> productImgs) {
		this.productImgs = productImgs;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setPromotionPrice(float promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
