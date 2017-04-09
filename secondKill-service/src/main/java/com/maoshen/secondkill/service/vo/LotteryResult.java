package com.maoshen.secondkill.service.vo;

import java.io.Serializable;
import java.util.Random;

public class LotteryResult implements Serializable {
	public static void main(String []s){
		System.out.println(new Random().nextInt(1));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 557991973314552550L;

	private String code;

	private String prizeName;

	// 抽奖类型
	private Integer type;

	// 自定义抽奖类型
	private Integer customType;

	// 抽奖记录
	private LotteryRecordDto lotteryRecordDto;

	private boolean isWin = false;

	public static final LotteryResult FAIL = new LotteryResult();

	private LotteryResult(String code, String prizeName) {
		this.isWin = true;
		this.code = code;
		this.prizeName = prizeName;
	}

	public LotteryResult() {
	}

	public static final LotteryResult result(String code, String prizeName) {
		return new LotteryResult(code, prizeName);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCustomType() {
		return customType;
	}

	public void setCustomType(Integer customType) {
		this.customType = customType;
	}

	public LotteryRecordDto getLotteryRecordDto() {
		return lotteryRecordDto;
	}

	public void setLotteryRecordDto(LotteryRecordDto lotteryRecordDto) {
		this.lotteryRecordDto = lotteryRecordDto;
	}

}
