/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.dao;

import java.util.List;

import com.duowan.common.util.page.Page;
import com.fpcms.model.BlogExternal;
import com.fpcms.query.BlogExternalQuery;

/**
 * tableName: blog_external
 * [BlogExternal] 的Dao操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public interface BlogExternalDao {
	
	public void insert(BlogExternal entity);
	
	public int update(BlogExternal entity);

	public int deleteById(String blogUrl, String username, String password);
	
	public BlogExternal getById(String blogUrl, String username, String password);
	

	public Page<BlogExternal> findPage(BlogExternalQuery query);

	public List<BlogExternal> findAll();	
	
}
