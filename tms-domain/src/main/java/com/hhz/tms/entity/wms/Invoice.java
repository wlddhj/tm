package com.hhz.tms.entity.wms;

import java.math.BigDecimal;

/**
 * 
 * 发票信息
 * 
 * @author Administrator
 * 
 */
public class Invoice extends BaseInfoEntity {
	/**
	 * 发票号
	 */
	public String invociceNo;
	/**
	 * 发票抬头
	 */
	public String invociceTitle;
	/**
	 * 金额
	 */
	public BigDecimal invoiceAmount;
	/**
	 * 币制
	 */
	public Currency currencyEntity;
	/**
	 * 开票人
	 */
	public String invoicingName;
	/**
	 * 开票单位
	 */
	public String invoicingCompany;

	public String getInvociceNo() {
		return invociceNo;
	}

	public void setInvociceNo(String invociceNo) {
		this.invociceNo = invociceNo;
	}

	public String getInvociceTitle() {
		return invociceTitle;
	}

	public void setInvociceTitle(String invociceTitle) {
		this.invociceTitle = invociceTitle;
	}

	public BigDecimal getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Currency getCurrencyEntity() {
		return currencyEntity;
	}

	public void setCurrencyEntity(Currency currencyEntity) {
		this.currencyEntity = currencyEntity;
	}

	public String getInvoicingName() {
		return invoicingName;
	}

	public void setInvoicingName(String invoicingName) {
		this.invoicingName = invoicingName;
	}

	public String getInvoicingCompany() {
		return invoicingCompany;
	}

	public void setInvoicingCompany(String invoicingCompany) {
		this.invoicingCompany = invoicingCompany;
	}

}
