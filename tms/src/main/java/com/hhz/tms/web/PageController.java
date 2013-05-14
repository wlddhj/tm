/**
 * 
 */
package com.hhz.tms.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springside.modules.web.Servlets;

import com.hhz.tms.entity.IdEntity;
import com.hhz.tms.util.JsonUtil;
import com.hhz.tms.util.WebUtil;
import com.hhz.tms.util.service.BaseService;

/**
 * 单表翻页基类
 * @author huangjian
 *
 */
public abstract class PageController<T extends IdEntity> {
	public abstract BaseService<T> getService();
	protected final Log logger = LogFactory.getLog(getClass());
	@RequestMapping(value = "list")
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "rows", defaultValue = "10") int pageSize, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");

		String sortField = request.getParameter("sort");
		String order = request.getParameter("order");

		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			sortField = "id";
			order = WebUtil.DESC;
		}
		PageRequest pageRequest = WebUtil.buildPageRequest(pageNumber, pageSize, sortField, order);
		Page<T> page = getService().findPage(searchParams, pageRequest);

		JsonUtil.renderJson(response, page);
		return null;

	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, HttpServletResponse response) {
		getService().delete(id);
		JsonUtil.renderSuccess("保存成功", response);
		return null;
	}
}
