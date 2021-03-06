package com.maoshen.secondkill.domain;

import java.util.Date;

import com.maoshen.base.entity.BaseEntity;

public class RedPackageUser extends BaseEntity {
	private Long userId;
	private Long groupId;
	
	private Double money;
	
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	/**
	 * 创建时间
	 */
	private Date created;
	
	/**
	 * 更新时间戳，精确到毫秒
	 */
	private Long updated;
	
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

}
