package com.maoshen.secondkill.service;

import java.util.List;

import com.maoshen.component.db.DataSource;
import com.maoshen.secondkill.domain.LotteryRecord;
import com.maoshen.secondkill.domain.Prize;

/**
 * 抽奖记录业务SERVICE
 * @author jdx
 *
 */
public interface LotteryRecordService {
	/**
	 * 保存抽奖或者抢票的记录
	 * @param userId
	 * @param eventId
	 * @param prize
	 * @return
	 */
	@DataSource("master")
	public LotteryRecord saveLotteryRecord(long userId, long eventId, Prize prize);

	/**
	 * 查询抽奖抢票记录
	 * @param userId
	 * @param eventId
	 * @return
	 */
	@DataSource("slave")
	public List<LotteryRecord> getLotteryRecordList(Long userId, Long eventId);
}
