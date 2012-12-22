package com.fpcms.common.util;

import java.util.Collection;

import org.apache.commons.lang.math.RandomUtils;

public class RandomUtil {

	public static <T> T randomSelect(T[] array) {
		if(array == null) return null;
		return array[Math.abs(RandomUtils.nextInt(array.length))];
	}
	
	public static <T> T randomSelect(Collection<T> array) {
		if(array == null || array.isEmpty()) return null;
		int size = array.size();
		int count = 0;
		int random = Math.abs(RandomUtils.nextInt(size));
		for(T item : array) {
			if(random == count) {
				return item;
			}
			count++;
		}
		return null;
	}
}
