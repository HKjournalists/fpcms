package com.fpcms.common.random_gen_article;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.fpcms.common.util.ChineseSegmenterUtil;
import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.NetUtil;

public class RandomArticleBuilderTest extends Assert{

	RandomArticleBuilder b = new RandomArticleBuilder();
	
	@Test
	public void test_getRandomMainKeyword() {
		RandomArticleBuilder builder = new RandomArticleBuilder();
		for(int i = 0; i < 100; i++) {
			String randomMainKeyword = builder.getRandomMainKeyword();
			assertFalse(randomMainKeyword.isEmpty());
			assertTrue(randomMainKeyword.length() >= 2);
		};
	}
	
	@Test
	public void test() {
		RandomArticleBuilder builder = new RandomArticleBuilder();
		RandomArticle a = builder.buildRandomArticle("中文");
		assertTrue("a.getContent().length() > 400 is false,length"+a.getContent().length()+" content:"+a.getContent(),a.getContent().length() > 400);
		System.out.println(a.getPerfectKeyword()+" -------- size:"+a.getContent()+" " +a.getContent());
	}
	
	@Test
	public void test2_getValidTokens() {
		String result = NetUtil.httpGet("http://news.qq.com");
		ArrayList<String> tokenizerList = KeywordUtil.toTokenizerList(result);
		String tokens = StringUtils.join(tokenizerList,",");
		Set<String> tokenSet = new ArticleContentProcesser("").getValidTokens(tokens);
		System.out.println("valid tokens:"+tokenSet);
		assertTrue(tokenSet.size() > 10);
		
		Map<String,Integer> keywords = ChineseSegmenterUtil.segmenteForTokenCount(StringUtils.join(tokenSet, ","),true);
		System.out.println("segmenteForTokenCount:"+keywords);
		Set<String> lengthKeywords = new LinkedHashSet();
		for(String keyword : keywords.keySet()) {
			if(keyword.length() > 3) {
				lengthKeywords.add(keyword);
			}
		}
		assertTrue(lengthKeywords.size() > 10);
		System.out.println("lengthKeywords:"+lengthKeywords);
	}
	
	@Test
	public void test_chinese_segment() {
		RandomArticleBuilder builder = new RandomArticleBuilder();
		String finalSearchKeyword = "推病妻下河致死";
		RandomArticle a = builder.buildBySearchKeyword("唐山开发票", "发票", finalSearchKeyword,finalSearchKeyword);
		assertTrue("a.getContent().length() > 400 is false,length"+a.getContent().length()+" content:"+a.getContent(),a.getContent().length() > 400);
		System.out.println(a.getPerfectKeyword()+" -------- "+a.getContent());
		assertTrue(a.getContent().length() > 100);
	}
	
	@Test
	public void test_random_month() {
		for(int i = 0; i < 1000; i++) {
			String str = b.randomMonth();
			if(str.isEmpty()) {
				continue;
			}
			assertTrue(str.matches("\\d{4}年\\d{1,2}月"));
			System.out.println(str);
		}
	}

}
