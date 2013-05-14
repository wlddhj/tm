/**
 * 
 */
package com.hhz.tms.admin.service.sys;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhz.tms.admin.dao.sys.MenuDao;
import com.hhz.tms.admin.dao.sys.PermissionDao;
import com.hhz.tms.admin.dao.sys.ResourceDao;
import com.hhz.tms.admin.dao.sys.RoleDao;
import com.hhz.tms.entity.sys.Menu;
import com.hhz.tms.entity.sys.Permission;
import com.hhz.tms.entity.sys.Resource;
import com.hhz.tms.entity.sys.Role;
import com.hhz.tms.util.dao.BaseDao;
import com.hhz.tms.util.service.BaseService;

/**
 * @author huangjian
 * 
 */
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class PermissionService extends BaseService<Permission> {
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	@Override
	public BaseDao<Permission> getBaseDao() {
		// TODO Auto-generated method stub
		return permissionDao;
	}

	public List<Permission> findAllPerm() {
		Sort sort = new Sort(Direction.ASC, "permCd");
		List<Permission> list = (List<Permission>) permissionDao.findAll(sort);
		return list;
	}
	@Transactional(readOnly = false)
	public void saveAssign(Long id, String menuids,String resourceids,String roleids) {
		Permission permission = getEntity(id);
		saveMenus(id, menuids, permission);
		saveResources(id, resourceids, permission);
		saveRoles(id, roleids, permission);
	}
	/** 保存资源-权限关联关系 */
	private void saveMenus(Long id, String menuids,Permission permission) {
		String[] ids = menuids.split(",");
		// 删除不存在的记录
		List<Menu> menus = permission.getMenus();
		for (Iterator<Menu> it = menus.iterator(); it.hasNext();) {
			Menu menu = it.next();
			if (!ArrayUtils.contains(ids, String.valueOf(menu.getId()))) {
				// 删除不存在的记录
				it.remove();
			} else {
				// 已有的记录不动
				ids = ArrayUtils.removeElement(ids, String.valueOf(menu.getId()));
			}
		}
		// 新增新记录
		for (String strId : ids) {
			if (StringUtils.isNotBlank(strId)) {
				Long id_new = Long.valueOf(strId);
				if (id_new > 0) {
					Menu menu =menuDao.findOne(id_new);
					menu.setId(id_new);
					permission.getMenus().add(menu);
				}
			}
		}
		permissionDao.save(permission);
	}
	private void saveResources(Long id, String resourceids,Permission permission) {
		String[] ids = resourceids.split(",");
		// 删除不存在的记录
		List<Resource> resources = permission.getResources();
		for (Iterator<Resource> it = resources.iterator(); it.hasNext();) {
			Resource resource = it.next();
			if (!ArrayUtils.contains(ids, String.valueOf(resource.getId()))) {
				// 删除不存在的记录
				it.remove();
			} else {
				// 已有的记录不动
				ids = ArrayUtils.removeElement(ids, String.valueOf(resource.getId()));
			}
		}
		// 新增新记录
		for (String strId : ids) {
			if (StringUtils.isNotBlank(strId)) {
				Long id_new = Long.valueOf(strId);
				if (id_new > 0) {
					Resource resource = resourceDao.findOne(id_new);
					resource.setId(id_new);
					permission.getResources().add(resource);
				}
			}
		}
		permissionDao.save(permission);
	}
	private void saveRoles(Long id, String roleids,Permission permission) {
		String[] ids = roleids.split(",");
		// 删除不存在的记录
		List<Role> roles = permission.getRoles();
		for (Iterator<Role> it = roles.iterator(); it.hasNext();) {
			Role role = it.next();
			if (!ArrayUtils.contains(ids, String.valueOf(role.getId()))) {
				// 删除不存在的记录
				it.remove();
			} else {
				// 已有的记录不动
				ids = ArrayUtils.removeElement(ids, String.valueOf(role.getId()));
			}
		}
		// 新增新记录
		for (String strId : ids) {
			if (StringUtils.isNotBlank(strId)) {
				Long id_new = Long.valueOf(strId);
				if (id_new > 0) {
					Role role = roleDao.findOne(id_new);
					role.setId(id_new);
					permission.getRoles().add(role);
				}
			}
		}
		permissionDao.save(permission);
	}

	@Override
	public Class<Permission> getEntityClazz() {
		// TODO Auto-generated method stub
		return Permission.class;
	}

}
