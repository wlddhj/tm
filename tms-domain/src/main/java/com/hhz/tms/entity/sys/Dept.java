/**
 * 
 */
package com.hhz.tms.entity.sys;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OrderBy;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hhz.tms.entity.IdEntity;

/**
 * @author huangjian
 * 
 */
@Entity
@Table(name = "t_sys_dept")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dept extends IdEntity {
	private String deptName;
	private BigDecimal dispOrderNo;
	private Boolean enableFlg;
	private String remark;
	private Dept parent;
	private List<Dept> children;
	private List<User> users;
	public Dept(){
		
	}
	public Dept(Long parentId) {
		if (parentId != null && parentId != 0) {
			Dept dept = new Dept();
			dept.setId(parentId);
			this.setParent(dept);
		}
	}
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(precision = 12, scale = 0)
	public BigDecimal getDispOrderNo() {
		return dispOrderNo;
	}

	public void setDispOrderNo(BigDecimal dispOrderNo) {
		this.dispOrderNo = dispOrderNo;
	}
	@JsonSerialize(using=ToStringSerializer.class)
	public Boolean getEnableFlg() {
		return enableFlg;
	}

	public void setEnableFlg(Boolean enableFlg) {
		this.enableFlg = enableFlg;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public Dept getParent() {
		return parent;
	}

	public void setParent(Dept parent) {
		this.parent = parent;
	}
	@OneToMany(mappedBy = "dept")
	@BatchSize(size=10)
	@OrderBy(clause="disp_order_no asc")
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToMany(mappedBy = "parent")
	public List<Dept> getChildren() {
		return children;
	}

	public void setChildren(List<Dept> children) {
		this.children = children;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
