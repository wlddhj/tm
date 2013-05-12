/**
 * 
 */
package com.hhz.tms.admin.dao.sys;

import java.util.List;

import com.hhz.tms.dao.BaseDao;
import com.hhz.tms.entity.sys.Menu;
import com.hhz.tms.entity.sys.Resource;

/**
 * 菜单dao
 * @author huangjian
 * 
 */
public interface MenuDao extends BaseDao<Menu>{
	public List<Menu> findByEnableFlgOrderByMenuNameAsc(boolean enableFlg);
}
