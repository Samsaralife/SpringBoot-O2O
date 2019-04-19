package com.cugb.controller.shopadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cugb.entity.HeadLine;
import com.cugb.entity.ShopCategory;
import com.cugb.service.HeadLineService;
import com.cugb.service.ShopCategoryService;

@RestController
@RequestMapping("/frontend")
public class MainPageController {

	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private HeadLineService  headLineService;
	/**
	 * 初始化前端展示系统的主页信息，包括获取一级店铺类别类别以及头条列表
	 * @return
	 */
	@GetMapping("/listmainpageinfo")
	public Map<String, Object> listMainPageInfo()
	{
		Map<String, Object> map = new HashMap<>();
		List<ShopCategory> shopCategoryList = new ArrayList<>();
		try {
			//获取一级店铺类别列表（即parentId为空的ShopCategory）
			shopCategoryList = shopCategoryService.queryList(null);
			map.put("shopCategoryList", shopCategoryList);
		}catch (Exception e) {
			// TODO: handle exception
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		List<HeadLine> headLineList = new ArrayList<>();
		try {
			//获取状态可用为1的头条列表
			HeadLine headLine = new HeadLine();
			headLine.setEnableStatus(1);
			headLineList = headLineService.getHeadLineList(headLine);
			map.put("headLineList", headLineList);
		} catch (Exception e) {
			// TODO: handle exception
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		map.put("success", true);
		return map;
	}
}
