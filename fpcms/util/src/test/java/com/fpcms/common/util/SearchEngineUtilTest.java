package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class SearchEngineUtilTest extends Assert{

	@Test
	public void test_sogouSearch() {
		String result = SearchEngineUtil.sogouSearch("中央银行  +发票 ", 10, 1);
		assertTrue("result.length() > 1000 is false,length:"+result.length()+" result:"+result,result.length() > 1000);
		System.out.println(result.length());
	}
	
	@Test
	public void test_sosoSearch() {
		String result = SearchEngineUtil.sosoSearch("中央银行  +发票 ", 1);
		assertTrue("result.length() > 1000 is false,length:"+result.length()+" result:"+result,result.length() > 1000);
		System.out.println(result.length());
	}
	
	@Test(expected=RuntimeException.class)
	public void test_sogouSearch_exception() {
		SearchEngineUtil.sogouSearch("iusifugswfiufg9asfkgjsfdg 2928348 lzsdlk oiutwioue ", 60, 1);
	}
	
	@Test(expected=RuntimeException.class)
	public void test_sosoSearch_exception() {
		SearchEngineUtil.sosoSearch("sdkdo2342342kch7HJHGNF34FDHDasdfasds23423cdKLUIfau", 1);
	}
}
