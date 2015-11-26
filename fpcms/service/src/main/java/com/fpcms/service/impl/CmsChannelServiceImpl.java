/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service.impl;

import static com.github.rapid.common.util.holder.BeanValidatorHolder.validateWithException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.rapid.common.util.page.Page;
import com.github.rapid.common.util.tree.NodeWrapper;
import com.fpcms.common.util.Constants;
import com.fpcms.dao.CmsChannelDao;
import com.fpcms.model.CmsChannel;
import com.fpcms.query.CmsChannelQuery;
import com.fpcms.service.CmsChannelService;
import com.fpcms.service.CmsSiteService;


/**
 * [CmsChannel] 的业务操作实现类
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service("cmsChannelService")
public class CmsChannelServiceImpl implements CmsChannelService {

	protected static final Logger log = LoggerFactory.getLogger(CmsChannelServiceImpl.class);
	
	//
	// 请删除无用的方法，代码生成器只是为你生成一个架子
	//
	private CmsSiteService cmsSiteService;
	
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
	    cmsChannel.setId(System.currentTimeMillis());
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
    public void removeById(String site,long id) {
        cmsChannelDao.deleteById(site,id);
    }
    
	/** 
	 * 根据ID得到CmsChannel
	 **/    
    public CmsChannel getById(String site,long id) {
        return cmsChannelDao.getById(site,id);
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

	public NodeWrapper<CmsChannel> createTree(String site,long rootNodeId) {
		List<CmsChannel> list = cmsChannelDao.findBySite(site);
		return CmsChannel.createTree(list, rootNodeId);
	}
	
	
	@Override
	public String createTreeXmlString(String site) {
		 createDefaultChannelsIfRequired(site);
		 try {
			 NodeWrapper<CmsChannel> root = createTree(site,Constants.TREE_ROOT_ID);
			 StringBuilder sb = new StringBuilder();
			 sb.append("<?xml version='1.0' encoding='UTF-8'?>");
			 sb.append("<tree id='0'>");
			 appendNodeXml(root, sb);
			 sb.append("</tree>");
			 return sb.toString();
		 }catch(Exception e) {
			 log.error("createTree error for site:"+site,e);
			 return "";
		 }
	}

	public boolean createDefaultChannelsIfRequired(String site) {
		long count = cmsChannelDao.countBySite(site);
		if (count <= 0) {
			new DefaultChannelCreator(site).createDefaultChannels();
			return true;
		}
		return false;
	}

	public class DefaultChannelCreator {
		private String site;
		
		public DefaultChannelCreator(String site) {
			super();
			Assert.hasText(site,"site must be not empty");
			this.site = site;
		}

		public void createDefaultChannels() {
			newChannel(CmsChannel.ROOT);
			newChannel(CmsChannel.HOME);
			newChannel(CmsChannel.NEWS);
			newChannel(CmsChannel.NAV);
			for(CmsChannel item : CmsChannel.NAV_SUB_CHANNELS) {
				newChannel(item);
			}
		}
	
		private CmsChannel newChannel(CmsChannel template) {
			CmsChannel target = template.clone(site);
			cmsChannelDao.insert(target);
			return target;
		}

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
	public Map<String, Long> getChannelMapping(String site) {
		List<CmsChannel> list = cmsChannelDao.findBySite(site);
		Map<String,Long> result = new HashMap<String,Long>();
		for(CmsChannel item : list) {
			result.put(item.getChannelCode(), item.getId());
		}
		return result;
	}

	public List<CmsChannel> findChildsByChannelId(String site,long channelId) {
		return cmsChannelDao.findChildsByChannelId(site,channelId);
	}
	
	public List<CmsChannel> findChildsByChannelCode(String site,String channelCode) {
		Long channelId = getChannelId(site,channelCode);
		Assert.notNull(channelId,"not found channelId by channelCode:"+channelCode);
		return cmsChannelDao.findChildsByChannelId(site,channelId);
	}

	@Override
	public CmsChannel findByChannelCode(String site,String channelCode) {
		long channelId = getChannelId(site,channelCode);
		return cmsChannelDao.getById(site,channelId);
	}

	private Long getChannelId(String site,String channelCode) {
		Long result =  getChannelMapping(site).get(channelCode);
		if(result == null) throw new IllegalArgumentException("not found channelId by channelCode:"+channelCode);
		return result;
	}
	
}
