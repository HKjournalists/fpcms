package com.fpcms.common.util;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class SearchEngineUtilTest extends Assert{

	@Test
	public void test_baiduKeywordRank() {
		for(int i = 0; i < 1; i++) {
			int rank = SearchEngineUtil.baiduKeywordRank("唐山代开发票", "www.aaafaipiao.com");
			assertTrue(rank > 0);
			System.out.println(rank);
		}
		
		int rank = SearchEngineUtil.baiduKeywordRank("安徽开发票", "www.aaafaipiao.com");
		assertTrue(rank <= 0);
		
	}

	@Test
	public void test_baiduKeywordsRank() {
		Map<String,Integer> map = SearchEngineUtil.baiduKeywordsRank("唐山代开发票,唐山发票,唐山开发票,ABC代开发票", "www.aaafaipiao.com");
		System.out.println(map);
		assertTrue(!map.isEmpty());
		
		Map<String,Integer> empty = SearchEngineUtil.baiduKeywordsRank("安徽代开发票,安徽发票,安徽开发票", "www.aaafaipiao.com");
		System.out.println(empty);
		assertTrue(empty.isEmpty());
	}
	
	@Test
	public void test_googleSearch() {
		String string = SearchEngineUtil.googleSearch("中央银行",100,1);
		System.out.println("googleSearch:"+string);
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
		
		count = SearchEngineUtil.baiduSiteCount("news.163.com");
		System.out.println("baiduSiteCount:"+count);
		assertTrue(count > 1000000);
	}
	
}
