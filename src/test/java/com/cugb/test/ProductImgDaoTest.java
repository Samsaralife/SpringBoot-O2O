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
import com.cugb.dao.ProductImgMapper;
import com.cugb.entity.ProductImg;

@SpringBootTest(classes=Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@MapperScan(basePackages = {"com.cugb.dao"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest {

	@Autowired
	private ProductImgMapper productImgMapper;
	@Test
	@Ignore
	public void testABatchInsertProductImg()throws Exception{
		
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("图片一");
		productImg1.setImgDesc("测试图片1");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(2L);
		
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("图片二");
		productImg2.setImgDesc("测试图片二");
		productImg2.setCreateTime(new Date());
		productImg2.setPriority(1);
		productImg2.setProductId(2L);
		
		List<ProductImg> list = new ArrayList<>();
		list.add(productImg1);
		list.add(productImg2);
		int effectNum = productImgMapper.batchInsertProductImg(list);
		assertEquals(2, effectNum);
	}
}
