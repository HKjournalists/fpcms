/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms;

import java.util.Date;

import com.fpcms.model.CmsChannel;
import com.fpcms.query.CmsChannelQuery;


/**
 * 用于生成CmsChannel相关数据对象的默认值
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 * 
 */
public class CmsChannelDataFactory {
	
	public static CmsChannelQuery newCmsChannelQuery() {
		CmsChannelQuery query = new CmsChannelQuery();
		query.setPage(1);
		query.setPageSize(10);
		
	  	query.setChannelName(new String("1"));
	  	query.setChannelCode(new String("1"));
	  	query.setChannelDesc(new String("1"));
	  	query.setParentId(new Long("1"));
		query.setDateLastModifiedBegin(new Date(System.currentTimeMillis()));
		query.setDateLastModifiedEnd(new Date(System.currentTimeMillis()));
	  	query.setAuthor(new String("1"));
	  	query.setSite(new String("1"));
		return query;
	}
	
	public static CmsChannel newCmsChannel() {
		CmsChannel obj = new CmsChannel();
		
	  	obj.setChannelName(new java.lang.String("1"));
	  	obj.setChannelCode(new java.lang.String("1"));
	  	obj.setChannelDesc(new java.lang.String("1"));
	  	obj.setParentId(new java.lang.Long("1"));
	  	obj.setDateLastModified(new java.util.Date(System.currentTimeMillis()));
	  	obj.setAuthor(new java.lang.String("1"));
	  	obj.setSite(new java.lang.String("1"));
		return obj;
	}
}