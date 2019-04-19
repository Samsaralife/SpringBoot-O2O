package com.cugb.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cugb.application.Application;
import com.cugb.dao.HeadLineMapper;
import com.cugb.entity.HeadLine;

@SpringBootTest(classes=Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@MapperScan(basePackages= {"com.cugb.dao"})
public class HeadLineTest {

	@Autowired
	private  HeadLineMapper headLineMapper;
	
	@Test
	public void testQueryList()
	{
		List<HeadLine> list = headLineMapper.queryHeadLine(new HeadLine());
		assertEquals(2, list.size());
		
	}
}
