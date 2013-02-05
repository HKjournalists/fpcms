/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service;

import java.util.Date;

import com.duowan.common.util.DateRange;
import com.duowan.common.util.page.Page;
import com.duowan.common.util.page.PageQuery;
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
	 * 根据ID得到CmsContent
	 **/    
    public CmsContent getById(Date dateCreated,long id);
    
	/** 
	 * 根据ID得到上一条记录
	 **/    
    public CmsContent getPreCmsContent(Date dateCreated,String site,long id);

	/** 
	 * 根据ID得到下一条记录
	 **/    
    public CmsContent getNextCmsContent(Date dateCreated,String site,long id);
    
	/** 
	 * 分页查询: CmsContent
	 **/      
	public Page<CmsContent> findPage(CmsContentQuery query);
	
	
	public Page<CmsContent> findPage(PageQuery pageQuery,String site,String channelCode,DateRange createdRange);
    /**
     * 随机生成文章
     */
    public void genRandomCmsContent();
    /**
     * 根据site随机生成文章
     */
    public void genSiteRandomCmsContent(String site);

	public CmsContent findLastBySite(String site);
	
	public int countBySourceUrl(Date start, Date end, String sourceUrl);

	public CmsContent findFirstByCreatedDay(String site,Date createdDay);
}
