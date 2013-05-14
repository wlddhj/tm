/**
 * 
 */
package com.hhz.tms.admin.service.sys;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhz.tms.admin.dao.sys.PermissionDao;
import com.hhz.tms.admin.dao.sys.ResourceDao;
import com.hhz.tms.entity.sys.Menu;
import com.hhz.tms.entity.sys.Permission;
import com.hhz.tms.entity.sys.Resource;
import com.hhz.tms.util.dao.BaseDao;
import com.hhz.tms.util.service.BaseService;

/**
 * @author huangjian
 * 
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class ResourceService extends BaseService<Resource> {
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private PermissionDao permissionDao;
	public List<Resource> findAllEnable() {
		return resourceDao.findByEnableFlgOrderByUrlAsc(true);
	}

	/** 保存资源-权限关联关系 */
	@Transactional(readOnly = false)
	public void savePerms(Long id, String permids) {
		String[] ids = permids.split(",");
		Resource resource = getEntity(id);
		// 删除不存在的记录
		List<Permission> permissions = resource.getPermissions();
		for (Iterator<Permission> it = permissions.iterator(); it.hasNext();) {
			Permission permission = it.next();
			if (!ArrayUtils.contains(ids, String.valueOf(permission.getId()))) {
				// 删除不存在的记录
				it.remove();
			} else {
				// 已有的记录不动
				ids = ArrayUtils.removeElement(ids, String.valueOf(permission.getId()));
			}
		}
		// 新增新记录
		for (String strId : ids) {
			if (StringUtils.isNotBlank(strId)) {
				Long id_new = Long.valueOf(strId);
				if (id_new > 0) {
					Permission permission = permissionDao.findOne(id_new);
					permission.setId(id_new);
					resource.getPermissions().add(permission);
				}
			}
		}
		resourceDao.save(resource);
	}

	@Override
	public BaseDao<Resource> getBaseDao() {
		// TODO Auto-generated method stub
		return resourceDao;
	}

	@Override
	public Class<Resource> getEntityClazz() {
		// TODO Auto-generated method stub
		return Resource.class;
	}

}
