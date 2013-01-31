package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class CmsSiteUtilTest extends Assert{

	@Test
	public void test() {
		assertEquals("aaafaipiao.com",CmsSiteUtil.getDomain("www.aaafaipiao.com"));
		assertEquals("aaafaipiao.com",CmsSiteUtil.getDomain("www.gd.aaafaipiao.com"));
		assertEquals("aaafaipiao.com",CmsSiteUtil.getDomain("aaafaipiao.com"));
	}
	
}
