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

import com.hhz.tms.admin.service.sys.PermissionService;
import com.hhz.tms.admin.service.sys.ResourceService;
import com.hhz.tms.entity.sys.Permission;
import com.hhz.tms.entity.sys.Resource;
import com.hhz.tms.service.BaseService;
import com.hhz.tms.util.Constants;
import com.hhz.tms.util.EasyTreeNode;
import com.hhz.tms.util.EasyTreeUtil;
import com.hhz.tms.util.JsonUtil;
import com.hhz.tms.util.RenderUtil;
import com.hhz.tms.web.SingleCrudControll;

/**
 * @author huangjian
 * 
 */
@Controller
@RequestMapping(value = "/admin/resource")
public class ResourceController extends SingleCrudControll<Resource> {
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private PermissionService permissionService;

	@RequestMapping(value = "")
	public String index(Model model) {
		model.addAttribute("mapEnableFlg", Constants.getMapEnableFlg());
		model.addAttribute("search_EQB_enableFlg", true);
		return "admin/sys/resource";
	}

	/** 资源分配 */
	@RequestMapping(value = "assign")
	public String assign(Model model) {
		return "admin/sys/resource-assign";
	}

	/** 所有有效的资源 */
	@RequestMapping(value = "allEable")
	public void allEable(HttpServletResponse response) {
		List<Resource> resources = resourceService.findAllEnable();
		JsonUtil.renderListJson(response, resources);
	}

	@RequestMapping(value = "permission/{id}")
	public void permission(@PathVariable("id") Long id, HttpServletResponse response) {
		Resource resource = resourceService.getEntity(id);
		List<Permission> permissions = permissionService.findAll();
		EasyTreeNode treeNode = EasyTreeUtil.gePermCheckedTree(permissions, resource.getPermissions());
		List<EasyTreeNode> list = new ArrayList<EasyTreeNode>();
		list.add(treeNode);
		RenderUtil.renderJson(list, response);
	}
	@RequestMapping(value = "savePerm/{id}")
	public void savePerm(@PathVariable("id") Long id, HttpServletRequest request,HttpServletResponse response) {
		String permids=request.getParameter("permids");
		resourceService.savePerms(id, permids);
		permission(id, response);
	}

	@Override
	public BaseService<Resource> getService() {
		// TODO Auto-generated method stub
		return resourceService;
	}

}
