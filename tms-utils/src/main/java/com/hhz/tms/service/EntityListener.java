package com.hhz.tms.service;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Auditable;

public class EntityListener implements InitializingBean {
	private final Log logger = LogFactory.getLog(getClass());
	private static final String UPDATEDDATE = "updatedDate";
	private static final String CREATEDDATE = "createdDate";

	@PrePersist
	public void touchForCreate(Object entity) {
		logger.debug(entity);
		Date now = new Date();
		try {
			// 创建时间
			Date createdDate = (Date) PropertyUtils.getProperty(entity, CREATEDDATE);
			if (createdDate == null) {
				PropertyUtils.setProperty(entity, CREATEDDATE, now);
			}
			// 更新时间
			PropertyUtils.setProperty(entity, UPDATEDDATE, now);
		} catch (Exception e) {
			logger.warn("save createdDate/updatedDate error!", e);
		}
	}

	/**
	 * Sets modification and creation date and auditor on the target object in
	 * case it implements {@link Auditable} on update events.
	 * 
	 * @param target
	 */
	@PreUpdate
	public void touchForUpdate(Object entity) throws Throwable {
		logger.debug(entity);
		Date now = new Date();
		try {
			// 更新时间
			PropertyUtils.setProperty(entity, UPDATEDDATE, now);
		} catch (Exception e) {
			logger.warn("save updatedDate error!", e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

}
