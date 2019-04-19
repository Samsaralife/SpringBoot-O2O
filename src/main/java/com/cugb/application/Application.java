package com.cugb.application;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.cugb"})
@MapperScan(basePackages= {"com.cugb.dao"})
public class Application {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args)
	{
		LOGGER.warn("starting application......");
		SpringApplication.run(Application.class, args);
	}
}
