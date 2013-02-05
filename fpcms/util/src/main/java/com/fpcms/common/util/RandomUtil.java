package com.fpcms.common.util;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.util.Assert;

import com.fpcms.common.webcrawler.htmlparser.HtmlPage;

public class RandomUtil {
	/**
	 * 根据输入百分比
	 * @param percent 百分比
	 * @return
	 */
	public static boolean randomTrue(int percent) {
		Assert.isTrue(percent>0 && percent <= 100,"percent>0 && percent <= 100 is false,input value:"+percent);
		int random = Math.abs(RandomUtils.nextInt(100));
		return random < percent;
	}
	
	public static <T> T randomSelect(T... array) {
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

	public static <T> T randomRemove(List<T> pageList) {
		T obj = randomSelect(pageList);
		if(obj != null) {
			pageList.remove(obj);
		}
		return obj;
	}
}
