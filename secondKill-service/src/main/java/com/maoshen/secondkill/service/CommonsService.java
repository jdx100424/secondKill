package com.maoshen.secondkill.service;

import java.util.List;

import com.maoshen.secondkill.service.vo.LotteryRecordDto;

/**
 * 常规抽奖
 * 
 * @author jdx
 *
 */
public interface CommonsService {
	/**
	 * 抢票
	 * 
	 * @param userId
	 * @return
	 */
	public LotteryRecordDto draw(Long userId) throws Exception;

	/**
	 * 查询指定用户中奖记录
	 * 
	 * @return
	 */
	public List<LotteryRecordDto> getRecordByUserId(Long userId);
}
