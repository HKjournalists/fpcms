package com.fpcms.common.webcrawler.htmlparser;


public class HtmlContentExtracter {

	public static void main(String[] args) throws Exception {
		SinglePageCrawler c = new SinglePageCrawler();
		c.setSourceLang("en"); 
		
//		SinglePageCrawler c = new SinglePageCrawler("http://www.baidu.com/s?wd=%E4%B8%AD%E5%9B%BD&");
//		c.setAcceptUrlRegexList(".*url.*");
//		c.setMainContentSelector("#endText");
		
//		c.setUrl("http://www.iteye.com/blogs");
//		c.setAcceptUrlRegexList(".*blog.*");
//		c.setMainContentSelector("#blog_content");
		
//		c.setUrl("http://news.163.com");
//		c.setAcceptUrlRegexList(".*/13/.*.html");
//		c.setMainContentSelector("#endText");
		
		c.setUrlList("http://indiatoday.intoday.in/news.html");
		c.setAcceptUrlRegexList(".*/story/.*.html\\?.*");
		c.setMainContentSelector(".fullstorytext","#storybody");
		
		c.execute();
		
		
	}
	
}
