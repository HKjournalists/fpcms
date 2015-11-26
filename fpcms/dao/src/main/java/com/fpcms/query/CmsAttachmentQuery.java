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
public class CmsAttachmentQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** id */
	private java.lang.Long id;
	/** code */
	private java.lang.String code;
	/** remarks */
	private java.lang.String remarks;
	/** dateLastModified */
	private java.util.Date dateLastModifiedBegin;
	private java.util.Date dateLastModifiedEnd;
	/** author */
	private java.lang.String author;

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
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}
	
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
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
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

