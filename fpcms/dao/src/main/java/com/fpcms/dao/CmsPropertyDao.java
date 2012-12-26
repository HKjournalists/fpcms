/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao;

import java.util.List;

import com.duowan.common.util.page.Page;
import com.fpcms.model.CmsProperty;
import com.fpcms.query.CmsPropertyQuery;

/**
 * tableName: cms_property
 * [CmsProperty] 的Dao操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public interface CmsPropertyDao {
	
	public void insert(CmsProperty entity);
	
	public int update(CmsProperty entity);

	public int deleteById(String propGroup, String propKey);
	
	public CmsProperty getById(String propGroup, String propKey);
	
	public Page<CmsProperty> findPage(CmsPropertyQuery query);

	public List<CmsProperty> findByGroup(String group);	
	
}
