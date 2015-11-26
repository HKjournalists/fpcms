/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.model;

import static com.github.rapid.common.util.DateFormats.DATE_FORMAT;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.github.rapid.common.util.DateConvertUtil;
import com.fpcms.common.util.BlogPingUtil;
import com.fpcms.common.util.ChineseSegmenterUtil;
import com.fpcms.common.util.StringHelper;
import com.fpcms.common.util.Tags;


/**
 * tableName: cms_content [CmsContent] 
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsContent  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_DATE_CREATED = DATE_FORMAT;
	public static final String FORMAT_DATE_LAST_MODIFIED = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: id 
     */ 	
	
	private java.lang.Long id;
	
    /**
     * 频道ID       db_column: channel_code 
     */ 	
	@Length(max=50) @NotBlank
	private java.lang.String channelCode;
	
    /**
     * 标签       db_column: tags 
     */ 	
	private Set<String> tags = new HashSet<String>();
	
    /**
     * 网页头title       db_column: head_title 
     */ 	
	@Length(max=200)
	private java.lang.String headTitle;
	
    /**
     * 标题       db_column: title 
     */ 	
	@Length(max=200) @NotBlank
	private java.lang.String title;
	
    /**
     * 内容       db_column: content 
     */ 	
	@Length(max=65535) @NotBlank
	private java.lang.String content;
	
    /**
     * 作者       db_column: author 
     */ 	
	@Length(max=50)
	private java.lang.String author;
	
    /**
     * 创建时间       db_column: date_created 
     */ 	
	
	private java.util.Date dateCreated;
	
    /**
     * 更新时间       db_column: date_last_modified 
     */ 	
	
	private java.util.Date dateLastModified;
	
    /**
     * 网站       db_column: site 
     */ 	
	@Length(max=50) @NotBlank
	private java.lang.String site;
	
	private Long level;
	
	
	private String searchKeyword;
	/**
	 * 来源URL
	 */
	@Length(max=200)
	private String sourceUrl;
	
	public CmsContent(){
	}

	public CmsContent(
		java.lang.Long id
	){
		this.id = id;
	}

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
		this.channelCode = StringUtils.trim(value);
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
	
	public java.lang.String getHeadTitle() {
		return this.headTitle;
	}
	
	public void setHeadTitle(java.lang.String value) {
		this.headTitle = StringUtils.trim(value);
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = StringUtils.trim(value);
	}
	
	public String getKeyword() {
		if(StringUtils.isBlank(title)) {
			return null;
		}
		List<String> minLengthKeywords = new ArrayList(ChineseSegmenterUtil.getMinLengthKeywords(title, 3, true));
		StringHelper.removeRegexMatch(minLengthKeywords, ".*\\d.*");
		return StringUtils.join(minLengthKeywords,",");
	}
	
	public String getMetaDescription() {
		return StringHelper.getMetaDescription(getContent());
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
	
	public java.util.Date getDateCreated() {
		return this.dateCreated;
	}
	
	public void setDateCreated(java.util.Date value) {
		this.dateCreated = value;
	}
	
	public java.util.Date getDateLastModified() {
		return this.dateLastModified;
	}
	
	public void setDateLastModified(java.util.Date value) {
		this.dateLastModified = value;
	}
	
	public java.lang.String getSite() {
		return this.site;
	}
	
	public void setSite(java.lang.String value) {
		this.site = StringUtils.trim(value);
	}
	
	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}
	
	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSourceSite() {
		if(StringUtils.isBlank(sourceUrl)) {
			return null;
		}
		try {
			return new URL(sourceUrl).getHost();
		} catch (MalformedURLException e) {
			return sourceUrl;
		}
	}
	/**
	 * 得到访问的URL地址
	 * @return
	 */
	public String getUrl() {
		return "http://"+site+getUri();
	}
	
	/**
	 * 得到访问的URI地址
	 * @return
	 */
	public String getUri() {
		return "/content/"+DateConvertUtil.format(getDateCreated(), "yyyyMMdd")+"/"+getId()+".do";
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public static void baiduBlogPing(CmsContent cmsContent) {
//		if(!AppModeUtil.isProdAppMode()) {
//			return;
//		}
		if("localhost".equals(cmsContent.getSite())) {
			return;
		}
		if("127.0.0.1".equals(cmsContent.getSite())) {
			return;
		}
		BlogPingUtil.baiduPing(cmsContent.getSite(), "http://"+cmsContent.getSite(), cmsContent.getUrl(), "http://"+cmsContent.getSite()+"/rss.xml");
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj instanceof CmsContent == false) return false;
		CmsContent other = (CmsContent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

