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

import com.hhz.tms.admin.service.sys.DeptService;
import com.hhz.tms.admin.service.sys.PermissionService;
import com.hhz.tms.admin.service.sys.RoleService;
import com.hhz.tms.entity.sys.Dept;
import com.hhz.tms.entity.sys.Permission;
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
@RequestMapping(value = "/admin/role")
public class RoleController extends SingleCrudControll<Role>{
	@Autowired
	private RoleService roleService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private PermissionService permissionService;
	@RequestMapping(value = "")
	public String index(Model model) {
		return "admin/sys/role";
	}

	@Override
	public BaseService<Role> getService() {
		// TODO Auto-generated method stub
		return roleService;
	}
	
	/** 角色分配 */
	@RequestMapping(value = "assign")
	public String assign(Model model) {
		return "admin/sys/role-assign";
	}
	@RequestMapping(value = "all")
	public void all(HttpServletResponse response) {
		List<Role> roles = roleService.findAll();
		JsonUtil.renderListJson(response, roles);
	}

	@RequestMapping(value = "permission/{roleId}")
	public void permission(@PathVariable("roleId") Long roleId, HttpServletResponse response) {
		Role role = roleService.getEntity(roleId);
		List<Permission> permissions = permissionService.findAll();
		EasyTreeNode treeNode = EasyTreeUtil.gePermCheckedTree(permissions, role.getPermissions());
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(treeNode);
		RenderUtil.renderJson(list, response);
	}
	
	@RequestMapping(value = "user/{roleId}")
	public void user(@PathVariable("roleId") Long roleId,HttpServletResponse response) {
		Role role = roleService.getEntity(roleId);
		List<Dept> depts = deptService.findAllEnable();
		EasyTreeNode nodes = EasyTreeUtil.getDeptTree(depts, true,role.getUsers());
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(nodes);
		RenderUtil.renderJson(list, response);
	}
	
	@RequestMapping(value = "saveAssign/{id}")
	public void saveAssign(@PathVariable("id") Long id, HttpServletRequest request,HttpServletResponse response) {
		String userids=request.getParameter("userids");
		String permids=request.getParameter("permids");
		roleService.saveUsers(id, userids);
		roleService.savePerms(id, permids);
		JsonUtil.renderSuccess("保存成功", response);
	}
}
