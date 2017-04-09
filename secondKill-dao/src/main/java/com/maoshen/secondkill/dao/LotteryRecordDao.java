package com.maoshen.secondkill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.maoshen.secondkill.domain.LotteryRecord;

@Repository
public interface LotteryRecordDao {
	/**
	 * 根据用户id、活动id、抽奖轮次获取用户抽奖纪录
	 * 
	 * @param userId
	 * @param evenId
	 * @param evenId
	 * @return
	 */
	public LotteryRecord getByUserIdEvenIdAndRound(@Param("userId") long userId, @Param("eventId") long evenId,
			@Param("round") int round);

	public List<LotteryRecord> getByUserIdEvenIdList(@Param("userId") long userId, @Param("eventId") long evenId);

	/**
	 * 保存抽奖记录
	 * 
	 * @param e
	 */
	public void saveLotteryRecord(LotteryRecord e);

}
