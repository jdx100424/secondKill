package com.maoshen.secondkill.service.vo;

import java.io.Serializable;
import java.util.Date;

public class RedPackageUserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
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
