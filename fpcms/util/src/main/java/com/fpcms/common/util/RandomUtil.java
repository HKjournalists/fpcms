package com.fpcms.common.util;

import org.apache.commons.lang.math.RandomUtils;

public class RandomUtil {

	public static <T> T randomSelect(T[] array) {
		return array[Math.abs(RandomUtils.nextInt(array.length))];
	}
	
}
