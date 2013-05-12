/**
 * 
 */
package com.hhz.tms.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hhz.tms.entity.sys.Dept;
import com.hhz.tms.entity.sys.Menu;
import com.hhz.tms.entity.sys.Permission;
import com.hhz.tms.entity.sys.Resource;
import com.hhz.tms.entity.sys.Role;
import com.hhz.tms.entity.sys.User;

/**
 * @author jianhuang
 * 
 */
public class EasyTreeUtil {
	private static EasyTreeNode getRoot(String name) {
		EasyTreeNode root = new EasyTreeNode();
		root.setId("0");
		root.setText(name);
		root.setState(EasyTreeNode.STATE_OPEN);
		return root;
	}

	/** 菜单树 */
	public static EasyTreeNode getMenuTree(List<Menu> menus) {
		return getCheckMenuTree(menus, null);
	}

	/** 资源权限树 */
	public static EasyTreeNode gePermCheckedTree(List<Permission> permissions, List<Permission> lstSelect) {
		EasyTreeNode root = getRoot("All Permissions");
		for (Permission permission : permissions) {
			EasyTreeNode resultVo = new EasyTreeNode();
			resultVo.setText(permission.getPermName() + "(" + permission.getPermCd() + ")");
			resultVo.setId(String.valueOf(permission.getId()));
			boolean isContain = CollectionUtil.contain(lstSelect, "id", permission.getId());
			resultVo.setChecked(isContain);
			root.getChildren().add(resultVo);
		}
		return root;
	}

	/** 权限角色树 */
	public static EasyTreeNode getPermRoleTree(List<Role> roles, List<Role> lstSelect) {
		EasyTreeNode root = getRoot("All Roles");
		for (Role role : roles) {
			EasyTreeNode resultVo = new EasyTreeNode();
			resultVo.setText(role.getRoleName() + "(" + role.getRoleCd() + ")");
			resultVo.setId(String.valueOf(role.getId()));
			boolean isContain = CollectionUtil.contain(lstSelect, "id", role.getId());
			resultVo.setChecked(isContain);
			root.getChildren().add(resultVo);
		}
		return root;
	}

	/** 权限资源树 */
	public static EasyTreeNode getPermResourceTree(List<Resource> resources, List<Resource> lstSelect) {
		EasyTreeNode root = getRoot("All Resources");
		for (Resource resource : resources) {
			EasyTreeNode resultVo = new EasyTreeNode();
			resultVo.setText(resource.getUrl());
			resultVo.setId(String.valueOf(resource.getId()));
			boolean isContain = CollectionUtil.contain(lstSelect, "id", resource.getId());
			resultVo.setChecked(isContain);
			root.getChildren().add(resultVo);
		}
		return root;
	}

	/** 权限菜单树 */
	public static EasyTreeNode getCheckMenuTree(List<Menu> menus, List<Menu> lstSelect) {
		EasyTreeNode root = getRoot("All Menus");

		Map<Long, EasyTreeNode> mapId2Vo = new HashMap<Long, EasyTreeNode>();
		for (Menu menu : menus) {
			EasyTreeNode resultVo = new EasyTreeNode();
			resultVo.setText(menu.getMenuName());
			resultVo.setState(EasyTreeNode.STATE_OPEN);
			resultVo.setId(String.valueOf(menu.getId()));
			resultVo.addAttr("url", menu.getUrl());
			resultVo.addAttr("remark", menu.getRemark());
			resultVo.addAttr("parentId", menu.getParent() == null ? "" : menu.getParent().getId());
			if (lstSelect != null) {
				boolean isContain = CollectionUtil.contain(lstSelect, "id", menu.getId());
				resultVo.setChecked(isContain);
			}
			mapId2Vo.put(menu.getId(), resultVo);
		}

		for (Menu menu : menus) {
			EasyTreeNode resultVo = mapId2Vo.get(menu.getId());
			if (menu.getParent() == null) {
				root.getChildren().add(resultVo);
			} else {
				EasyTreeNode parentVo = mapId2Vo.get(menu.getParent().getId());
				parentVo.getChildren().add(resultVo);
				parentVo.setState(EasyTreeNode.STATE_CLOSED);
			}
		}
		for (EasyTreeNode node : root.getChildren()) {
			node.setState(EasyTreeNode.STATE_OPEN);
		}
		return root;
	}

	/** 机构树 */
	public static EasyTreeNode getDeptTree(List<Dept> depts) {
		return getDeptTree(depts, false, null);
	}
	public static EasyTreeNode getDeptTree(List<Dept> depts,boolean initUser) {
		return getDeptTree(depts, initUser, null);
	}

	/** 机构人员树 */
	public static EasyTreeNode getDeptTree(List<Dept> depts, boolean initUser, List<User> lstSelect) {
		EasyTreeNode root = getRoot("All Depts");

		Map<Long, EasyTreeNode> mapId2Vo = new HashMap<Long, EasyTreeNode>();
		for (Dept dept : depts) {
			EasyTreeNode resultVo = new EasyTreeNode();
			resultVo.setText(dept.getDeptName());
			resultVo.setState(EasyTreeNode.STATE_OPEN);
			resultVo.setId(String.valueOf(dept.getId()));
			resultVo.addAttr("parentId", dept.getParent() == null ? "" : dept.getParent().getId());
			resultVo.addAttr("nodeType", "dept");
			if (initUser) {
				// 生成人员节点
				for (User user : dept.getUsers()) {
					EasyTreeNode userNode = new EasyTreeNode();
					userNode.setText(user.getName());
					userNode.setId("user" + String.valueOf(user.getId()));
					userNode.addAttr("nodeType", "user");
					if (lstSelect != null) {
						boolean isContain = CollectionUtil.contain(lstSelect, "id", user.getId());
						userNode.setChecked(isContain);
					}
					resultVo.getChildren().add(userNode);
				}
			}
			mapId2Vo.put(dept.getId(), resultVo);
		}

		for (Dept dept : depts) {
			EasyTreeNode resultVo = mapId2Vo.get(dept.getId());
			if (dept.getParent() == null) {
				root.getChildren().add(resultVo);
			} else {
				EasyTreeNode parentVo = mapId2Vo.get(dept.getParent().getId());
				parentVo.getChildren().add(resultVo);
				parentVo.setState(EasyTreeNode.STATE_CLOSED);
			}
		}
		for (EasyTreeNode node : root.getChildren()) {
			node.setState(EasyTreeNode.STATE_OPEN);
		}
		return root;
	}

}
