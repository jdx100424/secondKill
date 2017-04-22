package com.maoshen.secondkill.service;

import java.math.BigDecimal;
import java.util.List;

import com.maoshen.secondkill.service.vo.LotteryRecordDto;

/**
 * 发指定的红包给指定的人
 * 
 * @author jdx
 *
 */
public interface RedPackageService {
	/**
	 * 生成红包，确定抢红包的USERID集合和此红包的GROUPID,红包放在redPackage表里，以GROUPID分开，每个红包有指定的钱，概率100%，
	 * 
	 * @param redPackageCount，红包数
	 * @param money，总金额
	 * @param isAllSame，红包里面的前是否一致
	 * @param userIdList，参与抢的用户ID集合
	 * @return 事件ID
	 * @throws Exception
	 */
	public Long create(String redPackageName,int redPackageCount,Integer money,boolean isAllSame,List<Long> userIdList) throws Exception;
	/**
	 * 抢票
	 * 
	 * @param userId,
	 * @param eventId,
	 * @return
	 */
	public LotteryRecordDto draw(Long userId,Long eventId) throws Exception;

	/**
	 * 查询指定用户中奖记录
	 * 
	 * @return
	 */
	public List<LotteryRecordDto> getRecordByUserId(Long userId,Long eventId);
}
