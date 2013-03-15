package com.fpcms.common.webcrawler.htmlparser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.fpcms.common.util.JsoupSelectorUtil;
import com.fpcms.common.util.JsoupSelectorUtil.JsoupElementParentsSizeComparator;
import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.NetUtil;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;

public class SinglePageCrawler {
	
	private static Logger logger = LoggerFactory.getLogger(SinglePageCrawler.class);
	
	private String[] urlList;
	private String[] acceptUrlRegexList = new String[]{".*"};
	private String[] excludeUriRegexList;
	private String sourceLang; //TODO 自动识别语言
	private String[] mainContentSelector;
	private int minContentLength = 300;
	private boolean deleteUrlQueryString = true;
	
	/**
	 * 为文章打些标签
	 */
	private String tags;
	
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
	
	public void setExcludeUriRegexList(String... excludeUriRegexList) {
		this.excludeUriRegexList = excludeUriRegexList;
	}

	public void setUrlList(String... url) {
		this.urlList = url;
	}
	
	public String[] getUrlList() {
		return urlList;
	}
	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setMainContentSelector(String... mainContentSelector) {
		this.mainContentSelector = mainContentSelector;
	}
	
	public void setMinContentLength(int minContentLength) {
		this.minContentLength = minContentLength;
	}
	
	public boolean isDeleteUrlQueryString() {
		return deleteUrlQueryString;
	}

	public void setDeleteUrlQueryString(boolean deleteUrlQueryString) {
		this.deleteUrlQueryString = deleteUrlQueryString;
	}

	public void execute() {
		logger.info("start_execute_craw,sourceLang:"+sourceLang+" tags:"+tags+" minContentLength:"+minContentLength+" acceptUrlRegexList:"+StringUtils.join(acceptUrlRegexList,","));
		
		for(String url : urlList) {
			try {
				crlawUrl(url);
			}catch(Exception e) {
				logger.error("error_on_crlaw_url:"+url,e);
			}
		}
	}

	public List<HtmlPage> crlawUrl(String url) {
		List<Anchor> shoudVisitAnchorList = getShoudVisitAnchorList(url);
		return visitAnchorList(shoudVisitAnchorList);
	}

	List<HtmlPage> visitAnchorList(List<Anchor> shoudVisitAnchorList) {
		List<HtmlPage> visitedPage = new ArrayList<HtmlPage>();
		for(Anchor a : shoudVisitAnchorList) {
			try {
				HtmlPage page = extractArticleByJsoup(a);
				if(page != null) {
					htmlPageCrawler.visit(page);
					visitedPage.add(page);
				}
			}catch(Exception e) {
				logger.warn("extractArticleByJsoup error",e);
			}
		}
		return visitedPage;
	}

	public List<Anchor> getShoudVisitAnchorList(String url) {
		String content = NetUtil.httpGet(url);
		Document doc = Jsoup.parse(content);
		Collection<Anchor> shoudVisitAnchorList = getShoudVisitAnchorList(url, doc);
		return new ArrayList<Anchor>(shoudVisitAnchorList);
	}
	
	private List<Anchor> getShoudVisitAnchorList(String url, Document doc) {
		Elements elements = doc.getElementsByTag("a");
		
		LinkedHashSet<Anchor> shoudVisitAnchorSet = new LinkedHashSet<Anchor>();
		for(Element anchor : elements) {
			String href = anchor.attr("href");
			String text = StringUtils.trim(anchor.text());
			String title = anchor.attr("title");
			Anchor a = new Anchor();
			String fullHref = Anchor.toFullUrl(url,href);
			fullHref = deleteUrlQueryString ? Anchor.removeQueryString(fullHref) : fullHref;
			a.setHref(fullHref);
			a.setText(text);
			a.setTitle(title);
			shoudVisitAnchorSet.add(a);
		}
		
		List<Anchor> result = new ArrayList<Anchor>();
		for(Anchor a : shoudVisitAnchorSet) {
			if(isAcceptUrl(a.getHref()) && htmlPageCrawler.shoudVisitPage(a)) {
				result.add(a);
			}else {
				logger.info("ignore_by_not_accept_url:{}",a.getHref());
			}
		}
		return result;
	}

