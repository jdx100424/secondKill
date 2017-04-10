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
	
	public static void main(String []s){
		CommonsServiceImplTest commonsServiceImplTest =new CommonsServiceImplTest();
		LeftMoneyPackage leftMoneyPackage = new LeftMoneyPackage();
		leftMoneyPackage.setRemainMoney(10);
		leftMoneyPackage.setRemainSize(10);
		double d = 0;
		for(int i=0;i<10;i++){
			double result =  commonsServiceImplTest.getRandom(leftMoneyPackage);
			d = d + result;
			System.out.println(result);
		}
		System.out.println("result:"+d);
	}
	
	
	public double getRandom(LeftMoneyPackage _leftMoneyPackage){
		if (_leftMoneyPackage.remainSize == 1) {
			_leftMoneyPackage.remainSize--;
			return (double)Math.round(_leftMoneyPackage.remainMoney*100)/100;
		}
		Random r = new Random();
		double min = 0.01;
		double max = _leftMoneyPackage.remainMoney/_leftMoneyPackage.remainSize*2;
		double money = r.nextDouble()*max;
		money = money<=min?0.01:money;
		money = Math.floor(money*100)/100;
		_leftMoneyPackage.remainSize--;
		_leftMoneyPackage.remainMoney = _leftMoneyPackage.remainMoney-money;
		return money;
	} 
}
