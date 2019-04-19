package com.cugb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cugb.entity.ShopCategory;

public interface ShopCategoryMapper {
 
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
