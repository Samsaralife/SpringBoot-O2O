package com.cugb.controller.shopadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cugb.dto.ProductCategoryExecution;
import com.cugb.entity.Product;
import com.cugb.entity.ProductCategory;
import com.cugb.entity.Shop;
import com.cugb.enumration.ProductCategoryStateEnum;
import com.cugb.exception.ProductCategoryOperationException;
import com.cugb.service.ProductCategoryService;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;

@RestController
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	/**
	 * 获取商品分类信息列表
	 * @param request
	 * @return
	 */
	@GetMapping("/getproductcategorylist")
	public Map<String, Object> getProductCategory(HttpServletRequest request){
		// to be removed
		Map<String, Object> map = new HashMap<>();
		Shop shop = new Shop();
		shop.setShopId(2L);
		request.getSession().setAttribute("currentShop", shop);
		Shop currentShop =(Shop) request.getSession().getAttribute("currentShop");  //从session中取出当前shop
		List<ProductCategory> productCategoryList = null;
		if(currentShop != null && currentShop.getShopId()>0) {
			productCategoryList = productCategoryService.getProductCategory(currentShop.getShopId());
			map.put("productCategoryList", productCategoryList);
			map.put("success", true);
		}else {
			map.put("success", false);
		}
		return map;
	}
	/**
	 * 批量添加商品分类列表
	 * @param productCategoryList
	 * @param request
	 * @return
	 */
	@PostMapping("/addproductcategory")
	public Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		//获取session中的shop从而获取shopId，防止批量添加商品的时候添加到其他商店中去，同时也减少对前端的依赖
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop"); 
		//给每个商品类别设置ShopId
		for(ProductCategory pCategory : productCategoryList) {
			pCategory.setShopId(currentShop.getShopId());
			pCategory.setCreateTime(new Date());
		}
		if(productCategoryList !=null && productCategoryList.size()>0) {
			try {
				ProductCategoryExecution pExecution = productCategoryService.batchAddproductCategory(productCategoryList);
				if(pExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					map.put("success",true);
				}else {
					map.put("success", false);
					map.put("errMsg", pExecution.getStateInfo());
				}
			} catch (ProductCategoryOperationException e) {
				// TODO: handle exception
				map.put("success", false);
				map.put("errMsg", e.toString());
				return map;
			}
		}else {
			map.put("success", false);
			map.put("errMsg", "请输入至少一个商品类别");
		}
		return map;
	}
	/**
	 * 删除商品类别
	 * @param productCategoryId
	 * @param request
	 * @return
	 */
	@PostMapping("/removeproductcatgory")
	public Map<String, Object> removeProduct(@Param("productCategoryId")Long productCategoryId,HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		if(productCategoryId != null && productCategoryId >0) {
			try {
				//从session中取出当前shop
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				ProductCategoryExecution productCategoryExecution = productCategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
				if(productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					map.put("success", true);
				}else {
					map.put("success", false);
					map.put("errMsg", productCategoryExecution.getState());
				}
			} catch (ProductCategoryOperationException e) {
				// TODO Auto-generated catch block
				map.put("success", false);
				map.put("errMsg", e.toString());
				e.printStackTrace();
			}
		}else {
			map.put("success", false);
			map.put("errMsg", "请至少选择一个商品类别");
		}
		return map;
	}
}
