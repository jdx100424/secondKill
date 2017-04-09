package com.maoshen.secondkill.service.impl.test;

import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.maoshen.component.test.BaseTest;
import com.maoshen.secondkill.service.CommonsService;

public class CommonsServiceImplTest extends BaseTest {
	@Autowired
	@Qualifier("commonsServiceImpl")
	private CommonsService commonsService;

	@Test
	public void test() {
		// Long userId = Long.valueOf(new Random().nextInt(10000000) +
		// 10000000);
		Long userId = 80L;
		try {
			commonsService.draw(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLock() {
		Long userId = Long.valueOf(new Random().nextInt(10000000) + 10000000);
		
		for(int i=0;i<2;i++){
			new Thread(){
				public void run(){
					try {
						commonsService.draw(userId);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
}
