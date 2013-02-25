package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class DomainUtilTest extends Assert{

	@Test
	public void test_isMainSite() {
		assertTrue(DomainUtil.isMainSite("aaa.com"));
		assertTrue(DomainUtil.isMainSite("www.aaa.com"));
		assertFalse(DomainUtil.isMainSite("sz.aaa.com"));
		assertFalse(DomainUtil.isMainSite(null));
	}
	
}
