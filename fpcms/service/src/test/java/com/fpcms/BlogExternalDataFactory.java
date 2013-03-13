/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms;

import com.fpcms.model.BlogExternal;
import com.fpcms.query.BlogExternalQuery;


/**
 * 用于生成BlogExternal相关数据对象的默认值
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 * 
 */
public class BlogExternalDataFactory {
	
	public static BlogExternalQuery newBlogExternalQuery() {
		BlogExternalQuery query = new BlogExternalQuery();
		query.setPage(1);
		query.setPageSize(10);
		
	  	query.setBlogRpcUrl(new String("1"));
	  	query.setBlogName(new String("1"));
	  	query.setTags(new String("1"));
	  	query.setCategories(new String("1"));
	  	query.setBlogRpcApi(new String("1"));
	  	query.setBlogDesc(new String("1"));
		return query;
	}
	
	public static BlogExternal newBlogExternal() {
		BlogExternal obj = new BlogExternal();
		
	  	obj.setBlogRpcUrl(new java.lang.String("1"));
	  	obj.setBlogName(new java.lang.String("1"));
	  	obj.setTags(new java.lang.String("1"));
	  	obj.setCategories(new java.lang.String("1"));
	  	obj.setBlogRpcApi(new java.lang.String("1"));
	  	obj.setBlogDesc(new java.lang.String("1"));
		return obj;
	}
}