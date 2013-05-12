package com.hhz.tms.admin.dao.sys;

import org.springframework.data.jpa.repository.Query;

import com.hhz.tms.dao.BaseDao;
import com.hhz.tms.entity.sys.User;

public interface UserDao extends BaseDao<User> {
	User findByLoginName(String loginName);
	@Query(value="select count(*) from User u where loginName=?1 and loginName <> ?2")
	public Long existLoginName(String loginName, String loginNameOld);
}
