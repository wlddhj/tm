package com.hhz.tms.entity.wms;

/**
 * 
 * 基础产品维护信息
 * 
 * @author Administrator
 * 
 */
public class WmsBasicProduct extends BaseInfoEntity {

	/**
	 * 序号
	 */
	public String sn;
	/**
	 * 产品信息
	 */
	public WmsBasicProduct basicProductEntiy;
	/**
	 * 中文名
	 */
	public String nameCn;
	/**
	 * 英文名
	 */
	public String nameEn;
	/**
	 * 产品类型
	 */
	public WmsProductType productTypeEntity;
	/**
	 * 所属客户
	 */
	public WmsCustomer customerEntity;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public WmsBasicProduct getBasicProductEntiy() {
		return basicProductEntiy;
	}

	public void setBasicProductEntiy(WmsBasicProduct basicProductEntiy) {
		this.basicProductEntiy = basicProductEntiy;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public WmsProductType getProductTypeEntity() {
		return productTypeEntity;
	}

	public void setProductTypeEntity(WmsProductType productTypeEntity) {
		this.productTypeEntity = productTypeEntity;
	}

	public WmsCustomer getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(WmsCustomer customerEntity) {
		this.customerEntity = customerEntity;
	}

}
