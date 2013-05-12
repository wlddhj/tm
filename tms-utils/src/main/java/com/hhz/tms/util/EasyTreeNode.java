/**
 * 
 */
package com.hhz.tms.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;

/**
 * EasyTree树模型
 * 
 * @author jianhuang
 * 
 */
public class EasyTreeNode {
	public static final String STATE_OPEN = "open";
	public static final String STATE_CLOSED = "closed";
	private String id;
	private String text;
	private String iconCls;// 图标class，如icon-save
	private String state;// open,closed,默认open
	private Boolean checked = false;
	private Map<String, Object> attributes = new HashMap<String, Object>();// 其他属性
	private List<EasyTreeNode> children = new ArrayList<EasyTreeNode>();

	public void addAttr(String key, Object value) {
		attributes.put(key, value);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean isChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<EasyTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<EasyTreeNode> children) {
		this.children = children;
	}
}
