package com.maoshen.secondkill.service.base;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.maoshen.secondkill.dao.PrizeDao;
import com.maoshen.secondkill.domain.LotteryRecord;
import com.maoshen.secondkill.domain.Prize;
import com.maoshen.secondkill.service.LotteryRecordService;
import com.maoshen.secondkill.service.LotteryService;
import com.maoshen.secondkill.service.vo.LotteryRecordDto;
import com.maoshen.secondkill.service.vo.LotteryResult;
import com.maoshen.secondkill.util.LotteryUtil;

@Service("lotteryServiceImpl")
public class LotteryServiceImpl implements LotteryService{

	@Autowired
	private PrizeDao prizeDao;

	@Autowired
	@Qualifier("lotteryRecordServiceImpl")
	private LotteryRecordService lotteryRecordService;

	@Transactional(propagation = Propagation.REQUIRED)
	public LotteryResult lottery(long userId, long eventId) {
		List<Prize> prizeList = prizeDao.getPrizeListByEventId(eventId);
		return lotteryAlgorithm(userId, eventId, prizeList);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public LotteryResult lotteryWithStockOne(long userId, long eventId,boolean isRecordFail) {
		List<Prize> prizeList = prizeDao.getPrizeListHasStockByEventId(eventId);
		if (prizeList == null || prizeList.size() <= 0) {
			return LotteryResult.FAIL;
		}
		LotteryResult result = lotteryAlgorithm(userId, eventId, prizeList, isRecordFail);
		if (result.isWin()) {
			return result;
		}
		return LotteryResult.FAIL;
	}
	
	private LotteryResult lotteryAlgorithm(long userId, long eventId, List<Prize> prizeList) {
		return lotteryAlgorithm(userId, eventId, prizeList, true);
	}

	private LotteryResult lotteryAlgorithm(long userId, long eventId, List<Prize> prizeList,
			boolean isNeedRecordFail) {
		if (prizeList != null && prizeList.size() > 0) {
			LotteryUtil<Prize> lottery = LotteryUtil.<Prize>newInstance();
			prizeList.forEach(p -> {
				lottery.add(p, p.getProbability());
			});
			Prize prize = lottery.lottery();
			if (prize != null) {// 抽中
				// 扣库存
				int num = prizeDao.decreasePrizeStock(prize.getId(),System.currentTimeMillis());
				if (num > 0) {
					LotteryRecord newLotteryRecordEntity = lotteryRecordService.saveLotteryRecord(userId, eventId,
							prize);
					LotteryResult lotteryResult = LotteryResult.result(String.valueOf(prize.getCode()),
							prize.getName());
					lotteryResult.setType(prize.getType());
					lotteryResult.setCustomType(prize.getCustomType());
					LotteryRecordDto LotteryRecordDto = new LotteryRecordDto();
					BeanUtils.copyProperties(newLotteryRecordEntity, LotteryRecordDto);
					lotteryResult.setLotteryRecordDto(LotteryRecordDto);
					return lotteryResult;
				}
			}
		}
		if (isNeedRecordFail) {
			lotteryRecordService.saveLotteryRecord(userId, eventId, null);
		}
		return LotteryResult.FAIL;
	}
}
