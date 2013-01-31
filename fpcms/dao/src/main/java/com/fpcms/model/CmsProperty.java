/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.model;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;


/**
 * tableName: cms_property [CmsProperty] 
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsProperty  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * propGroup       db_column: prop_group 
     */ 	
	@Length(max=255)
	private java.lang.String propGroup;
	
    /**
     * propKey       db_column: prop_key 
     */ 	
	@Length(max=255)
	private java.lang.String propKey;
	
    /**
     * propValue       db_column: prop_value 
     */ 	
	@Length(max=255)
	private java.lang.String propValue;
	
    /**
     * ramarks       db_column: ramarks 
     */ 	
	@Length(max=255)
	private java.lang.String ramarks;
	
	//columns END

	public CmsProperty(){
	}

	public CmsProperty(
		java.lang.String propGroup,
		java.lang.String propKey
	){
		this.propGroup = propGroup;
		this.propKey = propKey;
	}

	public java.lang.String getPropGroup() {
		return this.propGroup;
	}
	
	public void setPropGroup(java.lang.String value) {
		this.propGroup = StringUtils.trim(value);
	}
	
	public java.lang.String getPropKey() {
		return this.propKey;
	}
	
	public void setPropKey(java.lang.String value) {
		this.propKey = StringUtils.trim(value);
	}
	
	public java.lang.String getPropValue() {
		return this.propValue;
	}
	
	public void setPropValue(java.lang.String value) {
		this.propValue = StringUtils.trim(value);
	}
	
	public java.lang.String getRamarks() {
		return this.ramarks;
	}
	
	public void setRamarks(java.lang.String value) {
		this.ramarks = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPropGroup())
			.append(getPropKey())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj instanceof CmsProperty == false) return false;
		CmsProperty other = (CmsProperty)obj;
		return new EqualsBuilder()
			.append(getPropGroup(),other.getPropGroup())
			.append(getPropKey(),other.getPropKey())
			.isEquals();
	}
}

