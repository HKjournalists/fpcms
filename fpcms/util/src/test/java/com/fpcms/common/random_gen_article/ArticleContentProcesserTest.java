package com.fpcms.common.random_gen_article;

import org.junit.Assert;
import org.junit.Test;

import com.fpcms.common.random_gen_article.ArticleContentProcesser;


public class ArticleContentProcesserTest extends Assert{
	ArticleContentProcesser gen = new ArticleContentProcesser("中国");
	@Test
	public void test() {
		String input = "中国人民中国人民中国人民中国人民,好发票好发票好发票好发票好发票,929282,浙江浙江浙江浙江浙江";
		String output = gen.buildArticle(input);
		assertFalse(input.equals(output));
		assertFalse(output.length() < input.length() );
		System.out.println(output);
	}
	
}
