package com.hhz.tms.entity.wms;

/**
 * 
 * 基础产品信息
 * 
 * @author Administrator
 * 
 */
public class ProductType extends BaseInfoEntity {

	/**
	 * 类型名
	 */
	public String name;
	/**
	 * 编码
	 */
	public String code;
	/**
	 * 描述
	 */
	public String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
