package com.cugb.exception;

import com.cugb.enumration.ProductCategoryStateEnum;

public class ProductCategoryOperationException extends RuntimeException {

	private ProductCategoryStateEnum productCategoryStateEnum;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2422531227011124692L;

	public ProductCategoryOperationException(String msg) {
		super(msg);
	}
	
}
