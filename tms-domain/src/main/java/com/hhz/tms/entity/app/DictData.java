package com.hhz.tms.entity.app;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hhz.tms.entity.IdEntity;

/**
 * 数据字典
 * 
 * @author jianhuang
 * 
 */
@Entity
@Table(name = "t_app_dict_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DictData extends IdEntity {
	private DictType dictType;
	private String dictCd;
	private String dictName;
	private BigDecimal dispOrderNo;
	private String remark;
	private String i18n;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dict_type_id")
	public DictType getDictType() {
		return this.dictType;
	}

	public void setDictType(DictType dictType) {
		this.dictType = dictType;
	}

	@Column(length = 50)
	public String getDictCd() {
		return this.dictCd;
	}

	public void setDictCd(String dictCd) {
		this.dictCd = dictCd;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	@Column(precision = 12, scale = 0)
	public BigDecimal getDispOrderNo() {
		return this.dispOrderNo;
	}

	public void setDispOrderNo(BigDecimal dispOrderNo) {
		this.dispOrderNo = dispOrderNo;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getI18n() {
		return i18n;
	}

	public void setI18n(String i18n) {
		this.i18n = i18n;
	}

}
