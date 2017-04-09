package com.maoshen.secondkill.domain;

import java.util.Date;

import com.maoshen.base.entity.BaseEntity;

public class LotteryRecord extends BaseEntity {
	/**
	 * 奖券过期时间
	 */
	private Date expireTime;

    /**
     * 奖品名称
     */

    private String prizeName;
    
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 活动事件id
     */
    private Long eventId;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间戳，精确到毫秒
     */
    private Long updated;

    /**
     * 第几轮抽奖
     */
    private Integer round;

    /**
     * 库存编码
     */
    private Integer prizeCode;

    /**
     * 状态:0-没中奖,1-中奖
     */
    private Integer status;

    /**
     * 库存ID
     */
    private Long prizeId;

	/**
	 * 类型：0虚拟1现金红包2实物
	 */
	private Integer type;
	
	//每个活动自定义的类型
	private Integer customType;
	
    public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取活动事件id
     *
     * @return event_id - 活动事件id
     */
    public Long getEventId() {
        return eventId;
    }

    /**
     * 设置活动事件id
     *
     * @param eventId 活动事件id
     */
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取更新时间戳，精确到毫秒
     *
     * @return updated - 更新时间戳，精确到毫秒
     */
    public Long getUpdated() {
        return updated;
    }

    /**
     * 设置更新时间戳，精确到毫秒
     *
     * @param updated 更新时间戳，精确到毫秒
     */
    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    /**
     * 获取第几轮抽奖
     *
     * @return round - 第几轮抽奖
     */
    public Integer getRound() {
        return round;
    }

    /**
     * 设置第几轮抽奖
     *
     * @param round 第几轮抽奖
     */
    public void setRound(Integer round) {
        this.round = round;
    }

    /**
     * 获取库存编码
     *
     * @return prize_code - 库存编码
     */
    public Integer getPrizeCode() {
        return prizeCode;
    }

    /**
     * 设置库存编码
     *
     * @param prizeCode 库存编码
     */
    public void setPrizeCode(Integer prizeCode) {
        this.prizeCode = prizeCode;
    }

    /**
     * 获取状态:0-没中奖,1-中奖
     *
     * @return status - 状态:0-没中奖,1-中奖
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态:0-没中奖,1-中奖
     *
     * @param status 状态:0-没中奖,1-中奖
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取库存ID
     *
     * @return prize_id - 库存ID
     */
    public Long getPrizeId() {
        return prizeId;
    }

    /**
     * 设置库存ID
     *
     * @param prizeId 库存ID
     */
    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
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

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	
	public LotteryRecord() {

	}
}
