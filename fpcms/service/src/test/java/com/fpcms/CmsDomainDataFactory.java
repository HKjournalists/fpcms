/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms;

import com.fpcms.model.CmsDomain;
import com.fpcms.query.CmsDomainQuery;


/**
 * 用于生成CmsDomain相关数据对象的默认值
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 * 
 */
public class CmsDomainDataFactory {
	
	public static CmsDomainQuery newCmsDomainQuery() {
		CmsDomainQuery query = new CmsDomainQuery();
		query.setPage(1);
		query.setPageSize(10);
		
	  	query.setRemarks(new String("1"));
	  	query.setProps(new String("1"));
	  	query.setIp(new String("1"));
		return query;
	}
	
	public static CmsDomain newCmsDomain() {
		CmsDomain obj = new CmsDomain();
		
	  	obj.setRemarks(new java.lang.String("1"));
	  	obj.setProps(new java.lang.String("1"));
	  	obj.setIp(new java.lang.String("1"));
		return obj;
	}
}