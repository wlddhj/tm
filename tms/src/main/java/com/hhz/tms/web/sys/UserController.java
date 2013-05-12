package com.hhz.tms.web.sys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hhz.tms.admin.service.sys.DeptService;
import com.hhz.tms.admin.service.sys.RoleService;
import com.hhz.tms.admin.service.sys.UserService;
import com.hhz.tms.entity.sys.Dept;
import com.hhz.tms.entity.sys.Role;
import com.hhz.tms.entity.sys.User;
import com.hhz.tms.util.EasyTreeNode;
import com.hhz.tms.util.EasyTreeUtil;
import com.hhz.tms.util.JsonUtil;
import com.hhz.tms.util.RenderUtil;

/**
 * 用户管理
 * 
 * @author huangjian
 * 
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "")
	public String index(Model model) {
		return "admin/sys/user";
	}

	/** 用户分配 */
	@RequestMapping(value = "assign")
	public String assign(Model model) {
		return "admin/sys/user-assign";
	}

	@RequestMapping(value = "role/{id}")
	public void role(@PathVariable("id") Long id, HttpServletResponse response) {
		User user = userService.getEntity(id);
		List<Role> roles = roleService.findAll();
		EasyTreeNode treeNode = EasyTreeUtil.getPermRoleTree(roles, user.getRoles());
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(treeNode);
		RenderUtil.renderJson(list, response);
	}

	@RequestMapping(value = "saveRoles/{id}")
	public void saveRoles(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
		String roleids = request.getParameter("roleids");
		userService.saveRoles(id, roleids);
		JsonUtil.renderSuccess("保存成功", response);
	}

	// 初始化树数据
	@RequestMapping(value = "initTree")
	public void initTree(HttpServletResponse response) {
		List<Dept> depts = deptService.findAllEnable();
		EasyTreeNode nodes = EasyTreeUtil.getDeptTree(depts, true);
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(nodes);
		RenderUtil.renderJson(list, response);
	}

	@RequestMapping(value = "create/{deptId}", method = RequestMethod.GET)
	public String create(@PathVariable("deptId") Long deptId, Model model) {
		User user = new User(deptId);
		user.setId(0l);
		model.addAttribute("user", user);
		return "admin/sys/user-detail";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, HttpServletResponse response) {
		userService.delete(id);
		// JsonUtil.renderSuccess("保存成功", response);
		return "redirect:/admin/user/";
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		User user = userService.getEntity(id);
		model.addAttribute("user", user);
		return "admin/sys/user-detail";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("preload") User user, @RequestParam("deptId") Long deptId, HttpServletResponse response) {
		String result = "success";
		Dept dept=new Dept();
		dept.setId(deptId);
		user.setDept(dept);
		userService.save(user);
		result = result + ":" + String.valueOf(user.getId());
		RenderUtil.renderText(result, response);
		return null;
	}

	@RequestMapping(value = "changePwd", method = RequestMethod.POST)
	public void changePwd(@Valid @ModelAttribute("preload") User user, @RequestParam("oldPwd") String oldPwd,
			@RequestParam("newPwd") String newPwd, HttpServletResponse response) {
		if (!userService.isPwdEqual(oldPwd, user)){
			RenderUtil.renderText("error", response);
			return;
		}
		user.setPlainPassword(newPwd);
		String result = "success";
		userService.save(user);
		RenderUtil.renderText(result, response);
	}

	/**
	 * Ajax请求校验loginName是否唯一。
	 */
	@RequestMapping(value = "checkLoginName")
	public void checkLoginName(@RequestParam("loginName") String loginName,
			@RequestParam("oldLoginName") String oldLoginName, HttpServletResponse response) {
		if (userService.existDictTypeCd(loginName, oldLoginName)) {
			RenderUtil.renderText("false", response);
		} else {
			RenderUtil.renderText("true", response);
		}
	}

	/**
	 * 使用@ModelAttribute, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 */
	@ModelAttribute("preload")
	public User getEntity(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			if (id > 0)
				return userService.getEntity(id);
			else
				return new User();
		}
		return null;
	}

}
