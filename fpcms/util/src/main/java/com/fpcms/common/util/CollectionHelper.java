package com.fpcms.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.shiro.util.CollectionUtils;

public class CollectionHelper {
	
	public static <T> List<List<T>> toMultiRows(Collection<T> list,int cols) {
		List<List<T>> result = new ArrayList<List<T>>();
		
		Iterator<T> it = list.iterator();
		while(it.hasNext()) {
			ArrayList<T> row = new ArrayList<T>();
			for(int i = 0; i < cols && it.hasNext(); i++) {
				row.add(it.next());
			}
			result.add(row);
		}
		return result;
	}
	
	public static <K,V extends Number> K getMaxKeyByValue(Map<K, V> elementScores) {
		if(CollectionUtils.isEmpty(elementScores)) {
			return null;
		}
		
		double maxValue = Double.MIN_VALUE;
		K maxKey = null;
		for(K key : elementScores.keySet()) {
			double value = ((Number)elementScores.get(key)).doubleValue();
			if(value > maxValue) {
				maxValue = value;
				maxKey = key;
			}
		}
		return maxKey;
	}
}
