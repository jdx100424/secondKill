package com.maoshen.secondkill.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.maoshen.common.constant.CommonKey;
import com.maoshen.component.base.errorcode.BaseErrorCode;
import com.maoshen.component.exception.BaseException;
import com.maoshen.component.other.DateUtils;
import com.maoshen.component.redis.RedisService;
import com.maoshen.secondkill.dao.EventDao;
import com.maoshen.secondkill.domain.Event;
import com.maoshen.secondkill.domain.LotteryRecord;
import com.maoshen.secondkill.service.CommonsService;
import com.maoshen.secondkill.service.LotteryRecordService;
import com.maoshen.secondkill.service.LotteryService;
import com.maoshen.secondkill.service.base.SecondKillBaseService;
import com.maoshen.secondkill.service.vo.LotteryRecordDto;
import com.maoshen.secondkill.service.vo.LotteryResult;

@Service("commonsServiceImpl")
public class CommonsServiceImpl extends SecondKillBaseService implements CommonsService {
	private static final Long EVENT_ID = 1L;
	private static final Integer TODAY_AWARD_COUNT = 3;
	@Autowired
	private RedisService redisService;
	@Autowired
	private EventDao eventDao;
	@Autowired
	@Qualifier("lotteryServiceImpl")
	private LotteryService lotteryService;
	@Autowired
	@Qualifier("lotteryRecordServiceImpl")
	private LotteryRecordService lotteryRecordService;

	private static final Logger logger = LoggerFactory.getLogger(CommonsServiceImpl.class);

	@Override
	@Transactional(rollbackFor = Exception.class)
	public LotteryRecordDto draw(Long userId) throws Exception {
		Date nowDate = new Date();
		Integer nowDateInt = Integer.parseInt(DateUtils.format(nowDate, "yyyyMMdd"));

		// 判断活动是否已经下架
		Event event = eventDao.selectById(EVENT_ID);
		if (event == null || !event.isAvailable()) {
			throw new BaseException("MARKETING", BaseErrorCode.SERVICE_EXCEPTION);
		}

		// redis lock迟点补上
		long lotteryCount = getLotteryCount(userId, EVENT_ID, nowDateInt);
		if (lotteryCount >= TODAY_AWARD_COUNT) {
			throw new BaseException("MARKETING", BaseErrorCode.SERVICE_EXCEPTION);
		}

		String userEventLock = String.format(CommonKey.COMMON_SECONDKILL_ACTIVE, userId,EVENT_ID);
		try {
			redisService.lock(userEventLock);
			// 抽奖
			LotteryResult lotteryResult = lotteryService.lotteryWithStockOne(userId, EVENT_ID,false);
			lotteryIncrEventDay(userId, EVENT_ID, nowDateInt);
			if (lotteryResult.isWin()) {
				logger.info("prize ok,info:{}", JSONObject.toJSONString(lotteryResult.getLotteryRecordDto()));
			} else {
				logger.warn("prize ok but not any obj");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BaseException("MARKETING", BaseErrorCode.SERVICE_EXCEPTION);
		} finally {
			redisService.unlock(userEventLock);
		}
		return null;
	}

	@Override
	public List<LotteryRecordDto> getRecordByUserId(Long userId) {
		List<LotteryRecord> list = lotteryRecordService.getLotteryRecordList(userId,EVENT_ID);
		List<LotteryRecordDto> resultList = new ArrayList<LotteryRecordDto>();
		//用USERID获取代金券对应中奖码的所有数据，FOR循环里用于比较
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		
		for(LotteryRecord lotteryRecord:list){
			LotteryRecordDto LotteryRecordDto = new LotteryRecordDto();
			BeanUtils.copyProperties(lotteryRecord, LotteryRecordDto);
			resultList.add(LotteryRecordDto);
		}
		return resultList;
	}
}
