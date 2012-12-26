/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;


/**
 * tableName: cms_site [CmsSite] 
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsSite  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 网站域名       db_column: site_domain 
     */ 	
	@Length(max=30)
	private java.lang.String siteDomain;
	
    /**
     * 网站名称       db_column: site_name 
     */ 	
	@Length(max=100)
	private java.lang.String siteName;
	
    /**
     * 网站描述       db_column: site_desc 
     */ 	
	@Length(max=60)
	private java.lang.String siteDesc;
	
    /**
     * 网站对应的城市       db_column: site_city 
     */ 	
	@Length(max=40)
	private java.lang.String siteCity;
	
    /**
     * 网站关键词       db_column: site_keyword 
     */ 	
	@Length(max=120)
	private java.lang.String siteKeyword;
	
    /**
     * 备注       db_column: remarks 
     */ 	
	@Length(max=100)
	private java.lang.String remarks;
	
	//columns END

	public CmsSite(){
	}

	public CmsSite(
		java.lang.String siteDomain
	){
		this.siteDomain = siteDomain;
	}

	public java.lang.String getSiteDomain() {
		return this.siteDomain;
	}
	
	public void setSiteDomain(java.lang.String value) {
		this.siteDomain = value;
	}
	
	public java.lang.String getSiteName() {
		return this.siteName;
	}
	
	public void setSiteName(java.lang.String value) {
		this.siteName = value;
	}
	
	public java.lang.String getSiteDesc() {
		return this.siteDesc;
	}
	
	public void setSiteDesc(java.lang.String value) {
		this.siteDesc = value;
	}
	
	public java.lang.String getSiteCity() {
		return this.siteCity;
	}
	
	public void setSiteCity(java.lang.String value) {
		this.siteCity = value;
	}
	
	public java.lang.String getSiteKeyword() {
		return this.siteKeyword;
	}
	
	public void setSiteKeyword(java.lang.String value) {
		this.siteKeyword = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}
	
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getSiteDomain())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj instanceof CmsSite == false) return false;
		CmsSite other = (CmsSite)obj;
		return new EqualsBuilder()
			.append(getSiteDomain(),other.getSiteDomain())
			.isEquals();
	}
}

