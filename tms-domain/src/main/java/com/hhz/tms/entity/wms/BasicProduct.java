package com.hhz.tms.entity.wms;

/**
 * 
 * 基础产品维护信息
 * 
 * @author Administrator
 * 
 */
public class BasicProduct extends BaseInfoEntity {

	/**
	 * 序号
	 */
	public String sn;
	/**
	 * 产品信息
	 */
	public BasicProduct basicProductEntiy;
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
	public ProductType productTypeEntity;
	/**
	 * 所属客户
	 */
	public Customer customerEntity;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public BasicProduct getBasicProductEntiy() {
		return basicProductEntiy;
	}

	public void setBasicProductEntiy(BasicProduct basicProductEntiy) {
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

	public ProductType getProductTypeEntity() {
		return productTypeEntity;
	}

	public void setProductTypeEntity(ProductType productTypeEntity) {
		this.productTypeEntity = productTypeEntity;
	}

	public Customer getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(Customer customerEntity) {
		this.customerEntity = customerEntity;
	}

}
