package com.hhz.tms.repository;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.hhz.tms.admin.dao.sys.PermissionDao;
import com.hhz.tms.entity.sys.Permission;

@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
public class PermissionDaoTest extends SpringTransactionalTestCase {

	@Autowired
	private PermissionDao permissionDao;

	@Test
	public void findAllOrderByPermCdAsc() throws Exception {
		Sort sort=new Sort(Direction.ASC, "permCd");
		List<Permission> list = (List<Permission>)permissionDao.findAll(sort);
		logger.info(list.size());
	}
}
