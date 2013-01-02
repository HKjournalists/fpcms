/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.model;

import static com.duowan.common.util.DateFormats.DATE_FORMAT;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.duowan.common.util.tree.Node;
import com.duowan.common.util.tree.NodeWrapper;
import com.duowan.common.util.tree.TreeCreator;
import com.fpcms.common.util.ClasspathUtil;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.FreemarkerUtil;


/**
 * tableName: cms_channel [CmsChannel] 
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsChannel  implements Node<Long>,java.io.Serializable,Cloneable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_DATE_LAST_MODIFIED = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: id 
     */ 	
	
	private java.lang.Long id;
	
    /**
     * 频道名称       db_column: channel_name 
     */ 	
	@Length(max=50)
	private java.lang.String channelName;
	
    /**
     * 频道代码       db_column: channel_code 
     */ 	
	@Length(max=50)
	private java.lang.String channelCode;
	
    /**
     * 频道描述       db_column: channel_desc 
     */ 	
	@Length(max=50)
	private java.lang.String channelDesc;
	
    /**
     * 父亲ID       db_column: parent_id 
     */ 	
	
	private java.lang.Long parentId;
	
    /**
     * 更新时间       db_column: date_last_modified 
     */ 	
	
	private java.util.Date dateLastModified;

    /**
     * 创建时间       db_column: date_created 
     */ 	
	
	private java.util.Date dateCreated;
	
    /**
     * 作者       db_column: author 
     */ 	
	@Length(max=50)
	private java.lang.String author;
	
    /**
     * 网站       db_column: site 
     */ 	
	@Length(max=50) @NotBlank
	private java.lang.String site;

    /**
     * 等级      db_column: level 
     */ 	
	private long level;
	
	/**
     * 频道内容      db_column: content 
     */ 	
	private String content;
	
	/**
	 * 链接
	 */
	@Length(max=100)
	private String link;
	
	
	/**
	 * 链接 target
	 */
	@Length(max=30)
	private String linkTarget;
	
	/**
	 * 关键字
	 */
	@Length(max=50)
	private String keyword;
	
	//columns END
	
	public static CmsChannel ROOT =  new CmsChannel(Constants.TREE_ROOT_PARENT_ID,Constants.TREE_ROOT_ID,"root","root");
	public static CmsChannel NAV =  ROOT.newSubChannel(10,"nav","导航条");
	public static CmsChannel HOME =  ROOT.newSubChannel(20,"home","首页").setContent(ClasspathUtil.getStringResouce("cms_channel_template/home", "UTF-8"));
	public static CmsChannel NEWS = ROOT.newSubChannel(30,"news","新闻中心");
	public static CmsChannel[] NAV_SUB_CHANNELS =  {
		NAV.newSubChannel(1010,"aboutus","关于我们").setContent(ClasspathUtil.getStringResouce("cms_channel_template/aboutus", "UTF-8")),
		NAV.newSubChannel(1020,"projects","开票项目").setContent(ClasspathUtil.getStringResouce("cms_channel_template/projects", "UTF-8")),
		NAV.newSubChannel(1040,"contact","联系方式").setContent(ClasspathUtil.getStringResouce("cms_channel_template/contact", "UTF-8")),
	};

	public CmsChannel(){
	}
	
	public CmsChannel(long id, String channelCode, String channelName) {
		super();
		this.id = id;
		this.channelCode = channelCode;
		this.channelName = channelName;
	}
	
	public CmsChannel(long parentId,long id, String channelCode, String channelName) {
		super();
		this.parentId = parentId;
		this.id = id;
		this.channelCode = channelCode;
		this.channelName = channelName;
	}

	public CmsChannel(
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
	
	public java.util.Date getDateLastModified() {
		return this.dateLastModified;
	}
	
	public void setDateLastModified(java.util.Date value) {
		this.dateLastModified = value;
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
	
	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}
	
	public String getContent() {
		return content;
	}

	public CmsChannel setContent(String content) {
		this.content = content;
		return this;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLinkTarget() {
		return linkTarget;
	}

	public void setLinkTarget(String linkTarget) {
		this.linkTarget = linkTarget;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(java.util.Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public CmsChannel newSubChannel() {
		CmsChannel result = new CmsChannel();
		result.setParentId(this.getId());
		result.setSite(site);
		return result;
	}
	
	public CmsChannel newSubChannel(String channelCode,String channelName) {
		CmsChannel result = newSubChannel();
		result.setChannelCode(channelCode);
		result.setChannelName(channelName);
		return result;
	}
	
	public CmsChannel newSubChannel(long channelId,String channelCode,String channelName) {
		CmsChannel result = newSubChannel(channelCode,channelName);
		result.setId(channelId);
		return result;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj instanceof CmsChannel == false) return false;
		CmsChannel other = (CmsChannel)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	@Override
	public int compareTo(Object o) {
		CmsChannel other = (CmsChannel)o;
		return new Long(getLevel()).compareTo(other.getLevel());
	}
	
	public static NodeWrapper<CmsChannel> createTree(List<CmsChannel> nodes,long rootNodeId) {
		TreeCreator creator = new TreeCreator();
		return creator.createTree(nodes, rootNodeId);
	}
	
	
	public CmsChannel clone(String site){
		CmsChannel result = clone();
		result.setSite(site);
		return result;
	}
	
	@Override
	public CmsChannel clone()  {
		try {
			return (CmsChannel)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("CloneNotSupportedException",e);
		}
	}
}

