package com.cugb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cugb.entity.Area;
@Mapper
public interface AreaMapper {
	//查询所有地域列表
	List<Area> queryArea();
}
