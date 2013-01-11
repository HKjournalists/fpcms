/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


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
	@Length(max=30) @NotBlank
	private java.lang.String siteDomain;
	
    /**
     * 网站名称       db_column: site_name 
     */ 	
	@Length(max=100) @NotBlank
	private java.lang.String siteName;
	
    /**
     * 网站描述       db_column: site_desc 
     */ 	
	@Length(max=60)
	private java.lang.String siteDesc;
	
    /**
     * 网站对应的城市       db_column: city 
     */ 	
	@Length(max=40)
	private java.lang.String city;
	
    /**
     * 网站关键词       db_column: keyword 
     */ 	
	@Length(max=120)
	private java.lang.String keyword;
	
    /**
     * 备注       db_column: remarks 
     */ 	
	@Length(max=100)
	private java.lang.String remarks;
	
    /**
     * 公司       db_column: company 
     */ 	
	@Length(max=50)
	private java.lang.String company;
	
    /**
     * 联系人       db_column: contact_name 
     */ 	
	@Length(max=50)
	private java.lang.String contactName;
	
    /**
     * 移动电话       db_column: mobile 
     */ 	
	@Length(max=20)
	private java.lang.String mobile;
	
    /**
     * QQ       db_column: qq 
     */ 	
	@Length(max=20)
	private java.lang.String qq;
	
    /**
     * 邮件       db_column: email 
     */ 	
	@Email @Length(max=20)
	private java.lang.String email;
	
    /**
     * 创建时间       db_column: date_created 
     */ 	
	
	private java.util.Date dateCreated;
	
    /**
     * 更新时间       db_column: date_last_modified 
     */ 	
	
	private java.util.Date dateLastModified;
	
	@Length(max=50)
	private java.lang.String author;
	//columns END
	/**
	 * 360收录数
	 */
	private int record360;
	/**
	 * google收录数
	 */	
	private int recordGoogle;
	/**
	 * 百度收录数
	 */
	private int recordBaidu;
	/**
	 * 360关键词排名
	 */
	private int rank360;
	/**
	 * google关键词排名
	 */
	private int rankGoogle;
	/**
	 * 百度关键词排名
	 */
	private int rankBaidu;
	
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
	
	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(java.util.Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public java.util.Date getDateLastModified() {
		return dateLastModified;
	}

	public void setDateLastModified(java.util.Date dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	public java.lang.String getAuthor() {
		return author;
	}

	public void setAuthor(java.lang.String author) {
		this.author = author;
	}
	
	public int getRecord360() {
		return record360;
	}

	public void setRecord360(int record360) {
		this.record360 = record360;
	}

	public int getRecordGoogle() {
		return recordGoogle;
	}

	public void setRecordGoogle(int recordGoogle) {
		this.recordGoogle = recordGoogle;
	}

	public int getRecordBaidu() {
		return recordBaidu;
	}

	public void setRecordBaidu(int recordBaidu) {
		this.recordBaidu = recordBaidu;
	}

	public int getRank360() {
		return rank360;
	}

	public void setRank360(int rank360) {
		this.rank360 = rank360;
	}

	public int getRankGoogle() {
		return rankGoogle;
	}

	public void setRankGoogle(int rankGoogle) {
		this.rankGoogle = rankGoogle;
	}

	public int getRankBaidu() {
		return rankBaidu;
	}

	public void setRankBaidu(int rankBaidu) {
		this.rankBaidu = rankBaidu;
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

