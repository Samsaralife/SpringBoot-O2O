package com.cugb.dao;

import java.util.List;

import com.cugb.entity.ProductImg;

public interface ProductImgMapper {

	/**
	 * 查询所有商品图片
	 * @param productId
	 * @return
	 */
	List<ProductImg> queryProductImgList(Long productId);
	/**
	 * 批量添加商品详情图片
	 * @param productImgList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);
	/**
	 * 根据商品id删除图片
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(Long productId);
}
