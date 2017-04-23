package com.maoshen.secondkill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.maoshen.secondkill.domain.RedPackage;

@Repository
public interface RedPackageDao {
	/**
	 * 创建红包
	 * @param list
	 */
	public void createRedPackage(List<RedPackage> list);
	
	public List<RedPackage> getRedPackageListByGroupId(@Param("groupId") long groupId,@Param("eventId")Long eventId);
	
	public int drawRedPackage(@Param("id")Long id,@Param("updated")Long updated);
}
