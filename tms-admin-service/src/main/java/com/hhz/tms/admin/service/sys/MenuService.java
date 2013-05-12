/**
 * 
 */
package com.hhz.tms.admin.service.sys;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhz.tms.admin.dao.sys.MenuDao;
import com.hhz.tms.admin.dao.sys.PermissionDao;
import com.hhz.tms.entity.sys.Menu;
import com.hhz.tms.entity.sys.Permission;
import com.hhz.tms.util.WebUtil;

/**
 * @author huangjian
 * 
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class MenuService {
	@Autowired
	private MenuDao menuDao;

	@Autowired
	private PermissionDao permissionDao;
	
	public List<Menu> findAll() {
		Sort sort = new Sort(Direction.ASC, "menuName");
		List<Menu> menus = (List<Menu>) menuDao.findAll(sort);
		return menus;
	}
	public List<Menu> findAllEnable() {
		List<Menu> menus =menuDao.findByEnableFlgOrderByMenuNameAsc(true);
		return menus;
	}

	public Page<Menu> findPage(Map<String, Object> searchParams, PageRequest pageRequest) {
		Specification<Menu> spec = WebUtil.buildSpecification(searchParams, Menu.class);
		return menuDao.findAll(spec, pageRequest);
	}

	public Menu getMenu(Long id) {
		return menuDao.findOne(id);
	}
	/** 保存资源-权限关联关系 */
	@Transactional(readOnly = false)
	public void savePerms(Long id, String permids) {
		String[] ids = permids.split(",");
		Menu menu = getMenu(id);
		// 删除不存在的记录
		List<Permission> permissions = menu.getPermissions();
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
					menu.getPermissions().add(permission);
				}
			}
		}
		menuDao.save(menu);
	}
	@Transactional(readOnly = false)
	public void save(Menu entity) {
		if (entity.getParent()!=null && (entity.getParent().getId() == null || entity.getParent().getId() == 0)) {
			entity.setParent(null);
		}else if (entity.getParent() != null) {
			Menu parent=menuDao.findOne(entity.getParent().getId());
			entity.setParent(parent);
		}
		menuDao.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		menuDao.delete(id);
	}

}
