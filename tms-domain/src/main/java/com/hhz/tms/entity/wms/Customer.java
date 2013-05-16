package com.hhz.tms.entity.wms;

/**
 * 简单客户信息
 * 
 * @author Administrator
 * 
 */
public class Customer extends BaseInfoEntity {
	/**
	 * 编码
	 */
	public String code;
	/**
	 * 姓名
	 */
	public String name;
	/**
	 * 联系地址
	 */
	public String address;
	/**
	 * 移动淡化
	 */
	public String mobilePhone;
	/**
	 * 固定电话
	 */
	public String teliphone;
	/**
	 * 传真
	 */
	public String faxCode;
	/**
	 * 联系人
	 */
	public String contactMan;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTeliphone() {
		return teliphone;
	}

	public void setTeliphone(String teliphone) {
		this.teliphone = teliphone;
	}

	public String getFaxCode() {
		return faxCode;
	}

	public void setFaxCode(String faxCode) {
		this.faxCode = faxCode;
	}

	public String getContactMan() {
		return contactMan;
	}

	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}

}
