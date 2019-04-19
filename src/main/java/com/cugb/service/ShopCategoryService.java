package com.cugb.service;

import java.util.List;

import com.cugb.dto.ProductCategoryExecution;
import com.cugb.entity.ProductCategory;
import com.cugb.entity.ShopCategory;
import com.cugb.exception.ProductCategoryOperationException;

public interface ShopCategoryService {
	/**
	 * 查询指定某个店铺下的所有商品类别信息
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> queryList(ShopCategory shopCategoryCondition);
	
}
