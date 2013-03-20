package com.fpcms.common.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;
import com.fpcms.common.webcrawler.htmlparser.SinglePageCrawler;

public class BlogUtil {
	private static Logger logger = LoggerFactory.getLogger(BlogUtil.class);
	/**
	 * 根据blogUrl得到有效的blog文章URL
	 * @param blogUrl
	 * @param expectedLinkChineseCount 期待的url文字链接数
	 * 
	 * @return
	 */
	public static List<Anchor> getValidBlogLinks(String blogUrl,int expectedLinkChineseCount) {
		SinglePageCrawler singlePageCrawler = new SinglePageCrawler();
		singlePageCrawler.setAcceptUrlRegexList(".*"+blogUrl+".*");
		
		List<Anchor> filtered = new ArrayList<Anchor>();
		List<Anchor> anchorList = singlePageCrawler.getShoudVisitAnchorList(blogUrl);
		for(Anchor a : anchorList) {
			if(TextLangUtil.chineseCount(a.getText()) >= expectedLinkChineseCount) {
				filtered.add(a);
			}
		}
		return filtered;
	}
	
	public static List<Anchor> pingAllBlog(String blogUrl) {
		List<Anchor> successList = new ArrayList<Anchor>();
		List<Anchor> list = BlogUtil.getValidBlogLinks(blogUrl, 8);
		for(Anchor a : list) {
			try {
				BlogPingUtil.baiduPing(blogUrl, blogUrl, a.getHref(), "");
				successList.add(a);
			}catch(Exception e) {
				logger.error("error_on_ping_blog_url:"+a);
			}
		}
		return successList;
	}
	
}
