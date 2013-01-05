package com.fpcms.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.fpcms.common.random_gen_article.ArticleContentProcesser;

public class KeywordUtilTest extends Assert{

	@Test
	public void test2() {
		String result = NetUtil.httpGet("http://news.qq.com");
		ArrayList<String> tokenizerList = KeywordUtil.toTokenizerList(result);
		String tokens = StringUtils.join(tokenizerList,",");
		Set<String> tokenSet = new ArticleContentProcesser("","").getValidTokens(tokens);
		System.out.println("valid tokens:"+tokenSet);
		
		Map<String,Integer> keywords = ChineseSegmenterUtil.segmenteForTokenCount(StringUtils.join(tokenSet, ","),true);
		System.out.println("segmenteForTokenCount:"+keywords);
		Set<String> lengthKeywords = new LinkedHashSet();
		for(String keyword : keywords.keySet()) {
			if(keyword.length() > 3) {
				lengthKeywords.add(keyword);
			}
		}
		System.out.println("lengthKeywords:"+lengthKeywords);
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
	public void test() {
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
}
