package com.fpcms.model;

import org.junit.Assert;
import org.junit.Test;


public class CmsContentTest extends Assert{
	CmsContent c = new CmsContent();
	@Test
	public void test_getKeyword() {
		c.setTitle("中国人民银行是个好银行");
		assertEquals("中国人民银行,银行",c.getKeyword());
		
		c.setTitle("   ");
		assertEquals(null,c.getKeyword());
	}
}
