/**
 * 
 */
package com.hhz.tms.admin.dao.sys;

import java.util.List;

import com.hhz.tms.dao.BaseDao;
import com.hhz.tms.entity.sys.Resource;

/**
 * @author huangjian
 * 
 */
public interface ResourceDao extends BaseDao<Resource> {
	public List<Resource> findByEnableFlgOrderByUrlAsc(boolean enableFlg);
}
