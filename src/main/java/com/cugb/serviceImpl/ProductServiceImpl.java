package com.cugb.serviceImpl;

import java.awt.print.PrinterAbortException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring5.ISpringTemplateEngine;

import com.cugb.dao.ProductMapper;
import com.cugb.dto.ProductExecution;
import com.cugb.entity.Product;
import com.cugb.enumration.ProductEnum;
import com.cugb.exception.ProductOpearetionException;
import com.cugb.service.ProductService;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	@Override
	// 1.处理缩略图，获取缩略图相对路径并赋值给product
	// 2.往tb_product写入商品信息，获取ProductId
	// 3.结合productid批量处理商品详情图
	// 4.将商品详情图列表批量插入tb_product_img中
	@Transactional
	public ProductExecution addProduct(Product product) throws ProductOpearetionException {
		// TODO Auto-generated method stub
		//空值判断
		if(product != null && product.getShop() != null && product.getShop().getShopId() !=null)
		{
			//给商品设置上默认属性
			product.setCreateTime(new Date());
			product.setUpdateTime(new Date());
			
			//默认为上架的状态
			product.setEnableStatus(1);
			try {
				//创建商品信息
				int effectNum = productMapper.insertProduct(product);
				if(effectNum <=0 ) {
					throw new ProductOpearetionException("创建商品失败");
				}else {
					//创建商品成功
					return new ProductExecution(ProductEnum.SUCCESS,product);
				}
			} catch ( Exception e) {
				// TODO: handle exception
				throw new ProductOpearetionException("创建商品失败"+ e.toString());
			}
		}else {
			return new ProductExecution(ProductEnum.EMPTY);
		}
	}
	@Override
	@Transactional
	//更新商品
	public ProductExecution updateProduct(Product product) throws ProductOpearetionException {
		// TODO Auto-generated method stub
		if(product != null && product.getShop() != null && product.getShop().getShopId() !=null)
		{
			//商品更新时间
			product.setUpdateTime(new Date());
			try {
				int effectNum = productMapper.updateProduct(product);
				if(effectNum<0)
				{
					//更新商品失败
					throw new ProductOpearetionException("更新商品失败");
				}else {
					//更新商品成功
					return new ProductExecution(ProductEnum.SUCCESS);
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				//更新商品失败
				throw new ProductOpearetionException("更新商品出现异常"+e.getMessage());
			}
		}
		else {
			return new ProductExecution(ProductEnum.EMPTY);
		}
		
	}
	@Override
	public Product getProductById(Long productId){
		// TODO Auto-generated method stub
		return productMapper.queryProductById(productId);
	}

}
