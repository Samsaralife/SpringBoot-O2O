package com.cugb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cugb.entity.HeadLine;
@Mapper
public interface HeadLineMapper {

	/**
	 * 根据传入的条件进行查询
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}
