package com.hhz.tms.entity.wms;

public class WmsBasicProductEntiy extends BaseInfoEntity {

	/**
	 * 序号
	 */
	public String sn;
	/**
	 * 产品信息
	 */
	public WmsBasicProductEntiy basicProductEntiy;
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
	public WmsProductTypeEntity productTypeEntity;
	/**
	 * 所属客户
	 */
	public WmsCustomerEntity customerEntity;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public WmsBasicProductEntiy getBasicProductEntiy() {
		return basicProductEntiy;
	}

	public void setBasicProductEntiy(WmsBasicProductEntiy basicProductEntiy) {
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

	public WmsProductTypeEntity getProductTypeEntity() {
		return productTypeEntity;
	}

	public void setProductTypeEntity(WmsProductTypeEntity productTypeEntity) {
		this.productTypeEntity = productTypeEntity;
	}

	public WmsCustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(WmsCustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

}
