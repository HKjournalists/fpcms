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
public class CmsDomainQuery extends PageQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 主域名 */
	private java.lang.String domain;
	/** 备注 */
	private java.lang.String remarks;
	/** 扩展key_value属性 */
	private java.lang.String props;
	/** IP地址 */
	private java.lang.String ip;
	
	private String sortColumns;

	public java.lang.String getDomain() {
		return this.domain;
	}
	
	public void setDomain(java.lang.String value) {
		this.domain = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}
	
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	
	public java.lang.String getProps() {
		return this.props;
	}
	
	public void setProps(java.lang.String value) {
		this.props = value;
	}
	
	public java.lang.String getIp() {
		return this.ip;
	}
	
	public void setIp(java.lang.String value) {
		this.ip = value;
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

