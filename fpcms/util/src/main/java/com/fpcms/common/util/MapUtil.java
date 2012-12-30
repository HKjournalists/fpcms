package com.fpcms.common.util;

import java.util.Map;

import com.duowan.common.util.ObjectUtils;

public class MapUtil {

	public static void mergeWithDefaultMap(Map map,Map defaultMap) {
		for(Object key : defaultMap.keySet()) {
			Object value = map.get(key);
			Object defaultValue = defaultMap.get(key);
			if(ObjectUtils.isNullOrEmptyString(value)) {
				map.put(key, defaultValue);
			}
		}
	}
	
}
