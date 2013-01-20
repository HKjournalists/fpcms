package com.fpcms.common.util;

import org.junit.Test;


public class GoogleTranslateUtilTest {

	@Test
	public void test() {
		String str = GoogleTranslateUtil.fromChinese2English("中国");
		System.out.println("fromChinese2English:"+str);
	}
}
