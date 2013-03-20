package com.fpcms.common.webcrawler.htmlparser;

import org.junit.Assert;
import org.junit.Test;

import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;


public class HtmlPageTest extends Assert{
	@Test
	public void test_getRootBaseUrl() {
		assertEquals(null,Anchor.getRootBaseUrl(null));
		assertEquals(null,Anchor.getRootBaseUrl(" "));
		assertEquals("http://www.163.com",Anchor.getRootBaseUrl("http://www.163.com/123123/123.html"));
	}
	
	@Test
	public void test_toFullUrl() {
		assertEquals("http://www.163.com/abc/1.txt",Anchor.toFullUrl("http://www.163.com/news/", "/abc/1.txt"));
		assertEquals("http://www.163.com/news/abc/1.txt",Anchor.toFullUrl("http://www.163.com/news/", "abc/1.txt"));
		
		assertEquals("http://www.163.com/abc/1.txt",Anchor.toFullUrl("http://www.163.com/news", "/abc/1.txt"));
		assertEquals("http://www.163.com/news/abc/1.txt",Anchor.toFullUrl("http://www.163.com/news", "abc/1.txt"));
	}
	
	@Test
	public void test_anchor_toString() {
		assertEquals(new Anchor("http://www.163.com","text","title").toString(),"<a href='http://www.163.com' title='title'>text</a>");
		assertEquals(new Anchor("http://www.163.com","text",null).toString(),"<a href='http://www.163.com'>text</a>");
		assertEquals(new Anchor("http://www.163.com",null,null).toString(),"<a href='http://www.163.com'>http://www.163.com</a>");
	}
}
