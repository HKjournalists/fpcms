/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.github.rapid.common.util.page.PageQuery;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsContentQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 频道ID */
	private java.lang.String channelCode;
	/** 标签 */
	private java.lang.String tags;
	/** 网页头title */
	private java.lang.String headTitle;
	/** 标题 */
	private java.lang.String title;
	/** 内容 */
	private java.lang.String content;
	/** 作者 */
	private java.lang.String author;
	/** 创建时间 */
	private java.util.Date dateCreatedBegin;
	private java.util.Date dateCreatedEnd;
	/** 更新时间 */
	private java.util.Date dateLastModifiedBegin;
	private java.util.Date dateLastModifiedEnd;
	/** 网站 */
	private java.lang.String site;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getChannelCode() {
		return this.channelCode;
	}
	
	public void setChannelCode(java.lang.String value) {
		this.channelCode = value;
	}
	
	public java.lang.String getTags() {
		return this.tags;
	}
	
	public void setTags(java.lang.String value) {
		this.tags = value;
	}
	
	public java.lang.String getHeadTitle() {
		return this.headTitle;
	}
	
	public void setHeadTitle(java.lang.String value) {
		this.headTitle = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(java.lang.String value) {
		this.author = value;
	}
	
	public java.util.Date getDateCreatedBegin() {
		return this.dateCreatedBegin;
	}
	
	public void setDateCreatedBegin(java.util.Date value) {
		this.dateCreatedBegin = value;
	}	
	
	public java.util.Date getDateCreatedEnd() {
		return this.dateCreatedEnd;
	}
	
	public void setDateCreatedEnd(java.util.Date value) {
		this.dateCreatedEnd = value;
	}
	
	public java.util.Date getDateLastModifiedBegin() {
		return this.dateLastModifiedBegin;
	}
	
	public void setDateLastModifiedBegin(java.util.Date value) {
		this.dateLastModifiedBegin = value;
	}	
	
	public java.util.Date getDateLastModifiedEnd() {
		return this.dateLastModifiedEnd;
	}
	
	public void setDateLastModifiedEnd(java.util.Date value) {
		this.dateLastModifiedEnd = value;
	}
	
	public java.lang.String getSite() {
		return this.site;
	}
	
	public void setSite(java.lang.String value) {
		this.site = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

