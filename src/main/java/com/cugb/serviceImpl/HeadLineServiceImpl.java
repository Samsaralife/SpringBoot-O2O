package com.cugb.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cugb.dao.HeadLineMapper;
import com.cugb.entity.HeadLine;
import com.cugb.service.HeadLineService;
@Service
public class HeadLineServiceImpl implements HeadLineService {

	@Autowired
	private HeadLineMapper headLineMapper;
	@Override
	/**
	 * 根据查询条件查出所有HeadLine
	 */
	public List<HeadLine> getHeadLineList(HeadLine headLine) {
		// TODO Auto-generated method stub
		return headLineMapper.queryHeadLine(headLine);
	}

}
