package com.hhz.tms.entity.wms;

import com.hhz.tms.entity.IdEntity;

/**
 * 
 * 实体基础字段信息
 * 
 * @author Administrator
 * 
 */
public class BaseInfoEntity extends IdEntity {

	/**
	 * 是否可用[Y=可用|N=不可用]
	 */
	private String active;
	/**
	 * 创建人编码
	 */
	private String createUserCode;
	/**
	 * 创建人姓名
	 */
	private String createUserName;
	/**
	 * 创建人组织编码
	 */
	private String createUserOrgCode;
	/**
	 * 创建人组织名称
	 */
	private String createUserOrgName;
	/**
	 * 备注
	 */
	private String remark;

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateUserOrgCode() {
		return createUserOrgCode;
	}

	public void setCreateUserOrgCode(String createUserOrgCode) {
		this.createUserOrgCode = createUserOrgCode;
	}

	public String getCreateUserOrgName() {
		return createUserOrgName;
	}

	public void setCreateUserOrgName(String createUserOrgName) {
		this.createUserOrgName = createUserOrgName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
