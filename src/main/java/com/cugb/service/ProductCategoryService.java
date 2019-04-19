package com.cugb.service;

import java.util.List;

import com.cugb.dto.ProductCategoryExecution;
import com.cugb.entity.ProductCategory;
import com.cugb.exception.ProductCategoryOperationException;

public interface ProductCategoryService {

	/**
	 * 查询指定某个店铺下所有商品类别信息
	 * @param shopId
	 * @return List<ProductCategory>
	 */
	public List<ProductCategory> getProductCategory(long shopId);
	/**
	 * 
	 * @param productCategoryList
	 * @return
	 */
	ProductCategoryExecution  batchAddproductCategory(List<ProductCategory> productCategoryList)throws ProductCategoryOperationException;
	/**
	 * 将此类别下的商品里的类别id置为空，再删除掉该商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId) throws ProductCategoryOperationException;
}
