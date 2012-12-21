package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class RssArticleGeneratorTest extends Assert{
	RssArticleGenerator gen = new RssArticleGenerator();
	@Test
	public void test() {
		String string = gen.buildArticle();
		System.out.println(string);
	}
	
}
