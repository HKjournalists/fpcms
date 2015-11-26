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
public class CmsSiteQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 网站域名 */
	private java.lang.String siteDomain;
	/** 网站名称 */
	private java.lang.String siteName;
	/** 网站描述 */
	private java.lang.String siteDesc;
	/** 网站对应的城市 */
	private java.lang.String city;
	/** 网站关键词 */
	private java.lang.String keyword;
	/** 备注 */
	private java.lang.String remarks;
	/** 公司 */
	private java.lang.String company;
	/** 联系人 */
	private java.lang.String contactName;
	/** 移动电话 */
	private java.lang.String mobile;
	/** QQ */
	private java.lang.String qq;
	/** 邮件 */
	private java.lang.String email;

	private String sortColumns;
	
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
	
	public java.lang.String getCity() {
		return this.city;
	}
	
	public void setCity(java.lang.String value) {
		this.city = value;
	}
	
	public java.lang.String getKeyword() {
		return this.keyword;
	}
	
	public void setKeyword(java.lang.String value) {
		this.keyword = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}
	
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	
	public java.lang.String getCompany() {
		return this.company;
	}
	
	public void setCompany(java.lang.String value) {
		this.company = value;
	}
	
	public java.lang.String getContactName() {
		return this.contactName;
	}
	
	public void setContactName(java.lang.String value) {
		this.contactName = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getQq() {
		return this.qq;
	}
	
	public void setQq(java.lang.String value) {
		this.qq = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

