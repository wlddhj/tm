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

import com.hhz.tms.admin.service.sys.MenuService;
import com.hhz.tms.admin.service.sys.PermissionService;
import com.hhz.tms.entity.sys.Menu;
import com.hhz.tms.entity.sys.Permission;
import com.hhz.tms.util.EasyTreeNode;
import com.hhz.tms.util.EasyTreeUtil;
import com.hhz.tms.util.RenderUtil;

/**
 * @author huangjian
 * 
 */
@Controller
@RequestMapping(value = "/admin/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private PermissionService permissionService;

	@RequestMapping(value = "")
	public String index(Model model) {
		return "admin/sys/menu";
	}

	// 初始化树数据
	@RequestMapping(value = "initTree")
	public void initTree(HttpServletResponse response) {
		List<Menu> menus = menuService.findAll();
		EasyTreeNode nodes = EasyTreeUtil.getMenuTree(menus);
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(nodes);
		RenderUtil.renderJson(list, response);
	}

	@RequestMapping(value = "create/{parentId}", method = RequestMethod.GET)
	public String create(@PathVariable("parentId") Long parentId, Model model) {
		Menu menu = new Menu(parentId);
		menu.setId(0l);
		model.addAttribute("menu", menu);
		return "admin/sys/menu-detail";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, HttpServletResponse response) {
		menuService.delete(id);
		// JsonUtil.renderSuccess("保存成功", response);
		return "redirect:/admin/menu/";
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		Menu menu = menuService.getMenu(id);
		model.addAttribute("menu", menu);
		return "admin/sys/menu-detail";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("preload") Menu menu, HttpServletResponse response) {
		String result = "success";
		menuService.save(menu);
		result = result + ":" + String.valueOf(menu.getId());
		// redirectAttributes.addFlashAttribute("message", "更新任务成功");
		RenderUtil.renderText(result, response);
		// return "redirect:/admin/menu/";
		return null;
	}

	@RequestMapping(value = "drag", method = RequestMethod.POST)
	public String drag(@Valid @ModelAttribute("preload") Menu menu, HttpServletRequest request) {
		Long parentId = Long.valueOf(request.getParameter("parentId"));
		Menu parent = menuService.getMenu(parentId);
		menu.setParent(parent);
		menuService.save(menu);
		return "redirect:/admin/menu/";
	}

	/** 菜单分配 */
	@RequestMapping(value = "assign")
	public String assign(Model model) {
		return "admin/sys/menu-assign";
	}

	/** 所有有效的资源 */
	@RequestMapping(value = "allEable")
	public void allEable(HttpServletResponse response) {
		List<Menu> menus = menuService.findAllEnable();
		EasyTreeNode nodes = EasyTreeUtil.getMenuTree(menus);
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(nodes);
		RenderUtil.renderJson(list, response);
	}

	@RequestMapping(value = "permission/{id}")
	public void permission(@PathVariable("id") Long id, HttpServletResponse response) {
		Menu menu = menuService.getMenu(id);
		List<Permission> permissions = permissionService.findAll();
		EasyTreeNode treeNode = EasyTreeUtil.gePermCheckedTree(permissions, menu.getPermissions());
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(treeNode);
		RenderUtil.renderJson(list, response);
	}

	@RequestMapping(value = "savePerm/{id}")
	public void savePerm(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
		String permids = request.getParameter("permids");
		menuService.savePerms(id, permids);
		permission(id, response);
	}

	/**
	 * 使用@ModelAttribute, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 */
	@ModelAttribute("preload")
	public Menu getEntity(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			if (id > 0)
				return menuService.getMenu(id);
			else
				return new Menu();
		}
		return null;
	}

}
