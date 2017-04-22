package com.maoshen.secondkill.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public Long create(String redPackageName, int redPackageCount, Integer money, boolean isAllSame,
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
			if (isAllSame) {
				BigDecimal big = new BigDecimal(money);
				BigDecimal bigCount = new BigDecimal(redPackageCount);
				double resultMoney = big.divide(bigCount).setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
				redPackage.setMoney(resultMoney);
			} else {
				// TODO
				throw new BaseException("MARKETING", BaseErrorCode.SERVICE_EXCEPTION);
			}
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

}
