/**
 * 
 */
package com.hhz.tms.admin.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhz.tms.admin.dao.sys.DeptDao;
import com.hhz.tms.entity.sys.Dept;

/**
 * 机构管理
 * @author huangjian
 * 
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class DeptService {
	@Autowired
	private DeptDao deptDao;

	public List<Dept> findAll() {
		Sort sort = new Sort(Direction.ASC, "dispOrderNo");
		List<Dept> depts = (List<Dept>) deptDao.findAll(sort);
		return depts;
	}

	public List<Dept> findAllEnable() {
		List<Dept> depts = deptDao.findByEnableFlgOrderByDispOrderNoAsc(true);
		return depts;
	}

	public Dept getDept(Long id) {
		return deptDao.findOne(id);
	}

	@Transactional(readOnly = false)
	public Dept save(Dept entity) {
		if (entity.getParent() != null && (entity.getParent().getId() == null || entity.getParent().getId() == 0)) {
			entity.setParent(null);
		} else if (entity.getParent() != null) {
			Dept parent = deptDao.findOne(entity.getParent().getId());
			entity.setParent(parent);
		}
		return deptDao.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		deptDao.delete(id);
	}

}
