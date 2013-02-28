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
public class CmsPropertyQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** propGroup */
	private java.lang.String propGroup;
	/** propKey */
	private java.lang.String propKey;
	/** propValue */
	private java.lang.String propValue;
	/** ramarks */
	private java.lang.String ramarks;

	private String sortColumns;
	
	public java.lang.String getPropGroup() {
		return this.propGroup;
	}
	
	public void setPropGroup(java.lang.String value) {
		this.propGroup = value;
	}
	
	public java.lang.String getPropKey() {
		return this.propKey;
	}
	
	public void setPropKey(java.lang.String value) {
		this.propKey = value;
	}
	
	public java.lang.String getPropValue() {
		return this.propValue;
	}
	
	public void setPropValue(java.lang.String value) {
		this.propValue = value;
	}
	
	public java.lang.String getRamarks() {
		return this.ramarks;
	}
	
	public void setRamarks(java.lang.String value) {
		this.ramarks = value;
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

