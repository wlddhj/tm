/**
 * 
 */
package com.hhz.tms.util.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.hhz.tms.entity.IdEntity;
import com.hhz.tms.util.WebUtil;
import com.hhz.tms.util.dao.BaseDao;

/**
 * @author huangjian
 * 
 */
@Transactional(readOnly = true)
public abstract class BaseService<T extends IdEntity> {
	public abstract BaseDao<T> getBaseDao();

	public abstract Class<T> getEntityClazz();

	public List<T> findAll() {
		List<T> list = (List<T>) getBaseDao().findAll();
		return list;
	}

	public Page<T> findPage(Map<String, Object> searchParams, PageRequest pageRequest) {
		Specification<T> spec = WebUtil.buildSpecification(searchParams, getEntityClazz());
		Page<T> page = getBaseDao().findAll(spec, pageRequest);
		return page;
	}

	public T getEntity(Long id) {
		return getBaseDao().findOne(id);
	}

	@Transactional(readOnly = false)
	public T save(T entity) {
		return getBaseDao().save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		getBaseDao().delete(id);
	}

	@Transactional(readOnly = false)
	public void saveBatch(List<T> insertedRecords, List<T> updatedRecords, List<T> deletedRecords) {
		for (T entity : insertedRecords) {
			save(entity);
		}
		for (T entity : updatedRecords) {
			save(entity);
		}
		for (T entity : deletedRecords) {
			delete(entity.getId());
		}
	}
}
