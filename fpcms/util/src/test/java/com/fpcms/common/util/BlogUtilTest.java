package com.fpcms.common.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;


public class BlogUtilTest extends Assert{
	
	@Test
	public void test_getValidBlogLinks() {
		printAndAssert("http://www.blogjava.net/badqiu",false);
		printAndAssert("http://blog.sina.com.cn/u/3225400392",false);
//		printAndAssert("http://sh292did.blog.163.com/",true);
//		printAndAssert("http://blog.csdn.net/fpqqchao2",true);
		printAndAssert("http://www.cnblogs.com/fpqqchao/",true);
	}

	private void printAndAssert(String blogUrl,boolean ping) {
		List<Anchor> list = BlogUtil.getValidBlogLinks(blogUrl, 8);
		assertFalse(blogUrl+" not found any blog to ping",list.isEmpty());
		for(Anchor a : list) {
			if(ping) {
				BlogPingUtil.baiduPing(blogUrl, blogUrl, a.getHref(), "");
			}
			System.out.println(a);
		}
	}
}
