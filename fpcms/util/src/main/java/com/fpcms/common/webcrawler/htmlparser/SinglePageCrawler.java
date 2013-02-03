package com.fpcms.common.webcrawler.htmlparser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.NetUtil;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;

public class SinglePageCrawler {
	
	
	private String url;
	private String[] acceptUrlRegex;
	private String[] excludeUriRegex;
	private String translateSourceLang;
	private String translateTargetLang;
	private String[] mainContentSelector;
	
	public SinglePageCrawler() {
	}
	
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
			
			String fullHref = Anchor.toFullUrl(url,href);
			a.setHref(fullHref);
			a.setText(text);
			a.setTitle(title);
			
			if(isAcceptUrl(a.getHref())) {
				extractArticleByJsoup(a);
			}
		}
	}

	
	private void extractArticleByJsoup(Anchor anchor) throws IOException {
		try {
			
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
			
			Element maxLength = smartGetMainContent(doc);
			
			System.out.println("maxLengthElement.text:" + (maxLength == null ? "NOT_FOUND" : maxLength.text()));
			System.out.println("url:"+page.getAnchor().getHref());
			System.out.println("title:"+page.getTitle());
			System.out.println("keywords:"+page.getKeywords());
			System.out.println("description:"+page.getDescription());
			System.out.println("content:"+page.getContent());
			System.out.println("content.deepLevel:"+select(doc,mainContentSelector).parents().size());
			
			if(maxLength != null) {
				if(!maxLength.text().equals(page.getContent())) {
					System.out.println("-------------------error: smart max length text != selector["+StringUtils.join(mainContentSelector,",")+"] text----------------------");
				}
			}
		}catch(Exception e) {
			throw new RuntimeException("error on process anchor:"+anchor,e);
		}
	}

	private Element smartGetMainContent(Document doc) {
		Elements div = select(doc,"div");
		
		List<Element> allDiv = new ArrayList<Element>();
		for(Element element : div) {
			allDiv.add(element);
		}
		Collections.sort(allDiv,new Comparator<Element>() {
			@Override
			public int compare(Element o1, Element o2) {
				int n1 = o1.parents().size();
				int n2 = o2.parents().size();
				return - new Integer(n1).compareTo(n2);
			}
		});
		
		List<Element> lengthValid = new ArrayList<Element>();
		for(Element element : allDiv) {
			int conditionSize = 300;
			int conditionSymbolesCount = conditionSize / 50;
			int commonSymbolesCount = KeywordUtil.getCommonSymbolsCount(element.text());
			int divCount = element.getElementsByTag("div").size();
			int parentLevel = element.parents().size();
			if(element.text().length() > conditionSize  && parentLevel >= 4 && commonSymbolesCount > conditionSymbolesCount) {
				System.out.println(element.tagName()+ " class:" + element.className() + " id:"+ element.id() + " anchor.count:"+element.getElementsByTag("a").size() + " levels:" + parentLevel + " contentSize:"+element.text().length()+" commonSymbolesCount:"+commonSymbolesCount+" divCount:"+divCount);
				if(element.getElementsByTag("a").size() >= 5) {
					continue;
				}
				if(divCount >= 5) {
					continue;
				}
				
				return element;
			}
		}
		return null;
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

	private static char[] titleSeperator = {'-','_','|',':'};
	private static String extrectMainTitle(String title) {
		title = title.trim();
		for(char c : titleSeperator) {
			int indexOf = title.lastIndexOf(c);
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
		
		if(excludeUriRegex != null) {
			for(String exclude : excludeUriRegex) {
				if(StringUtils.isNotBlank(exclude)) {
					if(href.matches(exclude)) {
						return false;
					}
				}
			}
		}
		
		if(acceptUrlRegex != null) {
			for(String accept : acceptUrlRegex) {
				if(StringUtils.isNotBlank(accept)) {
					if(href.matches(accept)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
