/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.model;

import static com.duowan.common.util.DateFormats.DATE_FORMAT;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/**
 * tableName: cms_key_value [CmsKeyValue] 
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsKeyValue  implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_DATE_CREATED = DATE_FORMAT;
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * 创建时间       db_column: date_created 
     */ 	
	@NotNull 
	private java.util.Date dateCreated;
	
    /**
     * 分组       db_column: key_group 
     */ 	
	@Length(max=30)
	@NotBlank
	private java.lang.String keyGroup;
	
    /**
     * str_key       db_column: str_key 
     */ 	
	@Length(max=100)
	@NotBlank
	private java.lang.String strKey;
	
    /**
     * value       db_column: value 
     */ 	
	@Length(max=500)
	private java.lang.String value;
	
	//columns END

	public CmsKeyValue(){
	}

	public CmsKeyValue(
		java.lang.String keyGroup,
		java.lang.String key
	){
		this.keyGroup = keyGroup;
		this.strKey = key;
	}

	public java.util.Date getDateCreated() {
		return this.dateCreated;
	}
	
	public void setDateCreated(java.util.Date value) {
		this.dateCreated = value;
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
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getKeyGroup())
			.append(getStrKey())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj instanceof CmsKeyValue == false) return false;
		CmsKeyValue other = (CmsKeyValue)obj;
		return new EqualsBuilder()
			.append(getKeyGroup(),other.getKeyGroup())
			.append(getStrKey(),other.getStrKey())
			.isEquals();
	}
}

