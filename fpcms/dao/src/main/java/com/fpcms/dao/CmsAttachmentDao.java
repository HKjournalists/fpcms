/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao;

import com.github.rapid.common.util.page.Page;
import com.fpcms.model.CmsAttachment;
import com.fpcms.query.CmsAttachmentQuery;

/**
 * tableName: cms_attachment
 * [CmsAttachment] 的Dao操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public interface CmsAttachmentDao {
	
	public void insert(CmsAttachment entity);
	
	public int update(CmsAttachment entity);

	public int deleteById(long id);
	
	public CmsAttachment getById(long id);
	

	public Page<CmsAttachment> findPage(CmsAttachmentQuery query);	
	
}
