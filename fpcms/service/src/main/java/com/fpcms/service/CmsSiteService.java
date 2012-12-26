/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service;

import com.duowan.common.util.page.Page;
import com.fpcms.model.CmsSite;
import com.fpcms.query.CmsSiteQuery;


/**
 * [CmsSite] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public interface CmsSiteService {

	/** 
	 * 创建CmsSite
	 **/
	public CmsSite create(CmsSite cmsSite);
	
	/** 
	 * 更新CmsSite
	 **/	
    public CmsSite update(CmsSite cmsSite);
    
	/** 
	 * 删除CmsSite
	 **/
    public void removeById(String siteDomain);
    
	/** 
	 * 根据ID得到CmsSite
	 **/    
    public CmsSite getById(String siteDomain);
    
	/** 
	 * 分页查询: CmsSite
	 **/      
	public Page<CmsSite> findPage(CmsSiteQuery query);
	
    
}
