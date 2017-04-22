package com.maoshen.secondkill.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maoshen.common.constant.CommonKey;
import com.maoshen.component.base.errorcode.BaseErrorCode;
import com.maoshen.component.exception.BaseException;
import com.maoshen.component.redis.RedisService;
import com.maoshen.secondkill.dao.RedPackageDao;
import com.maoshen.secondkill.domain.RedPackage;
import com.maoshen.secondkill.service.RedPackageService;
import com.maoshen.secondkill.service.impl.test.CommonsServiceImplTest;
import com.maoshen.secondkill.service.vo.LotteryRecordDto;

@Service("redPackageServiceImpl")
public class RedPackageServiceImpl implements RedPackageService {
	private static final Long EVENT_ID = 2L;
	@Autowired
	private RedPackageDao redPackageDao;
	@Autowired
	private RedisService redisService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long create(String redPackageName, Integer redPackageCount, Integer money, boolean isAllSame,
			List<Long> userIdList) throws Exception {
		if (redPackageCount < 1 || redPackageCount >= 30 || money <= 0 || money >= 1000 || userIdList == null
				|| userIdList.isEmpty() || userIdList.size() > 30) {
			throw new BaseException("MARKETING", BaseErrorCode.SERVICE_EXCEPTION);
		}
		List<RedPackage> list = new ArrayList<RedPackage>();
		// 红包有效期为24小时内
		Date nowDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(nowDate);
		c.add(Calendar.DAY_OF_YEAR, 1);
		Date endDate = c.getTime();
		Long groupId = System.currentTimeMillis();

		List<Double> redPackageList = getRandomRedPackage(money,redPackageCount,isAllSame);
		
		for (int i = 0; i < redPackageCount; i++) {
			// 红包有效期为24小时
			RedPackage redPackage = new RedPackage();
			redPackage.setGroupId(groupId);
			redPackage.setName(redPackageName);
			redPackage.setStock(1);
			redPackage.setDescription(redPackageName);
			redPackage.setEventId(EVENT_ID);
			redPackage.setBeginDate(nowDate);
			redPackage.setEndDate(endDate);
			redPackage.setProbability(1D);
			redPackage.setCreated(nowDate);
			redPackage.setMoney(redPackageList.get(i));
			list.add(redPackage);
		}
		//红包入库，并创建对应的单队列缓存
		redPackageDao.createRedPackage(list);
		List<Long> redPackageIdList = new ArrayList<Long>();
		List<RedPackage> resultList = redPackageDao.getRedPackageListByGroupId(groupId);
		String userRedPackage = String.format(CommonKey.REDPACKAGE_SECONDKILL_ID, groupId);
		for(RedPackage redPackage:resultList){
			redPackageIdList.add(redPackage.getId());
		}
		redisService.insertAllList(userRedPackage, redPackageIdList, 23, TimeUnit.HOURS);	
		
		//可抽此红包用户REDIS队列
		String allowUser = String.format(CommonKey.REDPACKAGE_SECONDKILL_ID_USERLIST, groupId);
		Map<String,Long> map = new HashMap<String,Long>();
		for(Long l:userIdList){
			map.put(Long.toString(l), l);
		}
		redisService.insertAllHash(allowUser, map, 23, TimeUnit.HOURS);
		return groupId;
	}

	@Override
	public LotteryRecordDto draw(Long userId, Long eventId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LotteryRecordDto> getRecordByUserId(Long userId, Long eventId) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Double> getRandomRedPackage(Integer money,Integer size,boolean isAllSame){
		List<Double> resultList = new ArrayList<Double>();
		if(isAllSame){
			BigDecimal big = new BigDecimal(money);
			BigDecimal bigCount = new BigDecimal(size);
			double resultMoney = big.divide(bigCount).setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
			for (int i = 0; i < size; i++) {
				resultList.add(resultMoney);
			}
		}else{
			LeftMoneyPackage leftMoneyPackage = new LeftMoneyPackage();
			leftMoneyPackage.setRemainMoney(money);
			leftMoneyPackage.setRemainSize(size);
			for(int i=0;i<size;i++){
				double result =  getRandom(leftMoneyPackage);
				resultList.add(result);
			}
		}
		return resultList;
	}
	
	private double getRandom(LeftMoneyPackage _leftMoneyPackage){
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
	
	public class LeftMoneyPackage {
		public int remainSize;
		public double remainMoney;
		public int getRemainSize() {
			return remainSize;
		}
		public void setRemainSize(int remainSize) {
			this.remainSize = remainSize;
		}
		public double getRemainMoney() {
			return remainMoney;
		}
		public void setRemainMoney(double remainMoney) {
			this.remainMoney = remainMoney;
		}
	}
}
