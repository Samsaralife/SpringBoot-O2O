package com.cugb.serviceImpl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cugb.dao.ShopMapper;
import com.cugb.dto.ShopExecution;
import com.cugb.entity.Shop;
import com.cugb.enumration.ShopStateEnum;
import com.cugb.exception.ShopOperationException;
import com.cugb.service.ShopService;
import com.cugb.utils.PageCalculator;

@Transactional
@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopMapper shopMapper;
	@Override
	public ShopExecution addShop(Shop shop) {
		// 1.空值判断 2.初始值赋值 3. 添加店铺信息
		if(shop == null)
		{
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			shop.setEnableStatus(0);  //审核中
			shop.setCreateTime(new Date());
			shop.setUpdateTime(new Date());
			int effectedNum = shopMapper.insertShop(shop);
			if(effectedNum <= 0)
			{
				throw new ShopOperationException("店铺创建失败");  //事物回滚
			}
			/*
			else
			{
				if(shopImg != null)
				{
					//存储图片
					try
					{
						addShopImg(shop,shopImg);
						shop.setShopImg();
					}catch(Exception e)
					{
						throw new RuntimeException("addShopImg error" + e.getMessage());
					}
					//更新店铺的图片地址
					effectedNum = shopMapper.updateShop(shop);
					if(effectedNum <= 0)
					{
						throw new RuntimeException("更新图片地址失败");
					}
				}
			}*/
		}catch(Exception e)
		{
			throw new ShopOperationException("addShop error"+e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}
	/*
	private void addShopImg(Shop shop, File shopImg) {
		// TODO Auto-generated method stub
		//获取shop图片目录的相对路径
		String dest = PathUtil.getShopImagePath(shop.getShopId);
		String shopImgaddr = ImageUtil.generateThumbnail(shopImg,dest);
		shop.setShopImg(shopImgaddr);
	}
	*/
	/**
	 * 根据id获取店铺
	 */
	@Override
	public Shop getShopById(Long shopId) {
		// TODO Auto-generated method stub
		return shopMapper.queryShopById(shopId);
	}
	/**
	 * 更新店铺
	 */
	@Override
	public ShopExecution modifyShop(Shop shop) {
		// TODO Auto-generated method stub
		try {
			if(shop == null || shop.getShopId() == null)
			{
				return new ShopExecution(ShopStateEnum.NULL_SHOP);
			}
			// 1.更新店铺信息
			shop.setUpdateTime(new Date());
			int effectNum = shopMapper.updateShop(shop);
			if(effectNum<=0)
			{
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			}else {
				shop = shopMapper.queryShopById(shop.getShopId());
				return new ShopExecution(ShopStateEnum.SUCCESS,shop);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ShopOperationException("modifyShop error" + e.getMessage());
		}
	}
	/**
	 * 分页功能
	 */
	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize); //页数转换
		List<Shop> shopList = shopMapper.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopMapper.queryShopCount(shopCondition);
		ShopExecution sExecution = new ShopExecution();
		if(shopList!=null)
		{
			sExecution.setShopList(shopList);
			sExecution.setCount(count);
		}else {
			sExecution.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return sExecution;
	}

}
