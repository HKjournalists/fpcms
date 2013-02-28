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
public class CmsKeyValueQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 创建时间 */
	private java.util.Date dateCreatedBegin;
	private java.util.Date dateCreatedEnd;
	/** 分组 */
	private java.lang.String keyGroup;
	/** key */
	private java.lang.String strKey;
	/** value */
	private java.lang.String value;

	private String sortColumns;
	
	public java.util.Date getDateCreatedBegin() {
		return this.dateCreatedBegin;
	}
	
	public void setDateCreatedBegin(java.util.Date value) {
		this.dateCreatedBegin = value;
	}	
	
	public java.util.Date getDateCreatedEnd() {
		return this.dateCreatedEnd;
	}
	
	public void setDateCreatedEnd(java.util.Date value) {
		this.dateCreatedEnd = value;
	}
	
	public java.lang.String getKeyGroup() {
		return this.keyGroup;
	}
	
	public void setKeyGroup(java.lang.String value) {
		this.keyGroup = value;
	}
	
	public java.lang.String getStrKey() {
		return this.strKey;
	}
	
	public void setStrKey(java.lang.String value) {
		this.strKey = value;
	}
	
	public java.lang.String getValue() {
		return this.value;
	}
	
	public void setValue(java.lang.String value) {
		this.value = value;
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

