/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.query;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.duowan.common.util.page.PageQuery;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsChannelQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** 频道名称 */
	private java.lang.String channelName;
	/** 频道代码 */
	private java.lang.String channelCode;
	/** 频道描述 */
	private java.lang.String channelDesc;
	/** 父亲ID */
	private java.lang.Long parentId;
	/** 更新时间 */
	private java.util.Date dateLastModifiedBegin;
	private java.util.Date dateLastModifiedEnd;
	/** 作者 */
	private java.lang.String author;
	/** 网站 */
	private java.lang.String site;

	public java.lang.Long getId() {
		return this.id;
	}
	
	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.String getChannelName() {
		return this.channelName;
	}
	
	public void setChannelName(java.lang.String value) {
		this.channelName = value;
	}
	
	public java.lang.String getChannelCode() {
		return this.channelCode;
	}
	
	public void setChannelCode(java.lang.String value) {
		this.channelCode = value;
	}
	
	public java.lang.String getChannelDesc() {
		return this.channelDesc;
	}
	
	public void setChannelDesc(java.lang.String value) {
		this.channelDesc = value;
	}
	
	public java.lang.Long getParentId() {
		return this.parentId;
	}
	
	public void setParentId(java.lang.Long value) {
		this.parentId = value;
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
	
	public java.lang.String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(java.lang.String value) {
		this.author = value;
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

