package com.fpcms.common.webcrawler.htmlparser;

import org.apache.commons.lang.StringUtils;

public class HtmlPage {

	private String title;
	private String keywords;
	private String description;
	private String content;

	private Anchor anchor;
	
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
	
	public static class Anchor {
		String href;
		String text;
		String title;

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
	}

}
