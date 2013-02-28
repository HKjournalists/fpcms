package com.fpcms.common.util;

import java.io.UnsupportedEncodingException;

import org.junit.Test;


public class IcibaTranslateUtilTest {

	@Test
	public void test_auto_translate() throws UnsupportedEncodingException {
		String str = IcibaTranslateUtil.autoTranslate("Earlier reports: interception technology China have begun to take the middle of the anti-missile capability is difficult");
		System.out.println(str);
	}
}
