package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class URLUtilTest extends Assert{

	@Test
	public void test() {
		assertEquals("localhost",URLUtil.getHostSite("http://192.168.0.1"));
		assertEquals("localhost",URLUtil.getHostSite(null));
		assertEquals("163.com",URLUtil.getHostSite("http://163.com"));
		assertEquals("163.com",URLUtil.getHostSite("https://163.com"));
	}
}
