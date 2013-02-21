package com.fpcms.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class KeywordUtilTest extends Assert{

	@Test
	public void getCommonSymbolsCount() {
		assertEquals(3,KeywordUtil.getCommonSymbolsCount("中国,是个.；"));
		assertEquals(2,KeywordUtil.getCommonSymbolsCount("中国,是个."));
		assertEquals(0,KeywordUtil.getCommonSymbolsCount(null));
		assertEquals(0,KeywordUtil.getCommonSymbolsCount("   "));
		assertEquals(0,KeywordUtil.getCommonSymbolsCount("  中国  "));
	}
	
	
	@Test
	public void print() {
		for(int i = 0; i < 256; i++) {
			System.out.println(i+"="+(char)i);
		}
		System.out.println("--------------------------------");
		char c = '！';
		for(int i = c; i < c+256; i++) {
			System.out.println(i+"="+(char)i);
		}
		
	}
	
	@Test
	public void test_getPerfectKeyword1() {
		String result = KeywordUtil.getPerfectKeyword("1234123,中国,中国人民银行,191811", "中国");
		assertEquals("中国人民银行",result);
		
		result = KeywordUtil.getPerfectKeyword("1234123,中国,中国人民银行,191811", "hibernate");
		assertEquals(null,result);
		
		result = KeywordUtil.getPerfectKeyword("1234123,中国,中国人民银行,191811", null);
		assertEquals(null,result);
	}
	
	@Test
	public void test_getPerfectKeyword() {
		String str = KeywordUtil.getPerfectKeyword("com/2012/06/1老妇疑吃方便面中毒身亡/", "老妇");
		System.out.println(str);
		assertEquals("1老妇疑吃方便面中毒身亡",str);
		
		System.out.println(KeywordUtil.toTokenizerList("lq=末日走你"));
		assertEquals("[lq, 末日走你]",KeywordUtil.toTokenizerList("lq=末日走你").toString());
	}
	
	@Test
	public void test_isSensitiveKeyword() {
		assertTrue(KeywordUtil.isSensitiveKeyword("乳交"));
		assertFalse(KeywordUtil.isSensitiveKeyword("xxxxx"));
	}
	
	@Test
	public void test_filterSensitiveKeyword() {
		List<String> list = new ArrayList(Arrays.asList("乳交","xxxx"));
		KeywordUtil.filterSensitiveKeyword(list);
		assertEquals("[xxxx]",list.toString());
	}
	
	@Test
	public void test_getMaxRank() {
		int rank = KeywordUtil.getMaxRank("唐山开发票,唐山代开发票,唐山发票","www.aaafaipiao.com");
		assertTrue(rank > 0);
		
	}
	
	@Test
	public void test_isNameKeyword() {
		assertTrue(KeywordUtil.isNameKeyword("中国"));
		assertFalse(KeywordUtil.isNameKeyword("中国的"));
		assertFalse(KeywordUtil.isNameKeyword("我的"));
	}
}
