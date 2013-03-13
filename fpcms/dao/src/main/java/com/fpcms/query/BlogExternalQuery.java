/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
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
public class BlogExternalQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 博客地址 */
	private java.lang.String blogUrl;
	/** 博客RPC地址 */
	private java.lang.String blogRpcUrl;
	/** blogName */
	private java.lang.String blogName;
	/** username */
	private java.lang.String username;
	/** password */
	private java.lang.String password;
	/** tags */
	private java.lang.String tags;
	/** categories */
	private java.lang.String categories;
	/** 博客RPC类型 */
	private java.lang.String blogRpcApi;
	/** 博客描述 */
	private java.lang.String blogDesc;

	public java.lang.String getBlogUrl() {
		return this.blogUrl;
	}
	
	public void setBlogUrl(java.lang.String value) {
		this.blogUrl = value;
	}
	
	public java.lang.String getBlogRpcUrl() {
		return this.blogRpcUrl;
	}
	
	public void setBlogRpcUrl(java.lang.String value) {
		this.blogRpcUrl = value;
	}
	
	public java.lang.String getBlogName() {
		return this.blogName;
	}
	
	public void setBlogName(java.lang.String value) {
		this.blogName = value;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getTags() {
		return this.tags;
	}
	
	public void setTags(java.lang.String value) {
		this.tags = value;
	}
	
	public java.lang.String getCategories() {
		return this.categories;
	}
	
	public void setCategories(java.lang.String value) {
		this.categories = value;
	}
	
	public java.lang.String getBlogRpcApi() {
		return this.blogRpcApi;
	}
	
	public void setBlogRpcApi(java.lang.String value) {
		this.blogRpcApi = value;
	}
	
	public java.lang.String getBlogDesc() {
		return this.blogDesc;
	}
	
	public void setBlogDesc(java.lang.String value) {
		this.blogDesc = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

