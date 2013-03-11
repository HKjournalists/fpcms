/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service;

import java.util.List;
import java.util.Map;

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
	
	/**
	 * 得到站点属性
	 * @return
	 */
	public Map<String,String> getSiteProperties(String site);

	public List<CmsSite> findAll();
    
	/**
	 * 初始化所有网站的频道,返回实始化成功的频道
	 * @return
	 */
	public List<CmsSite> initAllSiteDefaultChannels();

	/**
	 * 更新搜索引擎site:搜索的记录数
	 * @return 有更新的记录
	 */
	public List<CmsSite> updateSearchEngineRecord();

	/**
	 * 更新搜索引擎关键字最高排名
	 * @return 有更新的记录
	 */
	public List<CmsSite> updateSearchEngineKeywordMaxRank();
	
	/**
	 * 更新http状态 
	 */
	public void updateHttpStatus();

	public List<CmsSite> findSubSites(String domain);
	
	
	public void batchUpdateProperty(String[] sites, String key, String value);
}
