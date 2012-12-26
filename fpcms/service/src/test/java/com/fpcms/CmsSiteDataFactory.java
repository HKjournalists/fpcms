/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms;

import com.fpcms.model.CmsSite;
import com.fpcms.query.CmsSiteQuery;


/**
 * 用于生成CmsSite相关数据对象的默认值
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 * 
 */
public class CmsSiteDataFactory {
	
	public static CmsSiteQuery newCmsSiteQuery() {
		CmsSiteQuery query = new CmsSiteQuery();
		query.setPage(1);
		query.setPageSize(10);
		
	  	query.setSiteName(new String("1"));
	  	query.setSiteDesc(new String("1"));
	  	query.setSiteCity(new String("1"));
	  	query.setSiteKeyword(new String("1"));
	  	query.setRemarks(new String("1"));
		return query;
	}
	
	public static CmsSite newCmsSite() {
		CmsSite obj = new CmsSite();
		
	  	obj.setSiteName(new java.lang.String("1"));
	  	obj.setSiteDesc(new java.lang.String("1"));
	  	obj.setSiteCity(new java.lang.String("1"));
	  	obj.setSiteKeyword(new java.lang.String("1"));
	  	obj.setRemarks(new java.lang.String("1"));
		return obj;
	}
}