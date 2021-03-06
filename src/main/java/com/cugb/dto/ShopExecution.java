package com.cugb.dto;
//存储店铺信息和结果状态

import java.util.List;

import com.cugb.entity.Shop;
import com.cugb.enumration.ShopStateEnum;

public class ShopExecution {
	//结果状态
	private int state;
	
	//状态表示
	private String stateInfo;
	
	//店铺数量
	private int count;
	//操作店铺的增删改查的时候用到
	private Shop shop;
	//shop列表（查询店铺列表的时候用到）
	private List<Shop> shopList;
	public ShopExecution()
	{
		
	}
	//店铺操作失败的时候使用的构造器
	public ShopExecution(ShopStateEnum stateEnum)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//店铺操作成功的时候使用的构造器
	public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
	}
	//店铺操作成功的时候使用的构造器
	public ShopExecution(ShopStateEnum stateEnum,Shop shop)
	{
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}
	public int getCount() {
		return count;
	}
	public Shop getShop() {
		return shop;
	}
	public List<Shop> getShopList() {
		return shopList;
	}
	public int getState() {
		return state;
	}
	
	public String getStateInfo() {
		return stateInfo;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setStateInfo(String stateInfo) {
			this.stateInfo = stateInfo;
		}
	
}
