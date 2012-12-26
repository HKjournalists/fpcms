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
public class CmsSiteQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 网站域名 */
	private java.lang.String siteDomain;
	/** 网站名称 */
	private java.lang.String siteName;
	/** 网站描述 */
	private java.lang.String siteDesc;
	/** 网站对应的城市 */
	private java.lang.String siteCity;
	/** 网站关键词 */
	private java.lang.String siteKeyword;
	/** 备注 */
	private java.lang.String remarks;

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
	
}

