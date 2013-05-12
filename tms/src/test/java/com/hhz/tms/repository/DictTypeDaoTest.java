package com.hhz.tms.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.hhz.tms.admin.dao.app.DictTypeDao;

@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
public class DictTypeDaoTest extends SpringTransactionalTestCase {

	@Autowired
	private DictTypeDao dictTypeDao;

	@Test
	public void Test() throws Exception {
		Long flag = dictTypeDao.existDictTypeCd("1", "2");
		logger.info(flag);
	}
}
