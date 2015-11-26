/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.dao;

import java.util.Date;

import com.github.rapid.common.util.page.Page;
import com.fpcms.model.CmsKeyValue;
import com.fpcms.query.CmsKeyValueQuery;

/**
 * tableName: cms_key_value
 * [CmsKeyValue] 的Dao操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public interface CmsKeyValueDao {
	
	public void insert(CmsKeyValue entity);
	
	public int update(CmsKeyValue entity);

	public int deleteById(String keyGroup, String key);
	
	public CmsKeyValue getById(String keyGroup, String key);
	

	public Page<CmsKeyValue> findPage(CmsKeyValueQuery query);
	
	/**
	 * 删除多久时间以前的数据
	 * @param beforeDateCreated
	 * @return
	 */
	public int deleteBy(Date beforeDateCreated);
	
}
