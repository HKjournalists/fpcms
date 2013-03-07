package com.fpcms.model;

import org.junit.Assert;
import org.junit.Test;

import com.duowan.common.util.DateConvertUtils;
import com.fpcms.CmsContentDataFactory;


public class CmsContentTest extends Assert{
	CmsContent c = new CmsContent();
	@Test
	public void test_getKeyword() {
		c.setTitle("中国人民银行是个好银行");
		assertEquals("中国人民银行,银行",c.getKeyword());
		
		c.setTitle("   ");
		assertEquals(null,c.getKeyword());
	}
	
	@Test
	public void test_getUrl() {
		c.setSite("www.163.com");
		c.setId(100L);
		c.setDateCreated(DateConvertUtils.parse("20130206", "yyyyMMdd"));
		assertEquals(c.getUrl(),"http://www.163.com/content/20130206/100.do"); 
	}
	
	
	@Test
	public void test_getTags() {
		c.setTags("abc,123");
		assertTrue(c.getTagSet().contains("abc"));
		assertTrue(c.getTagSet().add("blog"));
		assertEquals(c.getTags(),"abc,123,blog");
		
		c.setTags(null);
		assertEquals(c.getTags(),"");
	}
	
	
}
