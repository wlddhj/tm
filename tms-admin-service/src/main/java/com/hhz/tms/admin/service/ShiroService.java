/**
 * 
 */
package com.hhz.tms.admin.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhz.tms.admin.service.sys.MenuService;
import com.hhz.tms.admin.service.sys.ResourceService;
import com.hhz.tms.entity.sys.Menu;
import com.hhz.tms.entity.sys.Permission;
import com.hhz.tms.entity.sys.Resource;
import com.hhz.tms.util.CollectionUtil;

/**
 * @author huangjian
 * 
 */
@Component
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
public class ShiroService {
	/**
	 * 默认premission字符串
	 */
	public static final String PREMISSION_STRING = "perms[{0}]";
	public static final String ROLE_STRING = "roles[{0}]";
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private MenuService menuService;
	private void initResource(Ini.Section section){
		List<Resource> list = (List<Resource>) resourceService.findAllEnable();
		// 循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,
		// 里面的键就是链接URL,值就是存在什么条件才能访问该链接
		for (Iterator<Resource> it = list.iterator(); it.hasNext();) {
			Resource resource = it.next();
			// 如果不为空值添加到section中
			List<String> perms = new ArrayList<String>();
			if (StringUtils.isNotEmpty(resource.getUrl())) {
				for (Permission permission : resource.getPermissions()) {
					perms.add(permission.getPermCd());
				}
			}
			String strPerms = CollectionUtil.array2String(perms);
			if (StringUtils.isNotBlank(strPerms)) {
				section.put(resource.getUrl(), MessageFormat.format(PREMISSION_STRING, strPerms));
			}
		}
	}
	private void initMenu(Ini.Section section){
		List<Menu> menus = menuService.findAllEnable();
		for (Menu menu : menus) {
			List<String> perms = new ArrayList<String>();
			if (StringUtils.isNotEmpty(menu.getUrl())) {
				for (Permission permission : menu.getPermissions()) {
					perms.add(permission.getPermCd());
				}
			}
			String strPerms = CollectionUtil.array2String(perms);
			if (StringUtils.isNotBlank(strPerms)) {
				section.put(menu.getUrl(), MessageFormat.format(PREMISSION_STRING, strPerms));
			}
		}
	}
	public void initSection(Ini.Section section) {
		initMenu(section);
		initResource(section);
	}

}
