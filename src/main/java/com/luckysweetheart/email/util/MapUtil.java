package com.luckysweetheart.email.util;

import org.apache.commons.collections.MapUtils;

import java.util.*;

public class MapUtil extends MapUtils {
	
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> objObj(Object... objs) {
		return MapUtils.putAll(new HashMap<Object, Object>(), objs);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> strObj(Object... objs) {
		if (objs.length % 2 != 0) {
			throw new RuntimeException("长度必须是2的倍数");
		}
		for (int i = 0; i < objs.length;) {
			if (objs[i].getClass() != String.class) {
				throw new RuntimeException("key必须是字符串");
			}
			i = i+2;
		}
		return MapUtils.putAll(new HashMap<String, Object>(), objs);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> strStr(String... objs) {
		if (objs.length % 2 != 0) {
			throw new RuntimeException("长度必须是2的倍数");
		}
		return MapUtils.putAll(new HashMap<String, String>(), objs);
	}


}
