/**
 * 
 */
package com.hhz.tms.util;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.hhz.tms.entity.sys.Resource;

/**
 * @author huangjian
 * 
 */
public class WebUtil {
	public static final String DESC = "desc";
	public static final String ASC = "asc";

	/**
	 * 创建动态查询条件组合.
	 */
	public static <T> Specification<T> buildSpecification(Map<String, Object> searchParams, Class<T> clazz) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<T> spec = DynamicSpecifications.bySearchFilter(filters.values(), clazz);
		return spec;
	}

	/**
	 * 创建分页请求.
	 */
	public static PageRequest buildPageRequest(int pageNumber, int pagSize, String sortField) {
		return buildPageRequest(pageNumber, pagSize, sortField, null);
	}

	/**
	 * 创建分页请求.
	 */
	public static PageRequest buildPageRequest(int pageNumber, int pagSize, String sortField, String order) {
		Sort sort = new Sort(getDirection(order), sortField);
		return new PageRequest(pageNumber - 1, pagSize, sort);
	}

	private static Direction getDirection(String order) {
		Direction direction = Direction.DESC;
		if (WebUtil.ASC.equalsIgnoreCase(order)) {
			direction = Direction.ASC;
		}
		return direction;
	}
}
