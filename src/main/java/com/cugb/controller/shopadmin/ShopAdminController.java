package com.cugb.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopadmin")
public class ShopAdminController {

	//店铺注册页面
	@GetMapping("/shopoperation")
	public String shopOperation()
	{
		return "index";
	}
	
	/**
	 * 店铺列表
	 * @return
	 */
	@GetMapping("/shoplist")
	public String shopList() {
		return "shoplist";
	}
	/**
	 * 店铺管理页面
	 * @return
	 */
	@GetMapping("/shopmanagent")
	public String shopManagement() {
		return "shopmanagent";
	}
	
	/**
	 * 商品分类管理页面
	 * @return
	 */
	@GetMapping("/productcategorymanage")
	public String ProductCategoryManagement() {
		return "productcategorymanagement";
	}
	
	@GetMapping("/productoperation")
	public String productOperation() {
		//转发至商品添加/编辑页面
		return "shop/productoperation";
	}
	
	@GetMapping("/userlogin")
	public String login()
	{
		return "userlogin";
	}
	
	@GetMapping("/addproduct")
	public String addProduct() {
		return "addproduct";
	}
	
	@GetMapping("/gotofrontend")
	public String goToFrontEnd()
	{
		return "frontend";
	}
	
	@GetMapping("/toregister")
	public String regesterUser()
	{
		return "register";
	}
}
