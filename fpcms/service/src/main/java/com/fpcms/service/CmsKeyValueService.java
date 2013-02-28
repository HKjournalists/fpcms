/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.service;

import java.util.Date;

import com.duowan.common.util.page.Page;
import com.fpcms.model.CmsKeyValue;
import com.fpcms.query.CmsKeyValueQuery;


/**
 * [CmsKeyValue] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public interface CmsKeyValueService {

	/** 
	 * 创建CmsKeyValue
	 **/
	public CmsKeyValue create(CmsKeyValue cmsKeyValue);
	
	
	/** 
	 * 更新CmsKeyValue
	 **/	
    public CmsKeyValue update(CmsKeyValue cmsKeyValue);
    
	/** 
	 * 删除CmsKeyValue
	 **/
    public void removeById(String keyGroup, String key);
    
	/** 
	 * 根据ID得到CmsKeyValue
	 **/    
    public CmsKeyValue getById(String keyGroup, String key);
    
	/** 
	 * 分页查询: CmsKeyValue
	 **/      
	public Page<CmsKeyValue> findPage(CmsKeyValueQuery query);

	/**
	 * 判断是否存在
	 * @param cmsKeyValue
	 * @return
	 */
	public boolean exist(CmsKeyValue cmsKeyValue);
	
	/**
	 * 删除多久时间以前的数据
	 * @param beforeDateCreated
	 * @return
	 */
	public int deleteBy(Date beforeDateCreated);
    
}
