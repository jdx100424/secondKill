package com.maoshen.secondkill.domain;

import java.util.Date;

import com.maoshen.base.entity.BaseEntity;

public class Event extends BaseEntity{

	/**
	 * 活动名称
	 */
	private String name;

	/**
	 * 活动描述
	 */
	private String description;

	/**
	 * 创建时间
	 */
	private Date created;

	/**
	 * 开始时间
	 */
	private Date startDate;

	/**
	 * 结束时间
	 */
	private Date endDate;

	/**
	 * 状态:0:下线，1:上线
	 */
	private Integer status;


	/**
	 * 获取活动名称
	 *
	 * @return name - 活动名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置活动名称
	 *
	 * @param name
	 *            活动名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取活动描述
	 *
	 * @return description - 活动描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置活动描述
	 *
	 * @param description
	 *            活动描述
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * 获取开始时间
	 *
	 * @return start_date - 开始时间
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置开始时间
	 *
	 * @param startDate
	 *            开始时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获取结束时间
	 *
	 * @return end_date - 结束时间
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置结束时间
	 *
	 * @param endDate
	 *            结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取状态:0:下线，1:上线
	 *
	 * @return status - 状态:0:下线，1:上线
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置状态:0:下线，1:上线
	 *
	 * @param status
	 *            状态:0:下线，1:上线
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean isAvailable() {
		Date date = new Date();
		return status == 1 && date.after(getStartDate()) && date.before(getEndDate());
	}
}
