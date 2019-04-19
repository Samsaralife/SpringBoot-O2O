package com.cugb.controller.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cugb.dto.ProductExecution;
import com.cugb.entity.Product;
import com.cugb.entity.ProductCategory;
import com.cugb.entity.Shop;
import com.cugb.enumration.ProductEnum;
import com.cugb.exception.ProductOpearetionException;
import com.cugb.service.ProductCategoryService;
import com.cugb.service.ProductService;
import com.cugb.service.ShopService;
import com.cugb.utils.CodeUtil;
import com.cugb.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/shopadmin")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ProductCategoryService productCategoryService;
	/**
	 * 添加商品
	 * @param request
	 * @return
	 */
	@PostMapping("/addProduct")
	public Map<String, Object> addProduct(HttpServletRequest request)
	{
		Map<String, Object> map  = new HashMap<>();
		//验证校验码
		if(!CodeUtil.checkVerifyCode(request)) {
			map.put("success", false);
			map.put("errMsg", "验证码错误");
			return map;
		}
		//接收前端参数的变量的初始化，包括商品，缩略图，详情图列表实体类
		ObjectMapper objectMapper = new ObjectMapper();
		Product product= null;
		String productStr = HttpServletRequestUtil.getString(request, "productStr");
		//在反序列化时忽略在 json 中存在但 Java 对象不存在的属性 
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false); 
		try {
			//尝试获取前端传过来的表单String流并将其转换成product实体类
			product= objectMapper.readValue(productStr, Product.class);
		}catch(Exception exception) {
			map.put("success", false);
			map.put("errMsg", exception.toString());
			return map;
		}
		//若商品信息不为空,则开始进行商品添加操作
		if(product !=null)
		{
			try {
				//从session中获取当前店铺id并赋值给product，减少对前端数据的依赖
				Shop currentShop =  (Shop) request.getSession().getAttribute("currentShop");
				Shop shop = null;
				try
				{
					shop = shopService.getShopById(product.getShop().getShopId());
				}catch(Exception exception)
				{
					exception.printStackTrace();
				}
				//shop.setShopId(2L);
				product.setShop(shop);
				//执行添加操作
				ProductExecution productExecution =  productService.addProduct(product);
				if(productExecution.getState() == ProductEnum.SUCCESS.getState())
				{
					map.put("success", true);
				}else {
					map.put("success", false);
					map.put("errMsg", productExecution.getStateInfo());
				}
			} catch (ProductOpearetionException e) {
				// TODO Auto-generated catch block
				map.put("success", false);
				map.put("errMsg", e.toString());
				e.printStackTrace();
			}
		}else {
			map.put("success", false);
			map.put("errMsg", "请输入商品信息");
		}
		return map;
	}
	//更新商品
	@PostMapping("/updateProduct")
	public Map<String, Object> updateProduct(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		//验证验证码是否正确
		if(!CodeUtil.checkVerifyCode(request))
		{
			map.put("success", false);
			map.put("errMsg", "验证码错误");
			return map;
		}
		//接收前端传过来的参数，并将其反序列化
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = null;
		//获取前端传过来的参数
		String productStr = HttpServletRequestUtil.getString(request, "productStr");
		//在反序列化时忽略在 json 中存在但 Java 对象不存在的属性 
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false); 
		try {
			product = objectMapper.readValue(productStr, Product.class);
		}catch (Exception e) {
			// TODO: handle exception
			map.put("success", false);
			map.put("errMsg", e.getMessage());
			return map;
		}
		//如果前端传过来的product对象不为空
		if(product != null)
		{
			try {
				//从当前session中取出店铺    to do it 
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				Shop shop = null;
				try {
					shop = shopService.getShopById(product.getShop().getShopId());
				}catch(Exception e)
				{
					map.put("success", false);
					map.put("errMsg", e.getMessage());
				}
				//设置商品所对应的店铺
				product.setShop(shop);
				//更新商品
				ProductExecution productExecution = productService.updateProduct(product);
				//如果状态码为SUCCESS
				if(productExecution.getState() == ProductEnum.SUCCESS.getState())
				{
					map.put("success", true);
				}else {
					map.put("success", false);
					map.put("errMsg", "更新失败");
				}
			}catch(Exception e) {
				map.put("success", false);
				map.put("errMsg", e.getMessage());
				return map;
			}
		}
		return map;
	}
	
	@GetMapping("/getProduct")
	public Map<String, Object> getProductById(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		Long productId = HttpServletRequestUtil.getLong(request, "productId");
		if(productId > -1) {
			try {
				Product product = productService.getProductById(productId);
				List<ProductCategory> productCategorylist = productCategoryService.getProductCategory(product.getShop().getShopId());
				map.put("success", true);
				map.put("product", product);
				map.put("productCategorylist", productCategorylist);
			} catch (Exception e) {
				// TODO: handle exception
				map.put("success", false);
				map.put("errMsg", "查不到该Id对应的商品");
				return map;
			}
		}else {
			map.put("success", false);
			map.put("errMsg", "productId不能小于0");
		}
		return map;
	}
}
