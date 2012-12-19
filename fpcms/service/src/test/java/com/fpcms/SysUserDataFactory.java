/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms;

import com.fpcms.model.SysUser;
import com.fpcms.query.SysUserQuery;


/**
 * 用于生成SysUser相关数据对象的默认值
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 * 
 */
public class SysUserDataFactory {
	
	public static SysUserQuery newSysUserQuery() {
		SysUserQuery query = new SysUserQuery();
		query.setPage(1);
		query.setPageSize(10);
		
	  	query.setUsername(new String("1"));
	  	query.setPassword(new String("1"));
	  	query.setRemark(new String("1"));
	  	query.setEnabled(new Integer("1"));
		return query;
	}
	
	public static SysUser newSysUser() {
		SysUser obj = new SysUser();
		
	  	obj.setUsername(new java.lang.String("1"));
	  	obj.setPassword(new java.lang.String("1"));
	  	obj.setRemark(new java.lang.String("1"));
	  	obj.setEnabled(new Integer("1"));
		return obj;
	}
}