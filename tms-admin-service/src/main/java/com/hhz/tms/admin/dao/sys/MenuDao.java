/**
 * 
 */
package com.hhz.tms.admin.dao.sys;

import java.util.List;

import com.hhz.tms.entity.sys.Menu;
import com.hhz.tms.entity.sys.Resource;
import com.hhz.tms.util.dao.BaseDao;

/**
 * 菜单dao
 * @author huangjian
 * 
 */
public interface MenuDao extends BaseDao<Menu>{
	public List<Menu> findByEnableFlgOrderByMenuNameAsc(boolean enableFlg);
}
