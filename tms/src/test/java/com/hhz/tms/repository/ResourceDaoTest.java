package com.hhz.tms.repository;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.hhz.tms.admin.dao.sys.ResourceDao;
import com.hhz.tms.entity.sys.Resource;

@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
public class ResourceDaoTest extends SpringTransactionalTestCase {

	@Autowired
	private ResourceDao resourceDao;

	@Test
	public void findByEnableFlg() throws Exception {
		List<Resource> list = resourceDao.findByEnableFlgOrderByUrlAsc(true);
		logger.info(list.size());
		List<Resource> list2 = (List<Resource>)resourceDao.findAll();
		logger.info(list2.size());
	}
}
