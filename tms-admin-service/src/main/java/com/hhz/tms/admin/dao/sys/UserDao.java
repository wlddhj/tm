package com.hhz.tms.admin.dao.sys;

import org.springframework.data.jpa.repository.Query;

import com.hhz.tms.entity.sys.User;
import com.hhz.tms.util.dao.BaseDao;

public interface UserDao extends BaseDao<User> {
	User findByLoginName(String loginName);
	@Query(value="select count(*) from User u where loginName=?1 and loginName <> ?2")
	public Long existLoginName(String loginName, String loginNameOld);
}
