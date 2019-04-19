package com.cugb.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cugb.application.Application;

@SpringBootTest(classes=Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void testRedis1() {
		stringRedisTemplate.opsForValue().set("Bob", "deeping learn");
		assertEquals("deeping learn", stringRedisTemplate.opsForValue().get("Bob"));
	}
}
