package com.cugb.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cugb.application.Application;
import com.cugb.dao.ShopCategoryMapper;
import com.cugb.entity.ShopCategory;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@MapperScan(basePackages= {"com.cugb.dao"})
public class ShopCategoryMapperTest {

	@Autowired
	private ShopCategoryMapper shopCategoryMapper;
	@Test
	@Ignore
	public void testQueryShopCategory() {
		
	
	//	assertEquals(list.size(), 1);
		ShopCategory testCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setCreateTime(new Date());
		parentCategory.setUpdateTime(new Date());
		parentCategory.setShopCategoryId(1L);
		testCategory.setParent(parentCategory);
		List<ShopCategory> list = shopCategoryMapper.queryShopCategory(parentCategory);
		
		Iterator<ShopCategory> iterator = list.iterator();
		while(iterator.hasNext())
		{
			ShopCategory shopCategory = iterator.next();
			System.out.println(shopCategory.getShopCategoryDesc());
		}
		assertEquals(2, list.size());
	}
	
	
}
