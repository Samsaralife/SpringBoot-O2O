package com.cugb.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cugb.dao.ProductCategoryMapper;
import com.cugb.dto.ProductCategoryExecution;
import com.cugb.entity.ProductCategory;
import com.cugb.enumration.ProductCategoryStateEnum;
import com.cugb.exception.ProductCategoryOperationException;
import com.cugb.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	@Override
	public List<ProductCategory> getProductCategory(long shopId) {
		// TODO Auto-generated method stub
		return productCategoryMapper.queryProductCategoryList(shopId);
	}
	/**
	 * 批量添加店铺
	 */
	@Override
	@Transactional
	public ProductCategoryExecution batchAddproductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		// TODO Auto-generated method stub
		if(productCategoryList !=null && productCategoryList.size()>0) {
			try {
				int effectNum = productCategoryMapper.batchInsertProductCategory(productCategoryList);
				
				if(effectNum <=0) {
					throw new ProductCategoryOperationException("店铺类别创建失败");
				}else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				// TODO: handle exception
				throw new ProductCategoryOperationException("batchAddProductCategory error:"+e.getMessage());
			}
		}else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}
	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		// TODO Auto-generated method stub
		try {
			// todo 将此商品类别下的商品类别id置为空
			int effectNum = productCategoryMapper.deleteProductCategory(productCategoryId, shopId);
			if(effectNum<0) {
				throw new ProductCategoryOperationException("商品类别删除失败");
			}else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ProductCategoryOperationException("deleteProductCategory error"+e.getMessage());
		}
	}

}
