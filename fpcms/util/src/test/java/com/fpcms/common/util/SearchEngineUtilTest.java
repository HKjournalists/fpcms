package com.fpcms.common.util;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


public class SearchEngineUtilTest extends Assert{

	@Test(expected=EmptySearchResultException.class)
	public void test_baiduSearch() {
		SearchEngineUtil.baiduSearch("\"百度推广青岛胶东航母基地\"", 100, 1);
	}
	
	@Test
	public void baiduKeywordsNotExist() {
		assertFalse(SearchEngineUtil.baiduKeywordsNotExist("java 判断是否有中文,以及它的位置"));
		assertTrue(SearchEngineUtil.baiduKeywordsNotExist("张骥辞任广州市副市长同意被转移到家庭计划局局长-大洋网"));
	}
	
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
		
		
		Map<String,Integer> map2 = SearchEngineUtil.baiduKeywordsRank("南昌代开发票,南昌发票,南昌开发票", "www.fpnanchang.com");
		System.out.println(map2);
		assertTrue(!map2.isEmpty());
		
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
