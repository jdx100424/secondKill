package com.maoshen.secondkill.service.impl.test;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.maoshen.component.redis.RedisService;
import com.maoshen.component.test.BaseTest;
import com.maoshen.secondkill.service.impl.test.pojo.TestObject;

public class RedisObjectTest extends BaseTest {
	@Autowired
	private RedisService redisService;
	
	@Test
	public void test() {
		TestObject testObject = new TestObject();
		//testObject.setName("jdxName");
		try {
			//redisService.insertByValue("111", testObject,1,TimeUnit.HOURS);
			TestObject testObjectClass = (TestObject) redisService.getByValue("111");
			System.out.println(JSONObject.toJSONString(testObjectClass));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
