package com.cugb.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cugb.dao.ProductCategoryMapper;
import com.cugb.dao.ShopCategoryMapper;
import com.cugb.dto.ProductCategoryExecution;
import com.cugb.entity.ProductCategory;
import com.cugb.entity.ShopCategory;
import com.cugb.exception.ProductCategoryOperationException;
import com.cugb.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl  implements ShopCategoryService{

	@Autowired
	private ShopCategoryMapper shopCategoryMapper;

	@Override
	public List<ShopCategory> queryList(ShopCategory shopCategoryCondition) {
		// TODO Auto-generated method stub
		return shopCategoryMapper.queryShopCategory(shopCategoryCondition);
	}

}
