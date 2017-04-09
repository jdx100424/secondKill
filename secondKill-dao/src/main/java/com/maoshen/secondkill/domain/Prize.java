package com.maoshen.secondkill.domain;

import java.util.Date;

import com.maoshen.base.entity.BaseEntity;

public class Prize extends BaseEntity {
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 库存
	 */
	private Integer stock;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 活动事件id
	 */
	private Long eventId;
	
	/**
	 * 开始抽奖时间
	 */
	private Date beginDate;
	
	/**
	 * 结束抽奖时间
	 */
	private Date endDate;
	
	/**
	 * 中奖概率
	 */
	private Double probability;
	
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
	 * 奖品编码
	 */
	private Integer code;
	
	/**
	 * 类型：0虚拟1现金红包2实物
	 */
	private Integer type;
	
	//每个活动自定义的类型
	private Integer customType;
	
	/**
	 * 奖券过期时间
	 */
	private Date expireTime;
	
    public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
	/**
	 * 获取名称
	 *
	 * @return name - 名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置名称
	 *
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取库存
	 *
	 * @return stock - 库存
	 */
	public Integer getStock() {
		return stock;
	}
	
	/**
	 * 设置库存
	 *
	 * @param stock
	 *            库存
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	/**
	 * 获取描述
	 *
	 * @return description - 描述
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 设置描述
	 *
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @param eventId
	 *            活动事件id
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	
	/**
	 * 获取开始抽奖时间
	 *
	 * @return begin_date - 开始抽奖时间
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	
	/**
	 * 设置开始抽奖时间
	 *
	 * @param beginDate
	 *            开始抽奖时间
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	 * 获取结束抽奖时间
	 *
	 * @return end_date - 结束抽奖时间
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * 设置结束抽奖时间
	 *
	 * @param endDate
	 *            结束抽奖时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * 获取中奖概率
	 *
	 * @return probability - 中奖概率
	 */
	public Double getProbability() {
		return probability;
	}
	
	/**
	 * 设置中奖概率
	 *
	 * @param probability
	 *            中奖概率
	 */
	public void setProbability(Double probability) {
		this.probability = probability;
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
	 * @param created
	 *            创建时间
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
	 * @param updated
	 *            更新时间戳，精确到毫秒
	 */
	public void setUpdated(Long updated) {
		this.updated = updated;
	}
	
	/**
	 * 获取第几轮抽奖
	 * 
	 * @return round 第几轮抽奖
	 */
	public Integer getRound() {
		return round;
	}
	
	/**
	 * 设置第几轮抽奖
	 * 
	 * @param round
	 *            第几轮抽奖
	 */
	public void setRound(Integer round) {
		this.round = round;
	}
	
	/**
	 * 获取奖品编码
	 * 
	 * @return code 奖品编码
	 */
	public Integer getCode() {
		return code;
	}
	
	/**
	 * 设置奖品编码
	 * 
	 * @param code
	 *            奖品编码
	 */
	public void setCode(Integer code) {
		this.code = code;
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
}
