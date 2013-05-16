package com.hhz.tms.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 静态常量
 * 
 * @author huangjian
 * 
 */
public class TmsConstants {
	public static Map<Object, String> getMapEnableFlg() {
		Map<Object, String> map = new LinkedHashMap<Object, String>();
		map.put("", "");
		map.put(true, "是");
		map.put(false, "否");
		return map;
	}

	/** 应用代码 */
	public static class App {
		public static final String ADMIN = "0";
		public static final String WMS = "1";
	}

	public static class DictType {
		// 所有应用
		public static final String ALL_APPS = "ALL_APPS";
	}
}
