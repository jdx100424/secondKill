package com.maoshen.secondkill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.maoshen.secondkill.domain.RedPackageUser;

@Repository
public interface RedPackageUserDao {
	public List<RedPackageUser> getDrawRecord(@Param("groupId")Long groupId,@Param("userId")Long userId); 

	public Long insertDrawRecord(RedPackageUser RedPackageUser); 
}
