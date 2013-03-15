package com.fpcms.common.webcrawler.htmlparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.mockito.Mockito;

import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;


public class SinglePageCrawlerTest extends Mockito{

	SinglePageCrawler c = new SinglePageCrawler();
	@Test
	public void test_extractArticleByJsoup() throws IOException {
		Anchor anchor = newAnchor("http://badqiu.iteye.com/blog/1776090");
		HtmlPage page = c.extractArticleByJsoup(anchor);
		assertTrue(page.getContent().contains("合理的内链,便于蜘蛛爬行,如文章的:上一篇,下一篇"));
		assertTrue(page.getContent().contains("纯文本链接格式: 锚文本http://www.iteye.com"));
		
		assertTrue(page.getKeywords().equals("SEO经验及教训"));
		assertTrue(page.getDescription().contains("合理的内链,便于蜘蛛爬行,如文章的:上一篇,下一篇"));
		assertTrue(page.getTitle().equals("SEO经验及教训"));
		
	}
	
	@Test
	public void test_extractArticleByJsoup2() throws IOException {
		Anchor anchor = newAnchor("http://news.163.com/13/0204/02/8MR9K0MR00014AED.html");
		HtmlPage page = c.extractArticleByJsoup(anchor);
		assertTrue(page.getContent().contains("汽车行业内一直呼吁的“省部级领导带头乘坐自主品牌汽车”"));
		assertTrue(page.getContent().contains("否则在用车辆直接淘汰，会造成资源浪费。"));
		
		assertTrue(page.getKeywords().equals("国产,公车,官员"));
		assertTrue(page.getDescription().contains("核心提示：近日，宁夏、湖南、甘肃、新疆等多省党委政府规定："));
		assertEquals(page.getTitle(),"多省市出台改作风规定 要求领导乘国产品牌汽车");
		
	}
	
	@Test
	public void test_getShoudVisitAnchorList() throws IOException {
		c.setAcceptUrlRegexList(".*/\\d{2}/\\d{4}/\\d{2}/.*.html\\??.*");
		List<Anchor> list = c.getShoudVisitAnchorList("http://news.163.com");
		System.out.println(StringUtils.join(list,"\n"));
		assertTrue(list.size() > 50);
	}

	@Test
	public void test_getShoudVisitAnchorList_yahoo() throws IOException {
		c.setAcceptUrlRegexList("http://.*.yahoo.com/.*-\\d{6,}.html.*");
		List<Anchor> list = c.getShoudVisitAnchorList("http://news.yahoo.com/");
		System.out.println(StringUtils.join(list,"\n"));
		assertTrue(list.size() > 20);
	}
	
	@Test
	public void test() throws IOException {
		HtmlPage page = c.extractArticleByJsoup(newAnchor("http://blogs.wsj.com/venturecapital/2013/03/14/mobeam-brings-digital-coupons-to-new-samsung-phone/"));
		System.out.println(page.getContent());
	}
	
	
	
	@Test
	public void test_isAcceptUrl() throws IOException {
		assertFalse(c.isAcceptUrl(null));
		assertFalse(c.isAcceptUrl("   "));
		assertFalse(c.isAcceptUrl("httpbs://www.163.com"));
		
		c.setAcceptUrlRegexList(".*163.com.*",".*sina.com.*");
		assertTrue(c.isAcceptUrl("https://www.163.com/news/111.html"));
		assertTrue(c.isAcceptUrl("https://www.sina.com/news/111.html"));
		assertFalse(c.isAcceptUrl("https://www.qq.com/news/111.html"));
		
		c.setExcludeUriRegexList(".*blog/111.html");
		assertFalse(c.isAcceptUrl("https://www.163.com/blog/111.html"));
	}
	
	private Anchor newAnchor(String url) {
		Anchor anchor = new Anchor();
		anchor.setHref(url);
		anchor.setText(url);
		return anchor;
	}
	
}
