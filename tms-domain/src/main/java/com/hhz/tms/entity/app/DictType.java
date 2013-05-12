package com.hhz.tms.entity.app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OrderBy;

import com.hhz.tms.entity.IdEntity;

/**
 * 数据字典
 * 
 * @author jianhuang
 * 
 */
@Entity
@Table(name = "t_app_dict_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DictType extends IdEntity {
	private String dictTypeCd;
	private String dictTypeName;
	private BigDecimal dispOrderNo;
	private String remark;
	private List<DictData> dictDatas;

	public String getDictTypeCd() {
		return this.dictTypeCd;
	}

	public void setDictTypeCd(String dictTypeCd) {
		this.dictTypeCd = dictTypeCd;
	}

	public String getDictTypeName() {
		return this.dictTypeName;
	}

	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
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

	@OneToMany(fetch = FetchType.LAZY,mappedBy="dictType",cascade=CascadeType.ALL)
	@BatchSize(size = 10)
	@OrderBy(clause = "disp_Order_No asc")
	public List<DictData> getDictDatas() {
		return this.dictDatas;
	}

	public void setDictDatas(List<DictData> dictDatas) {
		this.dictDatas = dictDatas;
	}

}
