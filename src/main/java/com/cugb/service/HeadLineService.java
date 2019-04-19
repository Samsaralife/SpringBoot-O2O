package com.cugb.service;

import java.util.List;

import com.cugb.entity.HeadLine;

public interface HeadLineService {

	/**
	 * 根据查询条件查询出所有headLine
	 * @param headLine
	 * @return
	 */
	List<HeadLine> getHeadLineList(HeadLine headLine);
}
