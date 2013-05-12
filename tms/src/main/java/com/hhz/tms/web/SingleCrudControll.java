/**
 * 
 */
package com.hhz.tms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import com.hhz.tms.entity.IdEntity;
import com.hhz.tms.util.JsonUtil;

/**
 * 单表‘增删改查’功能
 * 
 * @author huangjian
 * 
 */
public abstract class SingleCrudControll<T extends IdEntity> extends PageController<T> {
	@RequestMapping(value = "saveBatch")
	public String saveBatch(HttpServletRequest request, HttpServletResponse response) {
		List<T> insertedRecords = JsonUtil.getInsertRecords(getService().getEntityClazz(), request);
		List<T> updatedRecords = JsonUtil.getUpdatedRecords(getService().getEntityClazz(), request);
		List<T> deletedRecords = JsonUtil.getDeletedRecords(getService().getEntityClazz(), request);
		getService().saveBatch(insertedRecords, updatedRecords, deletedRecords);
		JsonUtil.renderSuccess("保存成功", response);
		return null;
	}
}
