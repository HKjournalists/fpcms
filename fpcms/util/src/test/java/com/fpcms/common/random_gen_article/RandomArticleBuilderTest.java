package com.fpcms.common.random_gen_article;

import org.apache.shiro.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class RandomArticleBuilderTest extends Assert{

	RandomArticleBuilder b = new RandomArticleBuilder();
	@Test
	public void test() {
		RandomArticleBuilder builder = new RandomArticleBuilder();
		RandomArticle a = builder.buildRandomArticle("中文");
		assertTrue("a.getContent().length() > 400 is false,length"+a.getContent().length()+" content:"+a.getContent(),a.getContent().length() > 400);
		System.out.println(a.getPerfectKeyword()+" -------- size:"+a.getContent()+" " +a.getContent());
	}
	
	@Test
	public void test_chinese_segment() {
		RandomArticleBuilder builder = new RandomArticleBuilder();
		String finalSearchKeyword = "推病妻下河致死";
		RandomArticle a = builder.buildBySearchKeyword("唐山开发票", "发票", finalSearchKeyword,finalSearchKeyword);
		assertTrue("a.getContent().length() > 400 is false,length"+a.getContent().length()+" content:"+a.getContent(),a.getContent().length() > 400);
		System.out.println(a.getPerfectKeyword()+" -------- "+a.getContent());
	}
	@Test
	public void test_random_month() {
		for(int i = 0; i < 1000; i++) {
			String str = b.randomMonth();
			System.out.println(str);
		}
	}
	
	@Test
	public void test_getRandomInsertKeyword() {
		int count = 0;
		for(int i = 0; i < 100; i++) {
			String abc = b.getRandomInsertKeyword("浙江");
			if(StringUtils.hasText(abc)) {
				count++;
			}
			System.out.println(abc);
		}
		assertTrue(count > 34);
	}
}
