package com.maoshen.secondkill.domain;

import com.maoshen.base.entity.BaseEntity;

public class RedPackageUser extends BaseEntity {
	private Long userId;
	private Long groupId;
	
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
