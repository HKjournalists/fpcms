/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao;

import java.util.List;

import com.github.rapid.common.util.page.Page;
import com.fpcms.model.CmsSite;
import com.fpcms.query.CmsSiteQuery;

/**
 * tableName: cms_site
 * [CmsSite] 的Dao操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public interface CmsSiteDao {
	
	public void insert(CmsSite entity);
	
	public int update(CmsSite entity);

	public int deleteById(String siteDomain);
	
	public CmsSite getById(String siteDomain);
	

	public Page<CmsSite> findPage(CmsSiteQuery query);

	public List<CmsSite> findAll();

	public List<CmsSite> findSubSites(String domain);	
	
}
