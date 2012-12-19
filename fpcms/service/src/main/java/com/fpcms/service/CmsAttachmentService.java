/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service;

import com.duowan.common.util.page.Page;
import com.fpcms.model.CmsAttachment;
import com.fpcms.query.CmsAttachmentQuery;


/**
 * [CmsAttachment] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public interface CmsAttachmentService {

	/** 
	 * 创建CmsAttachment
	 **/
	public CmsAttachment create(CmsAttachment cmsAttachment);
	
	/** 
	 * 更新CmsAttachment
	 **/	
    public CmsAttachment update(CmsAttachment cmsAttachment);
    
	/** 
	 * 删除CmsAttachment
	 **/
    public void removeById(long id);
    
	/** 
	 * 根据ID得到CmsAttachment
	 **/    
    public CmsAttachment getById(long id);
    
	/** 
	 * 分页查询: CmsAttachment
	 **/      
	public Page<CmsAttachment> findPage(CmsAttachmentQuery query);
	
    
}
