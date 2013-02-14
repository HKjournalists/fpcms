/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.service;

import java.util.List;

import com.duowan.common.util.page.Page;
import com.fpcms.model.CmsDomain;
import com.fpcms.query.CmsDomainQuery;


/**
 * [CmsDomain] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public interface CmsDomainService {

	/** 
	 * 创建CmsDomain
	 **/
	public CmsDomain create(CmsDomain cmsDomain);
	
	/** 
	 * 更新CmsDomain
	 **/	
    public CmsDomain update(CmsDomain cmsDomain);
    
	/** 
	 * 删除CmsDomain
	 **/
    public void removeById(String domain);
    
	/** 
	 * 根据ID得到CmsDomain
	 **/    
    public CmsDomain getById(String domain);
    
	/** 
	 * 分页查询: CmsDomain
	 **/      
	public Page<CmsDomain> findPage(CmsDomainQuery query);
	
	/** 
	 * 根据Remarks(备注) 查找 CmsDomain
	 **/ 	
	public CmsDomain getByRemarks(String remarks);
	
    public List<CmsDomain> findAll();
}
