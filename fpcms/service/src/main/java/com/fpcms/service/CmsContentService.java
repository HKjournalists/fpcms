/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service;

import java.util.List;

import com.duowan.common.util.page.Page;
import com.fpcms.model.CmsContent;
import com.fpcms.query.CmsContentQuery;


/**
 * [CmsContent] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public interface CmsContentService {

	/** 
	 * 创建CmsContent
	 **/
	public CmsContent create(CmsContent cmsContent);
	
	/** 
	 * 更新CmsContent
	 **/	
    public CmsContent update(CmsContent cmsContent);
    
	/** 
	 * 删除CmsContent
	 **/
    public void removeById(long id);
    
	/** 
	 * 根据ID得到CmsContent
	 **/    
    public CmsContent getById(long id);
    
	/** 
	 * 分页查询: CmsContent
	 **/      
	public Page<CmsContent> findPage(CmsContentQuery query);
	
    public List<CmsContent> findByChannelCode(String channelCode);
    
    /**
     * 随机生成文章
     */
    public void genRandomCmsContent();
}
