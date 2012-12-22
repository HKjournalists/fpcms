package com.fpcms.common.random_gen_article;

import org.junit.Assert;
import org.junit.Test;

import com.fpcms.common.random_gen_article.NaipanArticleGeneratorUtil;


public class NaipanArticleGeneratorUtilTest extends Assert{

	@Test
	public void test() {
		String content = NaipanArticleGeneratorUtil.transformArticle("新版奶盘seo伪原创采用java技术开发");
		System.out.println(content);
		assertEquals("新版奶盘搜索引擎优化伪自创选用java技术开发",content);
		
		content = NaipanArticleGeneratorUtil.transformArticle("新版奶盘seo伪原创采用java技术开发<h3>11111111111</h3> 33333333");
		System.out.println(content);
		assertEquals("新版奶盘搜索引擎优化伪自创选用java技术开发<h3>11111111111</h3> 33333333",content);
	}
	
}
