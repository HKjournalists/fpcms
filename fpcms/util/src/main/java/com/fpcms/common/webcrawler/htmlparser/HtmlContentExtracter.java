package com.fpcms.common.webcrawler.htmlparser;


public class HtmlContentExtracter {

	public static void main(String[] args) throws Exception {
		SinglePageCrawler c = new SinglePageCrawler();
		
//		SinglePageCrawler c = new SinglePageCrawler("http://www.baidu.com/s?wd=%E4%B8%AD%E5%9B%BD&");
//		c.setAcceptUrlRegex(".*url.*");
//		c.setMainContentSelector("#endText");
		
//		c.setUrl("http://www.iteye.com/blogs");
//		c.setAcceptUrlRegex(".*blog.*");
//		c.setMainContentSelector("#blog_content");
		
		c.setUrl("http://news.163.com");
		c.setAcceptUrlRegex(".*/13/.*.html");
		c.setMainContentSelector("#endText");
		
//		c.setUrl("http://indiatoday.intoday.in/news.html");
//		c.setAcceptUrlRegex(".*/story/.*.html");
//		c.setMainContentSelector(".fullstorytext","#storybody");
		
		c.setTranslateSourceLang("en");
		c.setTranslateTargetLang("zh-CN");
		c.execute();
		
		
	}
	
}