	HtmlPage extractArticleByJsoup(Anchor anchor) throws IOException {
		try {
			
			Connection conn = Jsoup.connect(anchor.getHref());
			conn.userAgent("Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)");
			conn.timeout(1000 * 6);
			Document doc = conn.get();
			logger.info("doc.baseUri:"+doc.baseUri() );
			
			String title = HtmlPageTitleUtil.smartGetTitle(anchor,doc.title());
			String keywords = JsoupSelectorUtil.select(doc.head(),"[name=keywords]").attr("content");
			String description = JsoupSelectorUtil.select(doc.head(),"[name=description]").attr("content");
			String mainContentSelectorContent = JsoupSelectorUtil.select(doc.body(),mainContentSelector).text();
			Element smartMainContent = smartGetMainContent(doc);
			
			HtmlPage page = new HtmlPage();
			page.setAnchor(anchor);
			page.setContent(StringUtils.defaultIfBlank(mainContentSelectorContent,smartMainContent == null ? null : smartMainContent.text()));
			page.setDescription(description);
			page.setKeywords(keywords);
			page.setTitle(title);
			page.setSourceLang(sourceLang);
			page.setTags(tags);
			
			//TODO 增加anchor.text 与 page.title的比较或者是替换
			logger.info("------------------- url:"+page.getAnchor().getHref()+" ---------------------------");
			logger.info("smartMainContent.text:" + (smartMainContent == null ? "NOT_FOUND" : smartMainContent.text()));
			logger.info("title:"+page.getTitle());
			logger.info("keywords:"+page.getKeywords());
			logger.info("description:"+page.getDescription());
			logger.info("content,size:"+ StringUtils.length(page.getContent()) +" "+page.getContent());
			logger.info("content.deepLevel:"+JsoupSelectorUtil.select(doc,mainContentSelector).parents().size());
			if(smartMainContent != null && StringUtils.isNotBlank(mainContentSelectorContent)) {
				if(!smartMainContent.text().equals(page.getContent())) {
					logger.warn("-------------------error: smart max length text != selector["+StringUtils.join(mainContentSelector,",")+"] text----------------------");
				}
			}
			
			if(StringUtils.length(page.getContent()) < minContentLength) {
				return null;
			}
			
			return page;
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
			
			if("articlePage".equals(element.className().trim())) {
				int a = 1 + 1;
			}
			/*
			 * TODO 增加判断如果出现空格数过多的文字也属于垃圾特征,如: 首页 产品列表 关于我们 
			 * TODO 包含垃圾子段的父亲,也是垃圾
			 * TODO 
			 */
			int textLength = element.text().length();
			int anchorSize = element.getElementsByTag("a").size();
			if(getPageElementScore(textLength,parentsSize,commonSymbolesCount,conditionSymbolesCount,divCount,anchorSize) >= 30) {
				logger.info("success_found_valid_content:"+element.tagName()+ " class:" + element.className() + " id:"+ element.id() + " anchor.count:"+anchorSize + " parentsSize:" + parentsSize + " contentSize:"+textLength+" commonSymbolesCount:"+commonSymbolesCount+" divCount:"+divCount+" anchorSize:"+anchorSize);
				return element;
			}
		}
		return null;
	}
	
	public int getPageElementScore(int textLength,int parentsSize,int commonSymbolesCount,int conditionSymbolesCount,int divCount,int anchorSize) {
		int score = 0;
		if(textLength >= minContentLength) {
			score += 10;
		}
		if(parentsSize >= 4) {
			score += 10;
		}
		if(commonSymbolesCount > conditionSymbolesCount) {
			score += 10;
		}
		if(textLength >= 2000) {
			score += 10;
		}
		if(anchorSize >= 13) {
			score -= 10;
		}
		if(divCount >= 5) {
			score -= 10;
		}
		return score;
	}
	

	boolean isAcceptUrl(String href) {
		if(StringUtils.isBlank(href)) {
			return false;
		}
		
		try {
			new URL(href);
		} catch (MalformedURLException e) {
			return false;
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
