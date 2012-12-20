/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.duowan.common.util.page.Page;
import com.duowan.common.util.tree.NodeWrapper;
import com.fpcms.model.CmsChannel;
import com.fpcms.query.CmsChannelQuery;


/**
 * [CmsChannel] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public interface CmsChannelService {

	/** 
	 * 创建CmsChannel
	 **/
	public CmsChannel create(CmsChannel cmsChannel);
	
	/** 
	 * 更新CmsChannel
	 **/	
    public CmsChannel update(CmsChannel cmsChannel);
    
	/** 
	 * 删除CmsChannel
	 **/
    public void removeById(long id);
    
	/** 
	 * 根据ID得到CmsChannel
	 **/    
    public CmsChannel getById(long id);
    
	/** 
	 * 分页查询: CmsChannel
	 **/      
	public Page<CmsChannel> findPage(CmsChannelQuery query);
	
    
	public NodeWrapper<CmsChannel> createTree(long rootNodeId);
	
	
	public String createTreeXmlString(long rootNodeId);
	
	/**
	 * 得到频道代码与频道ID的映射关系
	 * 
	 * @return Map<频道代码，频道ID>
	 */
	public Map<String, Long> getChannelMapping();
	
	public List<CmsChannel> findChildsByChannelId(long channelId);
	
	public List<CmsChannel> findChildsByChannelCode(String channelCode);
	
	public CmsChannel findByChannelCode(String channelCode);
}
