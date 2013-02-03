package com.fpcms.common.webcrawler.htmlparser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.fpcms.common.util.GoogleTranslateUtil;
import com.fpcms.common.util.JsoupSelectorUtil;
import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.NetUtil;
import com.fpcms.common.util.JsoupSelectorUtil.JsoupElementParentsSizeComparator;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;

public class SinglePageCrawler {
	
	private static Logger logger = LoggerFactory.getLogger(SinglePageCrawler.class);
	
	private String[] urlList;
	private String[] acceptUrlRegexList;
	private String[] excludeUriRegexList;
	private String sourceLang; //TODO 自动识别语言
	private String[] mainContentSelector;
	private int minContentLength = 300;
	
	private HtmlPageCrawler htmlPageCrawler = new HtmlPageCrawler() {
		public boolean shoudVisitPage(Anchor a) {
			return true;
		}
		public void visit(HtmlPage page) {
		}
	};
	
	public SinglePageCrawler() {
	}
	
	public SinglePageCrawler(String... url) {
		super();
		setUrlList(url);
	}
	
	public void setHtmlPageCrawler(HtmlPageCrawler htmlPageCrawler) {
		Assert.notNull(htmlPageCrawler,"htmlPageCrawler must be not null");
		this.htmlPageCrawler = htmlPageCrawler;
	}

	public void setSourceLang(String sourceLang) {
		this.sourceLang = sourceLang;
	}

	public void setAcceptUrlRegexList(String... acceptUrlRegex) {
		this.acceptUrlRegexList = acceptUrlRegex;
	}
	
	public void setExcludeUriRegexList(String[] excludeUriRegexList) {
		this.excludeUriRegexList = excludeUriRegexList;
	}

	public void setUrlList(String... url) {
		this.urlList = url;
	}

	public void setMainContentSelector(String... mainContentSelector) {
		this.mainContentSelector = mainContentSelector;
	}
	
	public void setMinContentLength(int minContentLength) {
		this.minContentLength = minContentLength;
	}

	public void execute() {
		for(String url : urlList) {
			try {
				crlawUrl(url);
			}catch(Exception e) {
				logger.error("error_on_crlaw_url:"+url,e);
			}
		}
	}

	private void crlawUrl(String url) {
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
			
			if(isAcceptUrl(a.getHref()) && htmlPageCrawler.shoudVisitPage(a)) {
				try {
					extractArticleByJsoup(a);
				}catch(Exception e) {
					logger.warn("extractArticleByJsoup error",e);
				}
			}
		}
	}

	private void extractArticleByJsoup(Anchor anchor) throws IOException {
		try {
			
			Connection conn = Jsoup.connect(anchor.getHref());
			conn.userAgent("Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)");
			conn.timeout(1000 * 6);
			Document doc = conn.get();
			logger.info("doc.baseUri:"+doc.baseUri() );
			
			String title = extrectMainTitle(doc.title());
			String keywords = JsoupSelectorUtil.select(doc.head(),"[name=keywords]").attr("content");
			String description = JsoupSelectorUtil.select(doc.head(),"[name=description]").attr("content");
			String content = JsoupSelectorUtil.select(doc.body(),mainContentSelector).text();
			Element smartMainContent = smartGetMainContent(doc);
			
			HtmlPage page = new HtmlPage();
			page.setAnchor(anchor);
			page.setContent(StringUtils.defaultIfBlank(content,smartMainContent == null ? null : smartMainContent.text()));
			page.setDescription(description);
			page.setKeywords(keywords);
			page.setTitle(title);
			page.setSourceLang(sourceLang);
			
			
			//TODO 增加anchor.text 与 page.title的比较或者是替换
			logger.info("------------------- url:"+page.getAnchor().getHref()+" ---------------------------");
			logger.info("smartMainContent.text:" + (smartMainContent == null ? "NOT_FOUND" : smartMainContent.text()));
			logger.info("title:"+page.getTitle());
			logger.info("keywords:"+page.getKeywords());
			logger.info("description:"+page.getDescription());
			logger.info("content,size:"+ StringUtils.length(page.getContent()) +" "+page.getContent());
			logger.info("content.deepLevel:"+JsoupSelectorUtil.select(doc,mainContentSelector).parents().size());
			if(smartMainContent != null && StringUtils.isNotBlank(content)) {
				if(!smartMainContent.text().equals(page.getContent())) {
					logger.warn("-------------------error: smart max length text != selector["+StringUtils.join(mainContentSelector,",")+"] text----------------------");
				}
			}
			
			htmlPageCrawler.visit(page);
		}catch(Exception e) {
			throw new RuntimeException("error on extractArticleByJsoup anchor:"+anchor,e);
		}
	}

	private Element smartGetMainContent(Document doc) {
		List<Element> allDiv = JsoupSelectorUtil.selectList(doc,"div");
		Collections.sort(allDiv,new JsoupElementParentsSizeComparator());
		
		for(Element element : allDiv) {
			int conditionSymbolesCount = minContentLength / 50;
			int commonSymbolesCount = KeywordUtil.getCommonSymbolsCount(element.text());
			int divCount = element.getElementsByTag("div").size();
			int parentsSize = element.parents().size();
			
			/*
			 * TODO 增加判断如果出现空格数过多的文字也属于垃圾特征,如: 首页 产品列表 关于我们 
			 * TODO 包含垃圾子段的父亲,也是垃圾
			 * TODO 
			 */
			if(element.text().length() >= minContentLength  && parentsSize >= 4 && commonSymbolesCount > conditionSymbolesCount) {
				if(element.getElementsByTag("a").size() >= 5) {
					continue;
				}
				if(divCount >= 5) {
					continue;
				}
				
				logger.info("success_found_valid_content:"+element.tagName()+ " class:" + element.className() + " id:"+ element.id() + " anchor.count:"+element.getElementsByTag("a").size() + " parentsSize:" + parentsSize + " contentSize:"+element.text().length()+" commonSymbolesCount:"+commonSymbolesCount+" divCount:"+divCount);
				return element;
			}
		}
		return null;
	}

	private static char[] titleSeperator = {':','_','-','|'};
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
		if(acceptUrlRegexList == null) {
			return true;
		}
		
		if(excludeUriRegexList != null) {
			for(String exclude : excludeUriRegexList) {
				if(StringUtils.isNotBlank(exclude)) {
					if(href.matches(exclude)) {
						return false;
					}
				}
			}
		}
		
		if(acceptUrlRegexList != null) {
			for(String accept : acceptUrlRegexList) {
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
