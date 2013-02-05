/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import com.duowan.common.util.DateConvertUtils;
import com.fpcms.common.cache.Cache;
import com.fpcms.common.cache.CacheManager;
import com.fpcms.common.cache.ValueCallback;
import com.fpcms.common.util.HttpStatusCheckUtil;
import com.fpcms.common.util.StringHelper;


/**
 * tableName: cms_domain [CmsDomain] 
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsDomain  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 主域名       db_column: domain 
     */ 	
	@Length(max=30)
	private java.lang.String domain;
	
    /**
     * 备注       db_column: remarks 
     */ 	
	@Length(max=200)
	private java.lang.String remarks;
	
    /**
     * 扩展key_value属性       db_column: props 
     */ 	
	@Length(max=2000)
	private java.lang.String props;
	
    /**
     * IP地址       db_column: ip 
     */ 	
	@Length(max=200)
	private java.lang.String ip;
	
	//columns END

	public CmsDomain(){
	}

	public CmsDomain(
		java.lang.String domain
	){
		this.domain = domain;
	}

	public java.lang.String getDomain() {
		return this.domain;
	}
	
	public void setDomain(java.lang.String value) {
		this.domain = StringUtils.trim(value);
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
		this.ip = StringUtils.trim(value);
	}

	static Cache cache = CacheManager.createCache(CmsDomain.class, 200);
	public String getHttpStatus() {
		return cache.get(domain,3600,new ValueCallback<String>() {
			@Override
			public String create(String key) {
				return HttpStatusCheckUtil.getHttpStatus("www."+domain);
			}
		});
	}
	
	public String getYesterdayOuterLinked() {
		String site = "www."+domain;
		return StringHelper.getYesterdayOuterLinked(site);
	}
	
	public boolean isHttpSuccess() {
		return HttpStatusCheckUtil.isHttpSuccess(getHttpStatus());
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDomain())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj instanceof CmsDomain == false) return false;
		CmsDomain other = (CmsDomain)obj;
		return new EqualsBuilder()
			.append(getDomain(),other.getDomain())
			.isEquals();
	}
}

