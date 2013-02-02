package com.fpcms.common.webcrawler.htmlparser;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fpcms.common.util.NetUtil;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;

public class SinglePageCrawler {
	
	
	private String url;
	private String[] acceptUrlRegex;
	private String[] excludeUriRegex;
	private String translateSourceLang;
	private String translateTargetLang;
	private String[] mainContentSelector;
	
	public SinglePageCrawler(String url) {
		super();
		this.url = url;
	}

	public void setTranslateSourceLang(String translateSourceLang) {
		this.translateSourceLang = translateSourceLang;
	}

	public void setTranslateTargetLang(String translateTargetLang) {
		this.translateTargetLang = translateTargetLang;
	}

	public void setAcceptUrlRegex(String... acceptUrlRegex) {
		this.acceptUrlRegex = acceptUrlRegex;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setMainContentSelector(String... mainContentSelector) {
		this.mainContentSelector = mainContentSelector;
	}

	public void execute() throws MalformedURLException, IOException {
		String content = NetUtil.httpGet(url);
		Document doc = Jsoup.parse(content);
		Elements elements = doc.getElementsByTag("a");
		for(Element anchor : elements) {
			String href = anchor.attr("href");
			String text = StringUtils.trim(anchor.text());
			String title = anchor.attr("title");
			Anchor a = new Anchor();
			a.setHref(href);
			a.setText(text);
			a.setTitle(title);
			
			if(isAcceptUrl(a.getHref())) {
				extractArticleByJsoup(a);
			}
		}
	}
	
	private void extractArticleByJsoup(Anchor anchor) throws IOException {
		Connection conn = Jsoup.connect(anchor.getHref());
		conn.userAgent("Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)");
		
		Document doc = conn.get();
		String title = extrectMainTitle(doc.getElementsByTag("title").text());
		String keywords = select(doc,"[name=keywords]").attr("content");
		String description = select(doc,"[name=description]").attr("content");
		String content = select(doc,mainContentSelector).text();
		
		HtmlPage page = new HtmlPage();
		page.setAnchor(anchor);
		page.setContent(content);
		page.setDescription(description);
		page.setKeywords(keywords);
		page.setTitle(title);
		
		System.out.println("url:"+page.getAnchor().getHref());
		System.out.println("title:"+page.getTitle());
		System.out.println("keywords:"+page.getKeywords());
		System.out.println("description:"+page.getDescription());
		System.out.println("content:"+page.getContent());
	}

	private static Elements select(Document doc,String... selectors) {
		for(String selector : selectors) {
			Elements elements = doc.select(selector);
			if(elements.isEmpty()) {
				continue;
			}
			return elements;
		}
		return new Elements();
	}

	private static char[] titleSeperator = {':','-','_','|'};
	private static String extrectMainTitle(String title) {
		title = title.trim();
		for(char c : titleSeperator) {
			int indexOf = title.indexOf(c);
			if(indexOf >= 0) {
				return title.substring(0,indexOf - 1);
			}
		}
		return title;
	}

	private boolean isAcceptUrl(String href) {
		if(StringUtils.isBlank(href)) {
			return false;
		}
		if(acceptUrlRegex == null) {
			return true;
		}
		
		for(String exclude : excludeUriRegex) {
			if(StringUtils.isNotBlank(exclude)) {
				if(href.matches(exclude)) {
					return false;
				}
			}
		}
		
		for(String accept : acceptUrlRegex) {
			if(StringUtils.isNotBlank(accept)) {
				if(href.matches(accept)) {
					return true;
				}
			}
		}
		
		return false;
	}

}
