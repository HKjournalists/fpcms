package com.fpcms.common.util;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;


public class HttpStatusCheckUtilTest extends Assert{

	@Test
	public void test_getHttpResponseCode() throws IOException {
		assertEquals(200,HttpStatusCheckUtil.getHttpResponseCode("http://news.163.com"));
		assertEquals(404,HttpStatusCheckUtil.getHttpResponseCode("http://news.163.com/292828/29823.html"));
		assertEquals(301,HttpStatusCheckUtil.getHttpResponseCode("http://www.gzsd.aaafaipiao.com"));
	}
	
	@Test(expected=IOException.class)
	public void test_getHttpResponseCode_exception() throws IOException {
		assertEquals(404,HttpStatusCheckUtil.getHttpResponseCode("http://news.aa1212notexist.com/292828/29823.html"));
	}
	
	@Test
	public void test_isSuccess() {
		assertTrue(HttpStatusCheckUtil.isHttpSuccess("200"));
		assertTrue(HttpStatusCheckUtil.isHttpSuccess("302"));
		assertTrue(HttpStatusCheckUtil.isHttpSuccess("301"));
		
		assertFalse(HttpStatusCheckUtil.isHttpSuccess(null));
		assertFalse(HttpStatusCheckUtil.isHttpSuccess("error"));
		assertFalse(HttpStatusCheckUtil.isHttpSuccess("400"));
		assertFalse(HttpStatusCheckUtil.isHttpSuccess("500"));
	}
	
}
