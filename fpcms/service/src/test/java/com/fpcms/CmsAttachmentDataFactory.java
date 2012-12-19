/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms;

import java.util.Date;

import com.fpcms.model.CmsAttachment;
import com.fpcms.query.CmsAttachmentQuery;


/**
 * 用于生成CmsAttachment相关数据对象的默认值
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 * 
 */
public class CmsAttachmentDataFactory {
	
	public static CmsAttachmentQuery newCmsAttachmentQuery() {
		CmsAttachmentQuery query = new CmsAttachmentQuery();
		query.setPage(1);
		query.setPageSize(10);
		
	  	query.setCode(new String("1"));
	  	query.setRemarks(new String("1"));
		query.setDateLastModifiedBegin(new Date(System.currentTimeMillis()));
		query.setDateLastModifiedEnd(new Date(System.currentTimeMillis()));
	  	query.setAuthor(new String("1"));
		return query;
	}
	
	public static CmsAttachment newCmsAttachment() {
		CmsAttachment obj = new CmsAttachment();
		
	  	obj.setCode(new java.lang.String("1"));
	  	obj.setAttachment(new byte[0]);
	  	obj.setRemarks(new java.lang.String("1"));
	  	obj.setDateLastModified(new java.util.Date(System.currentTimeMillis()));
	  	obj.setAuthor(new java.lang.String("1"));
		return obj;
	}
}