package com.maoshen.secondkill.request;

import java.io.Serializable;
import java.util.List;

public class RedPackageRequest implements Serializable{
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



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String redPackageName;
	private Integer redPackageCount;
	private Integer money;
	//0,yes,1,no
	private Integer isAllSame; 
	private List<Long> userIdList;
	public String getRedPackageName() {
		return redPackageName;
	}
	public void setRedPackageName(String redPackageName) {
		this.redPackageName = redPackageName;
	}
	public Integer getRedPackageCount() {
		return redPackageCount;
	}
	public void setRedPackageCount(Integer redPackageCount) {
		this.redPackageCount = redPackageCount;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getIsAllSame() {
		return isAllSame;
	}
	public void setIsAllSame(Integer isAllSame) {
		this.isAllSame = isAllSame;
	}
	public List<Long> getUserIdList() {
		return userIdList;
	}
	public void setUserIdList(List<Long> userIdList) {
		this.userIdList = userIdList;
	}
}
