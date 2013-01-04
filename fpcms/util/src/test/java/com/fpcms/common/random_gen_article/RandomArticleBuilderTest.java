package com.fpcms.common.random_gen_article;

import org.junit.Assert;
import org.junit.Test;

public class RandomArticleBuilderTest extends Assert{

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
		RandomArticleBuilder b = new RandomArticleBuilder();
		for(int i = 0; i < 1000; i++) {
			String str = b.randomMonth();
			System.out.println(str);
		}
	}
	
}
