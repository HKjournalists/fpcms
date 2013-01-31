package com.fpcms.common.random_gen_article;

import org.junit.Assert;
import org.junit.Test;


public class ArticleContentProcesserTest extends Assert{
	ArticleContentProcesser gen = new ArticleContentProcesser("好人");
	@Test
	public void test() {
		String input = "中国人民中国人民中国人民中国人民,好发票好发票好发票好发票好发票,929282,浙江浙江浙江浙江浙江";
		gen.buildArticle(input);
		String output = gen.getArticle();
		assertFalse(input.equals(output));
		assertFalse(output.length() < input.length() );
		System.out.println(output);
	}
	
	@Test
	public void test_mobile() {
		assertTrue(gen.isValidToken("中国中国中国1312834828中国中国中国中国中国中国"));
		assertTrue(gen.isValidToken("2012中1国101111"));
		assertTrue(gen.isValidToken("2012月10年10日"));
		assertFalse(gen.isValidToken("1312834828"));
		assertFalse(gen.isValidToken("1312834828"));
		assertFalse(gen.isValidToken("013128348282"));
		assertFalse(gen.isValidToken("13128348282"));
		assertFalse(gen.isValidToken("中国13128348282中国中国中国中国"));
		assertFalse(gen.isValidToken("中国13128348282中国中国中国中国中国中国中国"));
		
		assertFalse(gen.isValidToken("2012年1月"));
		assertFalse(gen.isValidToken("2012年1月5日"));
		assertFalse(gen.isValidToken("2012年10月5日"));
	}
	
}
