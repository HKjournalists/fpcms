package com.fpcms.common.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
	
	@SuppressWarnings("all")
	public static Map sortByValue(Map map) {
		return sortByValue(map,false);
	}
	
	@SuppressWarnings("all")
	public static Map sortByValue(Map map, final boolean reverse) {
        List list = new LinkedList(map.entrySet());
        Collections .sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                if (reverse) {
                    return -((Comparable) ((Map .Entry)o1).getValue())
                            .compareTo(((Map .Entry)o2).getValue());
                }
                return ((Comparable) ((Map .Entry)o1).getValue())
                        .compareTo(((Map .Entry)o2).getValue());
            }
        });

        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
}
	
}
