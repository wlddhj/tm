/**
 * 
 */
package com.hhz.tms.web.sys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hhz.tms.admin.service.sys.MenuService;
import com.hhz.tms.admin.service.sys.PermissionService;
import com.hhz.tms.admin.service.sys.ResourceService;
import com.hhz.tms.admin.service.sys.RoleService;
import com.hhz.tms.entity.sys.Menu;
import com.hhz.tms.entity.sys.Permission;
import com.hhz.tms.entity.sys.Resource;
import com.hhz.tms.entity.sys.Role;
import com.hhz.tms.util.EasyTreeNode;
import com.hhz.tms.util.EasyTreeUtil;
import com.hhz.tms.util.JsonUtil;
import com.hhz.tms.util.RenderUtil;
import com.hhz.tms.util.service.BaseService;
import com.hhz.tms.web.SingleCrudControll;

/**
 * @author huangjian
 * 
 */
@Controller
@RequestMapping(value = "/admin/permission")
public class PermissionController extends SingleCrudControll<Permission> {
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "")
	public String index(Model model) {
		return "admin/sys/permission";
	}

	@Override
	public BaseService<Permission> getService() {
		// TODO Auto-generated method stub
		return permissionService;
	}

	/** 权限分配 */
	@RequestMapping(value = "assign")
	public String assign(Model model) {
		return "admin/sys/permission-assign";
	}

	@RequestMapping(value = "all")
	public void all(HttpServletResponse response) {
		List<Permission> permissions = permissionService.findAllPerm();
		JsonUtil.renderListJson(response, permissions);
	}

	@RequestMapping(value = "menu/{id}")
	public void menu(@PathVariable("id") Long id, HttpServletResponse response) {
		Permission permission = permissionService.getEntity(id);
		List<Menu> menus = menuService.findAll();
		EasyTreeNode treeNode = EasyTreeUtil.getCheckMenuTree(menus, permission.getMenus());
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(treeNode);
		RenderUtil.renderJson(list, response);
	}

	@RequestMapping(value = "role/{id}")
	public void role(@PathVariable("id") Long id, HttpServletResponse response) {
		Permission permission = permissionService.getEntity(id);
		List<Role> roles = roleService.findAll();
		EasyTreeNode treeNode = EasyTreeUtil.getPermRoleTree(roles, permission.getRoles());
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(treeNode);
		RenderUtil.renderJson(list, response);
	}

	@RequestMapping(value = "resource/{id}")
	public void resource(@PathVariable("id") Long id, HttpServletResponse response) {
		Permission permission = permissionService.getEntity(id);
		List<Resource> resources = resourceService.findAll();
		EasyTreeNode treeNode = EasyTreeUtil.getPermResourceTree(resources, permission.getResources());
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(treeNode);
		RenderUtil.renderJson(list, response);
	}

	@RequestMapping(value = "saveAssign/{id}")
	public void saveAssign(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
		String menuids = request.getParameter("menuids");
		String resourceids = request.getParameter("resourceids");
		String roleids = request.getParameter("roleids");
		permissionService.saveAssign(id, menuids, resourceids, roleids);
//		menu(id, response);
		JsonUtil.renderSuccess("保存成功", response);
	}
}
