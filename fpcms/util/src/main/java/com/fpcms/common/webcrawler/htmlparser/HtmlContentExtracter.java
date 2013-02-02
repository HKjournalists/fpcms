package com.fpcms.common.webcrawler.htmlparser;


public class HtmlContentExtracter {

	public static void main(String[] args) throws Exception {
//		SinglePageCrawler c = new SinglePageCrawler("http://news.163.com");
//		c.setAcceptUrlRegex(".*/13/.*.html");
//		c.setMainContentSelector("#endText");
		SinglePageCrawler c = new SinglePageCrawler("http://indiatoday.intoday.in/news.html");
		c.setAcceptUrlRegex(".*/story/.*.html");
		c.setMainContentSelector(".fullstorytext","#storybody");
		c.setTranslateSourceLang("en");
		c.setTranslateTargetLang("zh-CN");
		c.execute();
	}
	
}
