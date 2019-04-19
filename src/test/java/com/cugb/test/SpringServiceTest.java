package com.cugb.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.mail.Session;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cugb.application.Application;
import com.cugb.dto.ShopExecution;
import com.cugb.entity.Area;
import com.cugb.entity.PersonInfo;
import com.cugb.entity.Shop;
import com.cugb.entity.ShopCategory;
import com.cugb.enumration.ShopStateEnum;
import com.cugb.service.ShopService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@MapperScan(basePackages= {"com.cugb.dao"})
public class SpringServiceTest {

	@Autowired
	private ShopService  shopService;
	
	@Test
	@Ignore
	public void testAddShop() {
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
		shop.setShopName("测试店铺1");
		shop.setShopDesc("test1");
		shop.setShopAddr("test1");
		shop.setPhone("test1");
		shop.setShopImg("test1");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		ShopExecution shopExecution = shopService.addShop(shop);
		assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
	}
	
	@Test
	@Ignore
	public void testModifyShop() {
		Shop shop = shopService.getShopById(1L);
		shop.setShopName("还我漂漂拳");
		ShopExecution shopExecution = shopService.modifyShop(shop);
		assertEquals(ShopStateEnum.CHECK, shopExecution.getState());
	}
}
