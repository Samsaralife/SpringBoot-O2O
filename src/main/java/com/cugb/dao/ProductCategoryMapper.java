package com.cugb.dao;
/**
 * 
 * @author wangyingbo
 *
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.beust.jcommander.Parameter;
import com.cugb.entity.ProductCategory;

/*
 * 通过shopid查询店铺商品类别
 * @param long  shopId
 * @return List<ProductCategory>
 */
public interface ProductCategoryMapper {
	
	List<ProductCategory>  queryProductCategoryList(long shopId );
	/**
	 * 批量添加商品类别
	 * @param productCategoryList
	 * @return
	 */
	int batchInsertProductCategory(@Param("productCategoryList")List<ProductCategory> productCategoryList);
	//根据商品分类的id和店铺id删除商品分类
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId,@Param("shopId") long shopId);
}
