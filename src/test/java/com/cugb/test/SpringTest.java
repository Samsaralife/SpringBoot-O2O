package com.cugb.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cugb.application.Application;
import com.cugb.dao.AreaMapper;
import com.cugb.dao.ShopMapper;
import com.cugb.entity.Area;
import com.cugb.entity.PersonInfo;
import com.cugb.entity.Shop;
import com.cugb.entity.ShopCategory;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@MapperScan(basePackages= {"com.cugb.dao"})
public class SpringTest {

	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Test
	@Ignore
	public void test1()
	{
		List<Area> list = areaMapper.queryArea();
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	@Ignore
	public void testInsertShop()
	{
		Shop shop =new Shop();
		PersonInfo personInfo = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		personInfo.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setPersonInfo(personInfo);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int result = shopMapper.insertShop(shop);
		assertEquals(1, result);
	}
	
	@Test
	@Ignore
	public void testUpdateShop()
	{
		Shop shop =new Shop();
		shop.setShopId(1L);
		PersonInfo personInfo = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		personInfo.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setPersonInfo(personInfo);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试店铺");
		shop.setShopDesc("测试描述");
		shop.setShopAddr("测试地址");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		shop.setUpdateTime(new Date());
		int result = shopMapper.updateShop(shop);
		assertEquals(1, result);
	}
	
	@Test
	@Ignore
	public void queryShoptest() {
		Shop shop =shopMapper.queryShopById(1L);
		System.out.println(shop.getShopName());
	}
	
	/**
	 * 测试店铺总数以及分页
	 */
	@Test
	@Ignore
	public void testQueryShopList() {
		Shop shopCondition = new Shop();
		 PersonInfo personInfo =new PersonInfo();
		 personInfo.setUserId(1L);
		 shopCondition.setPersonInfo(personInfo);
		List<Shop>  list = shopMapper.queryShopList(shopCondition, 0, 5);
		int count = shopMapper.queryShopCount(shopCondition);
		System.out.println("店铺列表的大小" + list.size()+"count === "+count); 
	}
}
