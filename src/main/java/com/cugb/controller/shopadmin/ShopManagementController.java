package com.cugb.controller.shopadmin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.mail.Session;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cugb.dao.ShopCategoryMapper;
import com.cugb.dto.ShopExecution;
import com.cugb.entity.Area;
import com.cugb.entity.PersonInfo;
import com.cugb.entity.Shop;
import com.cugb.entity.ShopCategory;
import com.cugb.enumration.ShopStateEnum;
import com.cugb.service.AreaService;
import com.cugb.service.ShopService;
import com.cugb.utils.CodeUtil;
import com.cugb.utils.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@RestController
@RequestMapping("/shopadmin")
public class ShopManagementController {

	private final static Logger logger = LoggerFactory.getLogger(ShopManagementController.class);
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryMapper shopCategoryService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private DefaultKaptcha defaultKaptcha;
	/**
	 * 店铺管理信息
	 * @param request
	 * @return
	 */
	@GetMapping("/getshopmanagementinfo")
	public Map<String, Object> getShopManagementInfo(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if(shopId<0)
		{
			Object object  = request.getSession().getAttribute("currentShop");//获取当前商店
			if (object == null) {
				map.put("redirect", true);  //如果为空，重定向
				map.put("url", "/shopadmin/shoplist");
			}else {
				Shop currentShop = (Shop) object;
				logger.info("current ShopId ===========" + currentShop.getShopId());
				map.put("redirect", false); 
				map.put("shopId", currentShop.getShopId());
				
			}
		}else {
			Shop currentshop = new Shop();
			currentshop.setShopId(shopId);
			request.getSession().setAttribute("currentShop", currentshop);   //设置当前personinfo
		//	map.put("redirect", false);
		//	map.put("url", "/shopadmin/shopmodify"+currentshop.getShopId());
		}
		return map;
	}
	/**
	 * 获取店铺列表
	 * @param request
	 * @return
	 */
	@GetMapping("/getshoplist")
	public Map<String, Object> getShopList(HttpServletRequest request)
	{
		Map<String, Object> map = new HashMap<>();
		PersonInfo personInfo = new PersonInfo();  //确保用户确实存在
		personInfo.setUserId(1L);
		personInfo.setName("test");
		personInfo.setCreateTime(new Date());
		personInfo.setUpdateTime(new Date());
		request.getSession().setAttribute("personInfo", personInfo);
		personInfo = (PersonInfo) request.getSession().getAttribute("personInfo");
		List<Shop> shopList = new ArrayList<>();
		try {
			Shop shopCondition = new Shop();
			shopCondition.setPersonInfo(personInfo);
			ShopExecution shopExecution = shopService.getShopList(shopCondition, 0, 100);
			logger.info("test..........");
			shopList = shopExecution.getShopList();
			map.put("shopList", shopList);
			map.put("personInfo", personInfo);
			map.put("success", true);
		}catch (Exception e) {
			// TODO: handle exception
			map.put("success", false);
			map.put("errMsg", e.getMessage());
		}
		return map;
	}
	/**
	 * 根据shoID获取对应的店铺
	 * @param request
	 * @return
	 */
	@GetMapping("/getshopbyid")
	private Map<String, Object> getShopById(HttpServletRequest request){
		Map<String, Object> map  = new HashMap<>();
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		HttpSession session = request.getSession();
		if(shopId > -1) {
			try {
				Shop  shop = shopService.getShopById(shopId);
				session.setAttribute("currentShop", shop);
				List<Area> areaList = areaService.getList();
				map.put("shop", shop);
				map.put("areaList", areaList);
				map.put("success", true);
			}catch(Exception e) {
				map.put("success", false);
				map.put("errMsg", e.toString());
			}
		}else {
			map.put("success", false);
			map.put("errMsg", "empty shopId");
		}
		return map;
	}
	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@GetMapping("/defaultKaptcha") //生产验证码
	public void defaultKaptcha(HttpServletRequest request,HttpServletResponse response) throws Exception {
		byte[] captchaChallengeAsJpeg = null;
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			//生产验证码字符串并保存到session中
			String createText = defaultKaptcha.createText();
			request.getSession().setAttribute("verifyCode", createText);
			//使用生产的验证码字符串返回一个bufferedImage对象并转为byte写入到byte数组中
			BufferedImage challenge = defaultKaptcha.createImage(createText);
			ImageIO.write(challenge, "jpg", jpegOutputStream);
		}catch(Exception e) {
			response.sendError(response.SC_NOT_FOUND);
			return;
		}
		//定义response输出类型为image/jpeg类型，使用response输出流输出图片为byte数组
		captchaChallengeAsJpeg  = jpegOutputStream.toByteArray();
		response.setHeader("Cache-Contro", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream servletOutputStream = response.getOutputStream();
		servletOutputStream.write(captchaChallengeAsJpeg);
		servletOutputStream.flush();
		servletOutputStream.close();
	}
	/**
	 * 获得店铺分类及相关信息
	 * @return
	 */
	@GetMapping("/getshopinitinfo")   //查询所有商铺分类和所属区域
	public Map<String, Object> getShopInitInfo()
	{
		Map<String, Object> map = new HashMap<>();
		List<ShopCategory> shopCategoryList = new ArrayList<>();
		List<Area> areaList = new ArrayList<>();
		try {
			shopCategoryList = shopCategoryService.queryShopCategory(new ShopCategory());
			areaList = areaService.getList();
			map.put("success", true);
			map.put("shopCategoryList", shopCategoryList);
			map.put("areaList", areaList);
		}catch (Exception e) {
			// TODO: handle exception
			map.put("success", false);
			map.put("errMsg", e.getMessage());
		}
		return map;
	}
	/**
	 * 注册店铺
	 * @param request
	 * @return
	 */
	@PostMapping("/registershop")
	private Map<String, Object> registerShop(HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<>();
		//提交之前判断验证码是否一致
		if(!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", "false");
			modelMap.put("errMsg", "验证码错误!");
			return modelMap;
		}
		//1.接收并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");//与前端约定好的参数,将前端传过来的字符串
		ObjectMapper mapper = new ObjectMapper(); // 将字符串转化为对象
		//在反序列化时忽略在 json 中存在但 Java 对象不存在的属性 
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false); 
		Shop shop =null;
		try {
			//mapper.enable(SerializationFeature.INDENT_OUTPUT);
			shop = mapper.readValue(shopStr, Shop.class);
			logger.info("shop===="+shop);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}/*
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(resolver.isMultipart(request))
		{
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");  //前端约定好的shopImg传值过来的
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}*/
		//2.注册店铺
		if(shop != null)
		{
			//登录成功后将用户信息添加到session (PersonInfo) request.getSession().getAttribute("user");
			PersonInfo personInfo = new PersonInfo();
			personInfo.setUserId(1L);
			shop.setPersonInfo(personInfo);
			// File shopImgFile = new File(PathUtils.getImgBasePath); 文件流转换
			// InputStreamtoFile(shopImg.getInputStream(),shopImgFile);
			ShopExecution seExecution = shopService.addShop(shop);
			if(seExecution.getState() == ShopStateEnum.CHECK.getState())
			{
				modelMap.put("success", true);
				//该用户可以操作的店铺列表
				List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
				if(shopList == null || shopList.size()==0)
				{
					shopList  = new ArrayList<>();
				}
				shopList.add(seExecution.getShop());
				request.getSession().setAttribute("shopList", shopList);
			}else
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", seExecution.getStateInfo());
			}
			return modelMap; //3.返回结果
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
			return modelMap;
		}
		

	}
	/**
	 * 根据id修改店铺属性
	 * @param request
	 * @return
	 */
	@PostMapping("/modifyshop")
	private Map<String, Object> modifyShop(HttpServletRequest request)
	{
		Map<String, Object> modelMap = new HashMap<>();
		//提交之前判断验证码是否一致
		if(!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", "false");
			modelMap.put("errMsg", "验证码错误!");
			return modelMap;
		}
		//1.接收并转化相应的参数，包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");//与前端约定好的参数,将前端传过来的字符串
		ObjectMapper mapper = new ObjectMapper(); // 将字符串转化为对象
		//在反序列化时忽略在 json 中存在但 Java 对象不存在的属性 
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false); 
		Shop shop =null;
		try {
			//mapper.enable(SerializationFeature.INDENT_OUTPUT);
			shop = mapper.readValue(shopStr, Shop.class);
			logger.info("shop===="+shop);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		//2.修改店铺信息
		if(shop != null&&shop.getShopId()!=null)
		{
			//如果店铺不为空及对应id不为空，获取修改状态
			ShopExecution seExecution = shopService.modifyShop(shop);
			//判断修改状态是否成功
			if(seExecution.getState() == ShopStateEnum.SUCCESS.getState())
			{
				modelMap.put("success", true);
			}else
			{
				modelMap.put("success", false);
				modelMap.put("errMsg", seExecution.getStateInfo());
			}
			return modelMap; //3.返回结果
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺Id");
			return modelMap;
		}
		

	}
	/**
	 * 文件处理流
	 * @param inputStream
	 * @param file
	 */
	private static void InputStreamtoFile(InputStream inputStream,File file) 
	{
		FileOutputStream outputStream = null;
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		try {
			while((bytesRead = inputStream.read(buffer))!=-1)
			{
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("调用inputStreamToFile产生异常"+e.getMessage());
		}finally {
			try {
				if(outputStream!=null)
				{
					outputStream.close();
				}
				if(inputStream!=null)
				{
					inputStream.close();
				}
			}catch (IOException e) {
				// TODO: handle exception
				throw new RuntimeException("调用inputStreamToFile关闭io产生异常"+e.getMessage());
			}
		}
	}
}
