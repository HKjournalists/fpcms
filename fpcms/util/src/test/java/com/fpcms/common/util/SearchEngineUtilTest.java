package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class SearchEngineUtilTest extends Assert{

	@Test
	public void test() {
		String result = SearchEngineUtil.sogouSearch("中央银行  +发票 ", 60, 1);
		assertTrue("result.length() > 1000 is false,length:"+result.length()+" result:"+result,result.length() > 1000);
	}
	
	@Test(expected=RuntimeException.class)
	public void test_exception() {
		SearchEngineUtil.sogouSearch("iusifugswfiufg9asfkgjsfdg 2928348 lzsdlk oiutwioue ", 60, 1);
	}
}
