package com.cugb.dao;

import org.apache.ibatis.annotations.Mapper;

import com.cugb.entity.Product;

@Mapper
public interface ProductMapper {

	
	/**
	 * 插入店铺
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);
	/**
	 * 通过productId查询唯一的商品信息
	 * @param productId
	 * @return
	 */
	Product queryProductById(Long productId);
	/**
	 * 更新商品信息
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);
	/**
	 * 删除指定商品下的所有详情图
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);
}
