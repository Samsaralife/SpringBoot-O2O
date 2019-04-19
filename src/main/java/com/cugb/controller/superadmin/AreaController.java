package com.cugb.controller.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cugb.entity.Area;
import com.cugb.service.AreaService;

@Controller
@RequestMapping("/superadmin")
public class AreaController {

	@Autowired
	private AreaService areaService;
	@GetMapping("/getArealist")
	@ResponseBody
	private Map<String, Object> listArea()
	{
		Map<String, Object> modelMap = new HashMap<>();
		List<Area> list = new ArrayList<>();
		
		try {
			list = areaService.getList();
			modelMap.put("success", true);
			modelMap.put("rows", list);
			modelMap.put("total", list.size());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}
}
