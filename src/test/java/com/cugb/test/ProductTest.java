package com.cugb.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cugb.application.Application;
import com.cugb.dao.ProductMapper;
import com.cugb.entity.Product;
import com.cugb.entity.ProductCategory;
import com.cugb.entity.ProductImg;
import com.cugb.entity.Shop;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@MapperScan(basePackages= {"com.cugb.dao"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductTest {

	@Autowired
	private ProductMapper productMapper;
	@Test
	@Ignore
	public void testInsertProduct() {
		
		Product product = new Product();
		Shop shop = new Shop();
		ProductCategory productCategory = new ProductCategory();
		List<ProductImg> list = new ArrayList<>();
		ProductImg productImg = new ProductImg();
		productImg.setProductId(1L);
		productImg.setImgAddr("测试地址");
		productImg.setImgDesc("测试描述");
		productImg.setPriority(1);
		productImg.setCreateTime(new Date());
		productImg.setProductId(1L);
		list.add(productImg);
		
		shop.setShopId(1L);
		productCategory.setProductCategoryId(2L);
		product.setProductId(1L);
		product.setImgAddr("hehh");
		product.setProductName("商品测试");
		product.setProductDesc("中国地质大学");
		product.setNormalPrice("100");
		product.setPriority(1);
		product.setEnableStatus(1);
		product.setCreateTime(new Date());
		product.setUpdateTime(new Date());
		product.setProductCategory(productCategory);
		product.setShop(shop);
		product.setPromotionPrice((float) 11.0);
		int effectNum = productMapper.insertProduct(product);
		assertEquals(effectNum,1 );
	}
	
	@Test
	@Ignore
	public void testGetProduct()
	{
		Long productId = 3L;
		Product product = productMapper.queryProductById(productId);
		assertEquals(product.getProductName(), "商品测试");
	}
}
