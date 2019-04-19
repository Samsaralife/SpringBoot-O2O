package com.cugb.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cugb.dao.AreaMapper;
import com.cugb.entity.Area;
import com.cugb.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService{

	private final static Logger logger  = LoggerFactory.getLogger(AreaServiceImpl.class);
	@Autowired
	private AreaMapper areaMapper;
	@Override
	public List<Area> getList() {
		// TODO Auto-generated method stub
		return areaMapper.queryArea();
	}

	
}
