/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import com.fpcms.common.util.Tags;


/**
 * tableName: blog_external [BlogExternal] 
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class BlogExternal  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 博客地址       db_column: blog_url 
     */ 	
	@Length(max=100)
	private java.lang.String blogUrl;
	
    /**
     * 博客RPC地址       db_column: blog_rpc_url 
     */ 	
	@Length(max=100)
	private java.lang.String blogRpcUrl;
	
    /**
     * blogName       db_column: blog_name 
     */ 	
	@Length(max=100)
	private java.lang.String blogName;
	
    /**
     * username       db_column: username 
     */ 	
	@Length(max=20)
	private java.lang.String username;
	
    /**
     * password       db_column: password 
     */ 	
	@Length(max=20)
	private java.lang.String password;
	
    /**
     * tags       db_column: tags 
     */ 	
	private Set<String> tags = new HashSet<String>();
	
    /**
     * categories       db_column: categories 
     */ 	
	@Length(max=100)
	private java.lang.String categories;
	
    /**
     * 博客RPC类型       db_column: blog_rpc_api 
     */ 	
	@Length(max=30)
	private java.lang.String blogRpcApi;
	
    /**
     * 博客描述       db_column: blog_desc 
     */ 	
	@Length(max=100)
	private java.lang.String blogDesc;
	/**
	 * 博客发送数
	 */
	private long blogPostCount;
	
	/**
	 * 博客发送的api实现class
	 */
	private String blogRpcApiClass;
	
	/**
	 * 激活状态
	 */
	private boolean enabled;
	//columns END

	public BlogExternal(){
	}

	public BlogExternal(
		java.lang.String blogUrl,
		java.lang.String username,
		java.lang.String password
	){
		this.blogUrl = blogUrl;
		this.username = username;
		this.password = password;
	}

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
		return Tags.toString(tags);
	}
	
	public void setTags(java.lang.String value) {
		this.tags = Tags.fromString(value);
	}
	
	public Set<String> getTagSet() {
		return this.tags;
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
	
	public long getBlogPostCount() {
		return blogPostCount;
	}

	public void setBlogPostCount(long blogPostCount) {
		this.blogPostCount = blogPostCount;
	}

	public String getBlogRpcApiClass() {
		return blogRpcApiClass;
	}

	public void setBlogRpcApiClass(String blogRpcApiClass) {
		this.blogRpcApiClass = blogRpcApiClass;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getBlogUrl())
			.append(getUsername())
			.append(getPassword())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj instanceof BlogExternal == false) return false;
		BlogExternal other = (BlogExternal)obj;
		return new EqualsBuilder()
			.append(getBlogUrl(),other.getBlogUrl())
			.append(getUsername(),other.getUsername())
			.append(getPassword(),other.getPassword())
			.isEquals();
	}
}

