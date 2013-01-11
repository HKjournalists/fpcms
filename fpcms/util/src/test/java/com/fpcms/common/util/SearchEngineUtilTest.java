package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class SearchEngineUtilTest extends Assert{

	@Test
	public void test_baiduKeywordRank() {
		for(int i = 0; i < 3; i++) {
			int rank = SearchEngineUtil.baiduKeywordRank("唐山代开发票", "www.aaafaipiao.com");
			assertTrue(rank > 0);
			System.out.println(rank);
		}
		int rank = SearchEngineUtil.baiduKeywordRank("安徽开发票", "www.aaafaipiao.com");
		assertTrue(rank <= 0);
		
	}
	
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
	
	@Test
	public void test_baidu_site_count() {
		int count = SearchEngineUtil.baiduSiteCount("aaafaipiao.com");
		System.out.println("baiduSiteCount:"+count);
		assertTrue(count > 0);
		
		int baiduRecentlySiteCount = SearchEngineUtil.baiduRecentlySiteCount("aaafaipiao.com");
		System.out.println("baiduSiteCount:"+baiduRecentlySiteCount);
		assertTrue(baiduRecentlySiteCount > 0);
	}
	
}
