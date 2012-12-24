package com.fpcms.common.random_gen_article;

import java.util.Date;

/**
 * 代表随机生成的一篇文章
 * 
 * @author badqiu
 * 
 */
public class RandomArticle {
	private String keyword;
	private String faipiaoKeyword;
	private String finalSearchKeyword;
	private String content;
	private String perfectKeyword; //推荐使用的keyword
	private Date dateCreated;

	public RandomArticle() {
	}

	public RandomArticle(String keyword, String faipiaoKeyword,
			String finalSearchKeyword, String content) {
		super();
		this.keyword = keyword;
		this.faipiaoKeyword = faipiaoKeyword;
		this.finalSearchKeyword = finalSearchKeyword;
		this.content = content;
		dateCreated = new Date();
	}
	
	public String getPerfectKeyword() {
		return perfectKeyword;
	}

	public void setPerfectKeyword(String perfectKeyword) {
		this.perfectKeyword = perfectKeyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getFaipiaoKeyword() {
		return faipiaoKeyword;
	}

	public void setFaipiaoKeyword(String faipiaoKeyword) {
		this.faipiaoKeyword = faipiaoKeyword;
	}

	public String getFinalSearchKeyword() {
		return finalSearchKeyword;
	}

	public void setFinalSearchKeyword(String finalSearchKeyword) {
		this.finalSearchKeyword = finalSearchKeyword;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "RandomArticle [keyword=" + keyword + ", faipiaoKeyword="
				+ faipiaoKeyword + ", finalSearchKeyword=" + finalSearchKeyword
				+ ", content=" + content + "]";
	}

}