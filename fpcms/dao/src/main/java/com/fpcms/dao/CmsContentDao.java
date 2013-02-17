/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao;

import java.util.Date;
import java.util.List;

import com.duowan.common.util.DateRange;
import com.duowan.common.util.page.Page;
import com.duowan.common.util.page.PageQuery;
import com.fpcms.model.CmsContent;
import com.fpcms.query.CmsContentQuery;

/**
 * tableName: cms_content
 * [CmsContent] 的Dao操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public interface CmsContentDao {
	
	public void insert(CmsContent entity);
	
	public int update(CmsContent entity);

	public int deleteById(long id);
	
	public CmsContent getById(long id);
	
	public Page<CmsContent> findPage(CmsContentQuery query);

	public CmsContent getNextCmsContent(Date dateCreated,String site,long id);

	public CmsContent getPreCmsContent(Date dateCreated,String site,long id);

	public int countByTitle(Date start, Date end, String title);

	public int countBySearchKeyword(Date start, Date end, String searchKeyword);
	
	public int countBySourceUrl(Date start, Date end, String sourceUrl);
	
	public Page<CmsContent> findPage(PageQuery pageQuery, String site,String channelCode, DateRange createdRange);

	public Page findBySiteLike(PageQuery pageQuery, String site,String channelCode, DateRange createdRange);
	
	public CmsContent getById(Date dateCreated, long id);

	public CmsContent findLastBySite(String site);

	public CmsContent findFirstByCreatedDay(String site,Date createdDay);


	
}
