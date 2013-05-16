package com.hhz.tms.entity.wms;

import java.math.BigDecimal;

/**
 * 库存产品信息
 * 
 * @author Administrator
 * 
 */
public class Product extends BaseInfoEntity {

	/**
	 * 序号
	 */
	public String sn;
	/**
	 * 数量
	 */
	public BigDecimal quantity;
	/**
	 * 产品信息
	 */
	public BasicProduct basicProductEntiy;
	/**
	 * 发票
	 */
	public Invoice invoiceEntity;
	/**
	 * 箱单号
	 */
	public String referenceBinCode;
	/**
	 * 产品类型
	 */
	public ProductType productTypeEntity;
	/**
	 * 币制
	 */
	public Currency currencyEntity;
	/**
	 * 单价
	 */
	public BigDecimal unit_price;
	/**
	 * 毛价格
	 */
	public BigDecimal gross_price;
	/**
	 * 单个质量
	 */
	public BigDecimal unit_weight;
	/**
	 * 毛重
	 */
	public BigDecimal unit_gross_weight;

	/**
	 * 款
	 */
	public BigDecimal width;
	/**
	 * 高
	 */
	public BigDecimal height;
	/**
	 * 长度
	 */
	public BigDecimal length;

}
