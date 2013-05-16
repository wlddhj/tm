package com.hhz.tms.entity.wms;

import com.hhz.tms.entity.IdEntity;

public class WmsCurrencyEntity extends IdEntity {

	/**
	 * 中文名称
	 */
	public String nameCn;
	/**
	 * 英文
	 */
	public String nameEn;
	/**
	 * 符号
	 */
	public String symbol;

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

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
