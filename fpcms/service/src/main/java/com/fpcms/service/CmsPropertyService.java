/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service;

import java.util.Map;

import com.duowan.common.util.page.Page;
import com.fpcms.model.CmsProperty;
import com.fpcms.query.CmsPropertyQuery;


/**
 * [CmsProperty] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public interface CmsPropertyService {

	/** 
	 * 创建CmsProperty
	 **/
	public CmsProperty create(CmsProperty cmsProperty);
	
	/** 
	 * 更新CmsProperty
	 **/	
    public CmsProperty update(CmsProperty cmsProperty);
    
	/** 
	 * 删除CmsProperty
	 **/
    public void removeById(String propGroup, String propKey);
    
	/** 
	 * 根据ID得到CmsProperty
	 **/    
    public CmsProperty getById(String propGroup, String propKey);
    
	/** 
	 * 分页查询: CmsProperty
	 **/      
	public Page<CmsProperty> findPage(CmsPropertyQuery query);
	
//    public Map<String,Map<String,String>> findAllGroup();
    
    public Map<String,String> findByGroup(String group);
}
