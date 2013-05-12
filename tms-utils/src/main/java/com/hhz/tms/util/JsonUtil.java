package com.hhz.tms.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * json转换工具类
 * 
 * @author Jian
 */
public class JsonUtil {
	private static String[] DATE_FORMATS = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd" };

	public static String list2Json(final List<?> list) {
		return list2Json(list, null, new String[0], null);
	}

	public static String list2Json(final List<?> list, String[] excludes) {
		return list2Json(list, null, excludes, null);
	}

	public static String list2Json(final List<?> list, String[] excludes, Set<String> codeFields) {
		return list2Json(list, null, excludes, codeFields);
	}

	/**
	 * 根据List自动生成jquery_easyui_grid所需的json数据
	 * 
	 * @param list
	 *            列表数据
	 * @param dateformat
	 *            日期格式化
	 * @param excludes
	 *            不需要生成json的字段名列表(如果包含one-2-many或者many-2-one的字段一定要包含在excludes里)
	 */
	private static String list2Json(final List<?> list, String dateformat, String[] excludes, Set<String> codeFields) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("rows", list);
		return obj2Json(map, dateformat, excludes, codeFields);
	}

	public static ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(CollectionUtil
				.array2Set(new String[0]));
		SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("excludeFilter", propertyFilter);
		mapper.setFilters(filters);
		return mapper;
	}

	public static String obj2Json(final Object obj) {
		return obj2Json(obj, null, null, null);
	}

	public static String obj2Json(final Object obj, String[] excludes) {
		return obj2Json(obj, null, excludes, null);
	}

	public static String obj2Json(final Object obj, String[] excludes, Set<String> codeFields) {
		return obj2Json(obj, null, excludes, codeFields);
	}

	private static String obj2Json(final Object obj, String dateformat, String[] excludes, Set<String> codeFields) {
		ObjectMapper mapper = getObjectMapper();
		SimpleDateFormat format;
		try {
			format = new SimpleDateFormat(dateformat);
		} catch (Exception ex) {
			format = DateOperator.defaultDateFormaterWithTime;
		}
		SimpleModule testModule = new SimpleModule("BooleanModule", Version.unknownVersion());
		ToStringSerializer stringSerializer = new ToStringSerializer();
//		testModule.addSerializer(boolean.class, stringSerializer);
//		testModule.addSerializer(Boolean.class, new ToStringSerializer());
		mapper.registerModule(testModule);
		mapper.setDateFormat(format);
		SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.serializeAllExcept(CollectionUtil
				.array2Set(excludes));
		SimpleFilterProvider filters = new SimpleFilterProvider().addFilter("excludeFilter", propertyFilter);
		mapper.setFilters(filters);
		// 去掉null值
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// mapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS,
		// true);
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// JsonConfig config = getJsonConfig(excludes, dateformat, codeFields);
		return jsonString;
	}

	/**
	 * 把jsonString转成对象
	 * 
	 * @param clazz
	 * @param jsonString
	 * @param root
	 * @return
	 */
	public static <T> List<T> json2List(Class<T> clazz, String jsonString, String root) {
		ObjectMapper mapper = getObjectMapper();
		List<T> list = new ArrayList<T>();

		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray array = jsonObject.getJSONArray(root);
			for (int i = 0; i < array.length(); i++) {
				T obj = mapper.readValue(array.getString(i), clazz);
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// JSONObject jsonObject = JSONObject.fromObject(jsonString);
		// JSONArray jsonArray = jsonObject.getJSONArray(root);
		// JSONUtils.getMorpherRegistry().registerMorpher(new
		// DateMorpher(DATE_FORMATS));
		return list;
	}

	/**
	 * 把jsonString转成对象
	 * 
	 * @param <T>
	 * @param clazz
	 * @param jsonString
	 * @return
	 */
	public static <T> T json2Object(Class<T> clazz, String jsonString) {
		ObjectMapper mapper = getObjectMapper();

		// JSONObject jsonObject = JSONObject.fromObject(jsonString);
		// JSONUtils.getMorpherRegistry().registerMorpher(new
		// DateMorpher(DATE_FORMATS));
		try {
			return mapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ------EasyUI专用方法和常量-----//

	public static String JSON_NAME = "_easy_grid";

	public static String INSERT_RECORDS_KEY = "_inserted";

	public static String UPDATE_RECORDS_KEY = "_updated";

	public static String DELETE_RECORDS_KEY = "_deleted";

	public static <T> List<T> getInsertRecords(Class<T> clazz, HttpServletRequest request) {
		return getJsonObject(clazz, INSERT_RECORDS_KEY, request);
	}

	public static <T> List<T> getUpdatedRecords(Class<T> clazz, HttpServletRequest request) {
		return getJsonObject(clazz, UPDATE_RECORDS_KEY, request);
	}

	public static <T> List<T> getDeletedRecords(Class<T> clazz, HttpServletRequest request) {
		return getJsonObject(clazz, DELETE_RECORDS_KEY, request);
	}

	private static <T> List<T> getJsonObject(Class<T> clazz, String key, HttpServletRequest request) {
		String json = request.getParameter(JSON_NAME);
		ObjectMapper mapper = getObjectMapper();

		List<T> list = new ArrayList<T>();
		mapper.setDateFormat(DateOperator.defaultDateFormaterWithTime);
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONArray array = jsonObject.getJSONArray(key);
			for (int i = 0; i < array.length(); i++) {
				T obj = mapper.readValue(array.getString(i), clazz);
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		// JSONObject jsonObject = JSONObject.fromObject(json);
		// JSONArray jsonArray = jsonObject.getJSONArray(key);
		// JSONUtils.getMorpherRegistry().registerMorpher(new
		// DateMorpher(DATE_FORMATS));
		// return JSONArray.toList(jsonArray, clazz);
	}

	/**
	 * 返回成功信息 {'success':successInfo}
	 * 
	 * @param successInfo
	 */
	public static void renderSuccess(String successInfo, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("success", successInfo);
		RenderUtil.renderJson(map, response);
	}

	/**
	 * 返回失败信息 {'failure':failInfo}
	 * 
	 * @param failInfo
	 */
	public static void renderFailure(String failInfo, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("failure", failInfo);
		RenderUtil.renderJson(map, response);
	}

	public static void renderJson(HttpServletResponse response, final Page<?> page) {
		renderJson(response, page, null, new String[0], null);
	}

	public static void renderJson(HttpServletResponse response, final Page<?> page, Set<String> codeFields) {
		renderJson(response, page, null, new String[0], codeFields);
	}

	public static void renderJson(HttpServletResponse response, final Page<?> page, String[] excludes) {
		renderJson(response, page, null, excludes, null);
	}

	public static void renderJson(HttpServletResponse response, final Page<?> page, String[] excludes,
			Set<String> codeFields) {
		renderJson(response, page, null, excludes, codeFields);
	}

	/**
	 * 根据Page自动生成jquery_easyui_grid所需的json数据，包含分页信息
	 * 
	 * @param page
	 *            翻页信息
	 * @param dateformat
	 *            日期格式化
	 * @param excludes
	 *            不需要生成json的字段名列表(如果包含one-2-many或者many-2-one的字段一定要包含在excludes里)
	 */
	private static void renderJson(HttpServletResponse response, final Page<?> page, String dateformat,
			String[] excludes, Set<String> codeFields) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", page.getTotalElements());
		map.put("rows", page.getContent());
		renderDataJson(response, map, dateformat, excludes, codeFields);
	}

	private static void renderDataJson(HttpServletResponse response, final Object obj, String dateformat,
			String[] excludes, Set<String> codeFields) {
		String jsonString = obj2Json(obj);
		RenderUtil.renderText(jsonString, response);
	}

	public static void renderListJson(HttpServletResponse response, final List<?> list) {
		renderListJson(response, list, null, new String[0], null);
	}

	public static void renderListJson(HttpServletResponse response, final List<?> list, String[] excludes) {
		renderListJson(response, list, null, excludes, null);
	}

	public static void renderListJson(HttpServletResponse response, final List<?> list, String[] excludes,
			Set<String> codeFields) {
		renderListJson(response, list, null, excludes, codeFields);
	}

	/**
	 * 根据List自动生成jquery_easyui_grid所需的json数据
	 * 
	 * @param list
	 *            列表数据
	 * @param dateformat
	 *            日期格式化
	 * @param excludes
	 *            不需要生成json的字段名列表(如果包含one-2-many或者many-2-one的字段一定要包含在excludes里)
	 */
	private static void renderListJson(HttpServletResponse response, final List<?> list, String dateformat,
			String[] excludes, Set<String> codeFields) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		map.put("rows", list);
		renderDataJson(response, map, dateformat, excludes, codeFields);
	}

	// -------end----------//

	public static void main(String[] args) {
		System.out.println("aaaaaaaaaaaaaa");
		String str = "asdfffffffffffffff";
		str = obj2Json(str);
		System.out.println(str);

	}
}
