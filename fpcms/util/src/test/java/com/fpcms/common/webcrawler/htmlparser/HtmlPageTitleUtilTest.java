package com.fpcms.common.webcrawler.htmlparser;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;


public class HtmlPageTitleUtilTest extends Assert{

	@Test
	public void test() {
		assertEquals("android - Get Drawable from image view",HtmlPageTitleUtil.smartGetTitle("android - Get Drawable from image view - Stack Overflow"));
		assertEquals("Anti-rape ordinance a landmark decision, says Union Law Minister : North, News",HtmlPageTitleUtil.smartGetTitle("Anti-rape ordinance a landmark decision, says Union Law Minister : North, News - India Today"));
		assertEquals("“倾向于原则适用统一赔偿标准”",HtmlPageTitleUtil.smartGetTitle("“倾向于原则适用统一赔偿标准”_网易新闻中心"));
		
		
		assertEquals("SEO经验及教训",HtmlPageTitleUtil.smartGetTitle("SEO经验及教训 - badqiu - ITeye技术网站"));
		assertEquals("穆沙拉夫称， 1999年的卡吉尔操作是一个巨大的成功，为白军",HtmlPageTitleUtil.smartGetTitle("穆沙拉夫称， 1999年的卡吉尔操作是一个巨大的成功，为白军：巴基斯坦新闻"));
		assertEquals("多省市出台改作风规定 要求领导乘国产品牌汽车",HtmlPageTitleUtil.smartGetTitle("多省市出台改作风规定 要求领导乘国产品牌汽车_网易新闻中心"));
		
		assertEquals("图片故事-赵本山曝光退出春晚的本相：具有挖苦意味的​​是难以总结市长过不去",HtmlPageTitleUtil.smartGetTitle("图片故事-赵本山曝光退出春晚的本相：具有挖苦意味的​​是难以总结市长过不去 - 河北网"));
	}
	
	@Test
	public void test2() {
		assertEquals("SEO经验及教训总结",HtmlPageTitleUtil.filterWithMaxLength("SEO经验及教训总结 - badqiu - a技术网站"));
		
		assertEquals("c123456789中",HtmlPageTitleUtil.filterWithMaxLength("c123456789中 - a123国"));
	}
	
	
}
