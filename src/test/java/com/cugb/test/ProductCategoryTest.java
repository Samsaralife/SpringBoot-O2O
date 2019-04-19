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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cugb.application.Application;
import com.cugb.dao.ProductCategoryMapper;
import com.cugb.entity.ProductCategory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@MapperScan(basePackages={"com.cugb.dao"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryTest {

	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	private final static Logger logger = LoggerFactory.getLogger(ProductCategoryTest.class);
	long shopId = 1L;
	@Test
	@Ignore
	public void Product_category_test()
	{
		List<ProductCategory> list = productCategoryMapper.queryProductCategoryList(shopId);
		assertEquals(list.size(), 1);
		System.out.println(list.get(0).getProductCategoryName());
	}
	
	@Test   //批量添加商铺类别
	@Ignore
	public void testBatchInsertProductCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("商铺类别1");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(2L);
		
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("商铺类别2");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(2L);
		List<ProductCategory> list = new ArrayList<>();
		list.add(productCategory);
		list.add(productCategory2);
		 int result = productCategoryMapper.batchInsertProductCategory(list);
		 assertEquals(2, result);
	}
	
	@Test
	@Ignore
	public void testDeleteProductCategory() throws Exception {
		long shopId1 = 2L;
		long productCategoryId = 38L;
		int effectNum = productCategoryMapper.deleteProductCategory(productCategoryId, shopId1);
		assertEquals(1, effectNum);
//		long productCategoryId = 1L;
//		int effectNum = productCategoryMapper.deleteProductCategory(productCategoryId, shopId);
//		assertEquals(1, effectNum);
	}
}
