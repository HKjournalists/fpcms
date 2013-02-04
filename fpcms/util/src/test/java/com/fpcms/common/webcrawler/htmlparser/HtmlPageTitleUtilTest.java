package com.fpcms.common.webcrawler.htmlparser;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;


public class HtmlPageTitleUtilTest extends Assert{

	@Test
	public void test() {
		assertEquals("Anti-rape ordinance a landmark decision, says Union Law Minister : North, News",HtmlPageTitleUtil.smartGetTitle("Anti-rape ordinance a landmark decision, says Union Law Minister : North, News - India Today"));
		assertEquals("“倾向于原则适用统一赔偿标准”",HtmlPageTitleUtil.smartGetTitle("“倾向于原则适用统一赔偿标准”_网易新闻中心"));
		
		
		assertEquals("SEO经验及教训",HtmlPageTitleUtil.smartGetTitle("SEO经验及教训 - badqiu - ITeye技术网站"));
		assertEquals("多省市出台改作风规定 要求领导乘国产品牌汽车",HtmlPageTitleUtil.smartGetTitle("多省市出台改作风规定 要求领导乘国产品牌汽车_网易新闻中心"));
	}
}
