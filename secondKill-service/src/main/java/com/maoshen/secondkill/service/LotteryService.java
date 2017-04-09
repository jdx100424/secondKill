package com.maoshen.secondkill.service;

import com.maoshen.component.db.DataSource;
import com.maoshen.secondkill.service.vo.LotteryResult;

public interface LotteryService {
	@DataSource("master")
	public LotteryResult lottery(long userId, long eventId) ;
	@DataSource("master")
	public LotteryResult lotteryWithStockOne(long userId, long eventId,boolean isRecordFail) ;
}
