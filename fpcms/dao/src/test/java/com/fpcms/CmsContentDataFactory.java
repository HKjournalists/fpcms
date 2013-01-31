/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms;

import java.util.Date;

import com.fpcms.model.CmsContent;
import com.fpcms.query.CmsContentQuery;


/**
 * 用于生成CmsContent相关数据对象的默认值
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 * 
 */
public class CmsContentDataFactory {
	
	public static CmsContentQuery newCmsContentQuery() {
		CmsContentQuery query = new CmsContentQuery();
		query.setPage(1);
		query.setPageSize(10);
		
	  	query.setChannelCode(new String("1"));
	  	query.setTags(new String("1"));
	  	query.setHeadTitle(new String("1"));
	  	query.setTitle(new String("1"));
	  	query.setContent(new String("1"));
	  	query.setAuthor(new String("1"));
		query.setDateCreatedBegin(new Date(System.currentTimeMillis()));
		query.setDateCreatedEnd(new Date(System.currentTimeMillis()));
		query.setDateLastModifiedBegin(new Date(System.currentTimeMillis()));
		query.setDateLastModifiedEnd(new Date(System.currentTimeMillis()));
	  	query.setSite(new String("1"));
		return query;
	}
	
	public static CmsContent newCmsContent() {
		CmsContent obj = new CmsContent();
		
	  	obj.setChannelCode(new java.lang.String("1"));
	  	obj.setTags(new java.lang.String("1"));
	  	obj.setHeadTitle(new java.lang.String("1"));
	  	obj.setTitle(new java.lang.String("1"));
	  	obj.setContent(new java.lang.String("1"));
	  	obj.setAuthor(new java.lang.String("1"));
	  	obj.setDateCreated(new java.util.Date(System.currentTimeMillis()));
	  	obj.setDateLastModified(new java.util.Date(System.currentTimeMillis()));
	  	obj.setSite(new java.lang.String("1"));
	  	obj.setSearchKeyword("1");
		return obj;
	}
}