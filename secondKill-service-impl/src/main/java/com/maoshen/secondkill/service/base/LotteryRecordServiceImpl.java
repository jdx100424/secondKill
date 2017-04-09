package com.maoshen.secondkill.service.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maoshen.secondkill.dao.LotteryRecordDao;
import com.maoshen.secondkill.domain.LotteryRecord;
import com.maoshen.secondkill.domain.Prize;
import com.maoshen.secondkill.service.LotteryRecordService;

@Service("lotteryRecordServiceImpl")
public class LotteryRecordServiceImpl implements LotteryRecordService{
	@Autowired
	private LotteryRecordDao lotteryRecordDao;

	public LotteryRecord saveLotteryRecord(long userId, long eventId, Prize prize) {
		LotteryRecord lotteryRecord = new LotteryRecord();
		Date nowDate = new Date();
		lotteryRecord.setCreated(nowDate);
		lotteryRecord.setEventId(eventId);
		lotteryRecord.setStatus(0);
		lotteryRecord.setUpdated(nowDate.getTime());
		lotteryRecord.setUserId(userId);
		lotteryRecord.setRound(0);
		if (prize != null) {
			lotteryRecord.setExpireTime(prize.getExpireTime());
			lotteryRecord.setPrizeCode(prize.getCode());
			lotteryRecord.setPrizeId(prize.getId());
			lotteryRecord.setStatus(1);
			lotteryRecord.setType(prize.getType());
			lotteryRecord.setCustomType(prize.getCustomType());
			lotteryRecord.setPrizeName(prize.getName());
		}
		lotteryRecordDao.saveLotteryRecord(lotteryRecord);
		return lotteryRecord;
	}

	public List<LotteryRecord> getLotteryRecordList(Long userId, Long eventId) {
		List<LotteryRecord> list = lotteryRecordDao.getByUserIdEvenIdList(userId, eventId);
		if (list == null) {
			list = new ArrayList<LotteryRecord>();
		}
		return list;
	}
}
