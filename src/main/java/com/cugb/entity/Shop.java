package com.cugb.entity;
//店铺

import java.io.Serializable;
import java.util.Date;
//店铺
public class Shop implements Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Long shopId;
		private String shopName;
		private String shopDesc;
		private String shopAddr;
		private String phone;
		private String shopImg;
		private Integer priority;
		private Date createTime;
		private Date updateTime;
		//状态 -1不可用  0 审核中 1 可用
		private Integer enableStatus;
		//超级管理员给店家的建议
		private String Advice;
		private Area area;
		private PersonInfo personInfo;
		private ShopCategory shopCategory;
		public String getAdvice() {
			return Advice;
		}
		public Area getArea() {
			return area;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public Integer getEnableStatus() {
			return enableStatus;
		}
		public PersonInfo getPersonInfo() {
			return personInfo;
		}
		public String getPhone() {
			return phone;
		}
		public Integer getPriority() {
			return priority;
		}
		public String getShopAddr() {
			return shopAddr;
		}
		public ShopCategory getShopCategory() {
			return shopCategory;
		}
		public String getShopDesc() {
			return shopDesc;
		}
		public Long getShopId() {
			return shopId;
		}
		public String getShopImg() {
			return shopImg;
		}
		public String getShopName() {
			return shopName;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
		public void setAdvice(String advice) {
			Advice = advice;
		}
		public void setArea(Area area) {
			this.area = area;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public void setEnableStatus(Integer enableStatus) {
			this.enableStatus = enableStatus;
		}
		public void setPersonInfo(PersonInfo personInfo) {
			this.personInfo = personInfo;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public void setPriority(Integer priority) {
			this.priority = priority;
		}
		public void setShopAddr(String shopAddr) {
			this.shopAddr = shopAddr;
		}
		public void setShopCategory(ShopCategory shopCategory) {
			this.shopCategory = shopCategory;
		}
		public void setShopDesc(String shopDesc) {
			this.shopDesc = shopDesc;
		}
		public void setShopId(Long shopId) {
			this.shopId = shopId;
		}
		public void setShopImg(String shopImg) {
			this.shopImg = shopImg;
		}
		public void setShopName(String shopName) {
			this.shopName = shopName;
		}
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
}
