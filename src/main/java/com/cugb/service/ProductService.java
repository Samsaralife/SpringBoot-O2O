package com.cugb.service;

import com.cugb.dto.ProductExecution;
import com.cugb.entity.Product;
import com.cugb.exception.ProductOpearetionException;

public interface ProductService {
	/**
	 * 添加商品
	 * @param product
	 * @return
	 * @throws ProductOpearetionException
	 */
	ProductExecution addProduct(Product product) throws ProductOpearetionException;
	ProductExecution updateProduct(Product product) throws ProductOpearetionException;
	Product getProductById(Long productId);
}
