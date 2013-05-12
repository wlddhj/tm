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
import com.hhz.tms.entity.sys.Dept;
import com.hhz.tms.util.EasyTreeNode;
import com.hhz.tms.util.EasyTreeUtil;
import com.hhz.tms.util.RenderUtil;

/**
 * 机构管理
 * @author huangjian
 * 
 */
@Controller
@RequestMapping(value = "/admin/dept")
public class DeptController {
	@Autowired
	private DeptService deptService;

	@RequestMapping(value = "")
	public String index(Model model) {
		return "admin/sys/dept";
	}

	// 初始化树数据
	@RequestMapping(value = "initTree")
	public void initTree(HttpServletResponse response) {
		List<Dept> depts = deptService.findAll();
		EasyTreeNode nodes = EasyTreeUtil.getDeptTree(depts);
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(nodes);
		RenderUtil.renderJson(list, response);
	}

	@RequestMapping(value = "create/{parentId}", method = RequestMethod.GET)
	public String create(@PathVariable("parentId") Long parentId, Model model) {
		Dept dept = new Dept(parentId);
		dept.setId(0l);
		model.addAttribute("dept", dept);
		return "admin/sys/dept-detail";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, HttpServletResponse response) {
		deptService.delete(id);
		// JsonUtil.renderSuccess("保存成功", response);
		return "redirect:/admin/dept/";
	}

	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		Dept dept = deptService.getDept(id);
		model.addAttribute("dept", dept);
		return "admin/sys/dept-detail";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("preload") Dept dept, HttpServletResponse response) {
		String result = "success";
		deptService.save(dept);
		result = result + ":" + String.valueOf(dept.getId());
		RenderUtil.renderText(result, response);
		return null;
	}

	@RequestMapping(value = "drag", method = RequestMethod.POST)
	public String drag(@Valid @ModelAttribute("preload") Dept dept, HttpServletRequest request) {
		Long parentId = Long.valueOf(request.getParameter("parentId"));
		Dept parent = deptService.getDept(parentId);
		dept.setParent(parent);
		deptService.save(dept);
		return "redirect:/admin/dept/";
	}

	/** 所有有效的资源 */
	@RequestMapping(value = "allEable")
	public void allEable(HttpServletResponse response) {
		List<Dept> depts = deptService.findAllEnable();
		EasyTreeNode nodes = EasyTreeUtil.getDeptTree(depts);
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(nodes);
		RenderUtil.renderJson(list, response);
	}


	/**
	 * 使用@ModelAttribute, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 */
	@ModelAttribute("preload")
	public Dept getEntity(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			if (id > 0)
				return deptService.getDept(id);
			else
				return new Dept();
		}
		return null;
	}

}
