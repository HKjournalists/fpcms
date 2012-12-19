/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service.impl;

import static com.duowan.common.util.holder.BeanValidatorHolder.validateWithException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.duowan.common.util.page.Page;
import com.duowan.common.util.tree.NodeWrapper;
import com.fpcms.dao.CmsChannelDao;
import com.fpcms.model.CmsChannel;
import com.fpcms.query.CmsChannelQuery;
import com.fpcms.service.CmsChannelService;


/**
 * [CmsChannel] 的业务操作实现类
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service("cmsChannelService")
@Transactional
public class CmsChannelServiceImpl implements CmsChannelService {

	protected static final Logger log = LoggerFactory.getLogger(CmsChannelServiceImpl.class);
	
	//
	// 请删除无用的方法，代码生成器只是为你生成一个架子
	//
	
	private CmsChannelDao cmsChannelDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setCmsChannelDao(CmsChannelDao dao) {
		this.cmsChannelDao = dao;
	}
	
	/** 
	 * 创建CmsChannel
	 **/
	public CmsChannel create(CmsChannel cmsChannel) {
	    Assert.notNull(cmsChannel,"'cmsChannel' must be not null");
	    initDefaultValuesForCreate(cmsChannel);
	    new CmsChannelChecker().checkCreateCmsChannel(cmsChannel);
	    cmsChannelDao.insert(cmsChannel);
	    return cmsChannel;
	}
	
	/** 
	 * 更新CmsChannel
	 **/	
    public CmsChannel update(CmsChannel cmsChannel) {
        Assert.notNull(cmsChannel,"'cmsChannel' must be not null");
        new CmsChannelChecker().checkUpdateCmsChannel(cmsChannel);
        cmsChannelDao.update(cmsChannel);
        return cmsChannel;
    }	
    
	/** 
	 * 删除CmsChannel
	 **/
    public void removeById(long id) {
        cmsChannelDao.deleteById(id);
    }
    
	/** 
	 * 根据ID得到CmsChannel
	 **/    
    public CmsChannel getById(long id) {
        return cmsChannelDao.getById(id);
    }
    
	/** 
	 * 分页查询: CmsChannel
	 **/      
	@Transactional(readOnly=true)
	public Page<CmsChannel> findPage(CmsChannelQuery query) {
	    Assert.notNull(query,"'query' must be not null");
		return cmsChannelDao.findPage(query);
	}
	
    
	/**
	 * 为创建时初始化相关默认值 
	 **/
    public void initDefaultValuesForCreate(CmsChannel cmsChannel) {
    }
    
    /**
     * CmsChannel的属性检查类,根据自己需要编写自定义检查
     **/
    public class CmsChannelChecker {
        /**可以在此检查只有更新才需要的特殊检查 */
        public void checkUpdateCmsChannel(CmsChannel cmsChannel) {
            checkCmsChannel(cmsChannel);
        }
    
        /**可以在此检查只有创建才需要的特殊检查 */
        public void checkCreateCmsChannel(CmsChannel cmsChannel) {
            checkCmsChannel(cmsChannel);
        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void checkCmsChannel(CmsChannel cmsChannel) {
        	// Bean Validator检查,属性检查失败将抛异常
            validateWithException(cmsChannel);
            
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }
    }

	@Override
	public NodeWrapper<CmsChannel> createTree(long rootNodeId) {
		List<CmsChannel> list = cmsChannelDao.findAll();
		return CmsChannel.createTree(list, rootNodeId);
	}
	
	
	@Override
	public String createTreeXmlString(long rootNodeId) {
		 NodeWrapper<CmsChannel> root = createTree(rootNodeId);
		 StringBuilder sb = new StringBuilder();
		 sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		 sb.append("<tree id='0'>");
		 appendNodeXml(root, sb);
		 sb.append("</tree>");
		 return sb.toString();
	}

	private void appendNodeXml(NodeWrapper<CmsChannel> root, StringBuilder sb) {
		sb.append(String.format("<item text='%s' id='%s'>",root.getNode().getChannelName(),root.getNode().getId()));
		sb.append(String.format("<userdata name='channelCode'>%s</userdata>",root.getNode().getChannelCode()));
		sb.append(String.format("<userdata name='site'>%s</userdata>",root.getNode().getSite()));
		 for(NodeWrapper<CmsChannel> child : root.getChilds()) {
			 appendNodeXml(child,sb);
		 }
		 sb.append("</item>");
	}

	@Override
	public Map<Long, String> getChannelMapping() {
		List<CmsChannel> list = cmsChannelDao.findAll();
		Map<Long,String> result = new HashMap<Long,String>();
		for(CmsChannel item : list) {
			result.put(item.getId(), item.getChannelCode());
		}
		return result;
	}
	
}
