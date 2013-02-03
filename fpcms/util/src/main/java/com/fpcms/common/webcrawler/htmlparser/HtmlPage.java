package com.fpcms.common.webcrawler.htmlparser;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

public class HtmlPage {

	private String title;
	private String keywords;
	private String description;
	private String content;
	
	private Anchor anchor;
	
	private String sourceLang;
	
	public Anchor getAnchor() {
		return anchor;
	}

	public void setAnchor(Anchor anchor) {
		this.anchor = anchor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = StringUtils.trim(title);
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = StringUtils.trim(keywords);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = StringUtils.trim(description);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = StringUtils.trim(content);
	}
	
	public String getSourceLang() {
		return sourceLang;
	}

	public void setSourceLang(String sourceLang) {
		this.sourceLang = sourceLang;
	}

	public static class Anchor {
		private String href;
		private String text;
		private String title;

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = StringUtils.trim(href);
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = StringUtils.trim(text);
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = StringUtils.trim(title);
		}

		@Override
		public String toString() {
			return "Anchor [href=" + href + ", text=" + text + ", title="
					+ title + "]";
		}
		
		public static String toFullUrl(String baseUrl,String href)  {
			if(href.matches("https?://.*")) {
				return href;
			}
			if(href.startsWith("/")) {
				return getRootUrl(baseUrl)	+	href;
			}else {
				return baseUrl + href;
			}
		}

		public static String getRootUrl(String baseUrl) {
			try {
				URL url = new URL(baseUrl);
				String root = url.getProtocol()+"://"+url.getHost();
				return root;
			} catch (MalformedURLException e) {
				throw new RuntimeException("MalformedURLException,url:"+baseUrl,e);
			}
		}
	}

}
