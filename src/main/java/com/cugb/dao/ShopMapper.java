package com.cugb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cugb.entity.Shop;

public interface ShopMapper {
	/**
	 * 新增商店
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	/**
	 * 更新商店
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
	/*
	 * 根据id查找店铺
	 */
	Shop queryShopById(Long shopId);
	
	/**
	 * 分页查询店铺，可输入的条件有：店铺名（模糊查询），店铺状态，店铺类别，区域ID，owner——id
	 * @param shopCondition
	 * @param rowIndex 从第几行开始取数据
	 * @param pageSize  返回的条数
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	/**
	 * 返回总数
	 * @return 
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
