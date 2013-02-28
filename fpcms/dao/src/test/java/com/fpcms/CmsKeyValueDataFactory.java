/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms;

import java.util.Date;

import com.fpcms.model.CmsKeyValue;
import com.fpcms.query.CmsKeyValueQuery;


/**
 * 用于生成CmsKeyValue相关数据对象的默认值
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 * 
 */
public class CmsKeyValueDataFactory {
	
	public static CmsKeyValueQuery newCmsKeyValueQuery() {
		CmsKeyValueQuery query = new CmsKeyValueQuery();
		query.setPage(1);
		query.setPageSize(10);
		
		query.setDateCreatedBegin(new Date(System.currentTimeMillis()));
		query.setDateCreatedEnd(new Date(System.currentTimeMillis()));
	  	query.setValue(new String("1"));
		return query;
	}
	
	public static CmsKeyValue newCmsKeyValue() {
		CmsKeyValue obj = new CmsKeyValue();
		obj.setStrKey("key");
		obj.setKeyGroup("group");
	  	obj.setDateCreated(new java.util.Date(System.currentTimeMillis()));
	  	obj.setValue(new java.lang.String("1"));
		return obj;
	}
}