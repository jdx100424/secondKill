package com.maoshen.secondkill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.maoshen.secondkill.domain.RedPackage;

@Repository
public interface RedPackageDao {
	/**
	 * 创建红包
	 * @param list
	 */
	public void createRedPackage(List<RedPackage> list);
	
	public List<RedPackage> getRedPackageListByGroupId(@Param("groupId") long groupId);
	
	/**
	 * 根据指定活动id获取对应时间内及对应轮数的奖品库存
	 * 
	 * @param evenId
	 * @param round
	 * @param current
	 * @return
	 */
	public List<RedPackage> getByEvenIdAndRoundBetweenBeignDateAndEndDate(@Param("eventId") long eventId,
			@Param("round") int ronud, @Param("current") Date current);

	/**
	 * 扣减奖品库存
	 * 
	 * @param id
	 * @param updated
	 * @return
	 */
	public int decreaseRedPackageStock(@Param("id") long id, @Param("updated") long updated);

	/**
	 * 根据活动id清除活动奖品
	 * 
	 * @param eventId
	 */
	public void deleteRedPackageByEventId(@Param("eventId") long eventId);

	public int decreaseRedPackageStockByCode(@Param("code") long code, @Param("eventId") long eventId,
			@Param("updated") long updated);

	/**
	 * 获取可用奖品列表
	 * 
	 * @param eventId
	 * @return
	 */
	public List<RedPackage> getRedPackageListByEventId(long eventId);

	/**
	 * 获取可用奖品列表
	 * 
	 * @param eventId
	 * @return
	 */
	public List<RedPackage> getRedPackageListHasStockByEventId(long eventId);
}
