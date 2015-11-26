/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.model;

import static com.github.rapid.common.util.DateFormats.DATE_FORMAT;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;


/**
 * tableName: cms_attachment [CmsAttachment] 
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsAttachment  implements java.io.Serializable{
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
     * code       db_column: code 
     */ 	
	@Length(max=50)
	private java.lang.String code;
	
    /**
     * attachment       db_column: attachment 
     */ 	
	
	private byte[] attachment;
	
    /**
     * remarks       db_column: remarks 
     */ 	
	@Length(max=50)
	private java.lang.String remarks;
	
    /**
     * dateLastModified       db_column: date_last_modified 
     */ 	
	
	private java.util.Date dateLastModified;
	
    /**
     * author       db_column: author 
     */ 	
	@Length(max=50)
	private java.lang.String author;
	
	//columns END

	public CmsAttachment(){
	}

	public CmsAttachment(
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
	
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	public byte[] getAttachment() {
		return this.attachment;
	}
	
	public void setAttachment(byte[] value) {
		this.attachment = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}
	
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
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
		if(obj instanceof CmsAttachment == false) return false;
		CmsAttachment other = (CmsAttachment)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

