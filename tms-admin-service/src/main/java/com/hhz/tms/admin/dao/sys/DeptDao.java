/**
 * 
 */
package com.hhz.tms.admin.dao.sys;

import java.util.List;

import com.hhz.tms.entity.sys.Dept;
import com.hhz.tms.util.dao.BaseDao;

/**
 * @author huangjian
 * 
 */
public interface DeptDao extends BaseDao<Dept>{
	public List<Dept> findByEnableFlgOrderByDispOrderNoAsc(Boolean flag);
}
