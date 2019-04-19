package com.cugb.service;



import com.cugb.dto.ShopExecution;
import com.cugb.entity.Shop;

public interface ShopService {

	/**
	 * 注册店铺
	 * @param shop
	 * @return
	 */
	ShopExecution addShop(Shop shop);
	/**
	 * 根据id获取店铺信息
	 * @param shopId
	 * @return
	 */
	Shop getShopById(Long shopId);
	/**
	 * 修改店铺
	 * @param shop
	 * @return
	 */
	ShopExecution modifyShop(Shop shop);
	/**
	 * 根据ShopConditon返回分页功能
	 * 
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition,int pageIndex, int pageSize);
}
