/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.dao;

import java.util.List;

import com.duowan.common.util.page.Page;
import com.fpcms.model.CmsDomain;
import com.fpcms.query.CmsDomainQuery;

/**
 * tableName: cms_domain
 * [CmsDomain] 的Dao操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public interface CmsDomainDao {
	
	public void insert(CmsDomain entity);
	
	public int update(CmsDomain entity);

	public int deleteById(String domain);
	
	public CmsDomain getById(String domain);
	
	public CmsDomain getByRemarks(String remarks);
	

	public Page<CmsDomain> findPage(CmsDomainQuery query);

	public List<CmsDomain> findAll();	
	
}
