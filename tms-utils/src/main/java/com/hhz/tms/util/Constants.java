package com.hhz.tms.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constants {
	public static Map<Object, String> getMapEnableFlg() {
		Map<Object, String> map = new LinkedHashMap<Object, String>();
		map.put("", "");
		map.put(true, "是");
		map.put(false, "否");
		return map;
	}
}
