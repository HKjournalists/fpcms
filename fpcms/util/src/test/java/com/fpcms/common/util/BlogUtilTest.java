package com.fpcms.common.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;


public class BlogUtilTest extends Assert{
	
	@Test
	public void test_getValidBlogLinks() {
		String blogUrl = "http://www.blogjava.net/badqiu";
		printAndAssert(blogUrl);
		printAndAssert("http://blog.sina.com.cn/u/3225400392");
//			BlogPingUtil.baiduPing(blogUrl, blogUrl, a.getHref(), "");
	}

	private void printAndAssert(String blogUrl) {
		List<Anchor> list = BlogUtil.getValidBlogLinks(blogUrl, 8);
		assertFalse(blogUrl+" not found any blog to ping",list.isEmpty());
		for(Anchor a : list) {
			System.out.println(a);
		}
	}
}
