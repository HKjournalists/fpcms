/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms;

import com.fpcms.model.CmsProperty;
import com.fpcms.query.CmsPropertyQuery;


/**
 * 用于生成CmsProperty相关数据对象的默认值
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 * 
 */
public class CmsPropertyDataFactory {
	
	public static CmsPropertyQuery newCmsPropertyQuery() {
		CmsPropertyQuery query = new CmsPropertyQuery();
		query.setPage(1);
		query.setPageSize(10);
		
	  	query.setPropValue(new String("1"));
	  	query.setRamarks(new String("1"));
		return query;
	}
	
	public static CmsProperty newCmsProperty() {
		CmsProperty obj = new CmsProperty();
		obj.setPropGroup("test_group");
		obj.setPropKey("test_key");
	  	obj.setPropValue(new java.lang.String("1"));
	  	obj.setRamarks(new java.lang.String("1"));
		return obj;
	}
}