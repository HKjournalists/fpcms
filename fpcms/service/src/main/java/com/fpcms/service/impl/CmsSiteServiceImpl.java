/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service.impl;

import static com.github.rapid.common.util.holder.BeanValidatorHolder.validateWithException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.rapid.common.beanutils.BeanUtils;
import com.github.rapid.common.util.page.Page;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.HttpStatusCheckUtil;
import com.fpcms.common.util.IpUtil;
import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.MapUtil;
import com.fpcms.common.util.SearchEngineUtil;
import com.fpcms.dao.CmsSiteDao;
import com.fpcms.model.CmsSite;
import com.fpcms.model.CmsSitePropertyEnum;
import com.fpcms.query.CmsSiteQuery;
import com.fpcms.service.CmsChannelService;
import com.fpcms.service.CmsPropertyService;
import com.fpcms.service.CmsSiteService;


/**
 * [CmsSite] 的业务操作实现类
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service("cmsSiteService")
public class CmsSiteServiceImpl implements CmsSiteService {

	protected static final Logger log = LoggerFactory.getLogger(CmsSiteServiceImpl.class);
	
	//
	// 请删除无用的方法，代码生成器只是为你生成一个架子
	//
	private CmsSiteDao cmsSiteDao;
	private CmsPropertyService cmsPropertyService;
	private CmsChannelService cmsChannelService;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setCmsSiteDao(CmsSiteDao dao) {
		this.cmsSiteDao = dao;
	}
	
	public void setCmsPropertyService(CmsPropertyService cmsPropertyService) {
		this.cmsPropertyService = cmsPropertyService;
	}

	public void setCmsChannelService(CmsChannelService cmsChannelService) {
		this.cmsChannelService = cmsChannelService;
	}

	/** 
	 * 创建CmsSite
	 **/
	public CmsSite create(CmsSite cmsSite) {
	    Assert.notNull(cmsSite,"'cmsSite' must be not null");
	    initDefaultValuesForCreate(cmsSite);
	    new CmsSiteChecker().checkCreateCmsSite(cmsSite);
	    cmsSiteDao.insert(cmsSite);
	    cmsChannelService.createDefaultChannelsIfRequired(cmsSite.getSiteDomain());
	    return cmsSite;
	}
	
	/** 
	 * 更新CmsSite
	 **/	
    public CmsSite update(CmsSite cmsSite) {
        Assert.notNull(cmsSite,"'cmsSite' must be not null");
        new CmsSiteChecker().checkUpdateCmsSite(cmsSite);
        cmsSiteDao.update(cmsSite);
        return cmsSite;
    }	
    
	/** 
	 * 删除CmsSite
	 **/
    public void removeById(String siteDomain) {
        cmsSiteDao.deleteById(siteDomain);
    }
    
	/** 
	 * 根据ID得到CmsSite
	 **/    
    public CmsSite getById(String siteDomain) {
        return cmsSiteDao.getById(siteDomain);
    }
    
	/** 
	 * 分页查询: CmsSite
	 **/      
	@Transactional(readOnly=true)
	public Page<CmsSite> findPage(CmsSiteQuery query) {
	    Assert.notNull(query,"'query' must be not null");
		return cmsSiteDao.findPage(query);
	}
	
    
	/**
	 * 为创建时初始化相关默认值 
	 **/
    public void initDefaultValuesForCreate(CmsSite cmsSite) {
    }
    
    /**
     * CmsSite的属性检查类,根据自己需要编写自定义检查
     **/
    public class CmsSiteChecker {
        /**可以在此检查只有更新才需要的特殊检查 */
        public void checkUpdateCmsSite(CmsSite cmsSite) {
            checkCmsSite(cmsSite);
        }
    
        /**可以在此检查只有创建才需要的特殊检查 */
        public void checkCreateCmsSite(CmsSite cmsSite) {
            checkCmsSite(cmsSite);
        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void checkCmsSite(CmsSite cmsSite) {
        	// Bean Validator检查,属性检查失败将抛异常
            validateWithException(cmsSite);
            
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }
    }

	@Override
	public Map<String,String> getSiteProperties(String site) {
		CmsSite cmsSite = getById(site);
		if(site == null) {
			throw new RuntimeException("not found CmsSite by site:"+site);
		}
		Map<String,String> localhostProps = cmsPropertyService.findByGroup(Constants.PROPERTY_DEFAULT_GROUP);
		Map<String,String> siteProps = cmsPropertyService.findByGroup(site);
		Map<String,String> cmsSiteMap = BeanUtils.describe(cmsSite);
		
		Map<String,String> result = new HashMap<String,String>();
		result.putAll(cmsSiteMap);
		MapUtil.mergeWithDefaultMap(result,siteProps);
		MapUtil.mergeWithDefaultMap(result,localhostProps);
		
		return result;
	}

	@Override
	public List<CmsSite> findAll() {
		return cmsSiteDao.findAll();
	}
	
	@Override
	public List<CmsSite> initAllSiteDefaultChannels() {
		List<CmsSite> list = new ArrayList<CmsSite>();
		for(CmsSite site : cmsSiteDao.findAll()) {
			if(cmsChannelService.createDefaultChannelsIfRequired(site.getSiteDomain())) {
				list.add(site);
			}
		}
		return list;
	}
	
	@Override
	public void updateHttpStatus(){
		List<CmsSite> list = findAll();
		Collections.sort(list,new ReverseComparator(new BeanComparator("recordBaidu")));
		for(CmsSite site : list) {
			try {
				String status = HttpStatusCheckUtil.getHttpStatus(site.getSiteDomain());
				site.setHttpStatus(status);
				site.setIp(IpUtil.getIp(site.getSiteDomain()));
				update(site);
			}catch(Exception e) {
				log.error("error on update CmsSite httpStatus:"+site.getSiteDomain(),e);
			}
		}
	}
	
	public List<CmsSite> updateSearchEngineRecord() {
		log.info("START updateSearchEngineRecord");
		List<CmsSite> updatedSiteList = new ArrayList<CmsSite>();
		List<CmsSite> list = findAll();
		Collections.sort(list,new ReverseComparator(new BeanComparator("recordBaidu")));
		
		for(CmsSite site : list) {
			try {
				int recordBaidu = SearchEngineUtil.baiduSiteCount(site.getSiteDomain());
				if(recordBaidu != site.getRecordBaidu()) {
					updatedSiteList.add(site);
					site.setRecordBaidu(recordBaidu);
					update(site);
				}
			}catch(Exception e) {
				log.error("error updateSearchEngineRecord on :"+site.getSiteDomain(),e);
			}
		}
		return updatedSiteList;
	}
	
	public synchronized List<CmsSite> updateSearchEngineKeywordMaxRank() {
		return new UpdateSearchEngineKeywordMaxRank().updateSearchEngineKeywordMaxRank();
	}
	
	public class UpdateSearchEngineKeywordMaxRank {
		public synchronized List<CmsSite> updateSearchEngineKeywordMaxRank() {
			log.info("START updateSearchEngineKeywordMaxRank");
			List<CmsSite> updatedSiteList = new ArrayList<CmsSite>();
			
			List<CmsSite> list = findAll();
			Collections.sort(list,new ReverseComparator(new BeanComparator("rankBaidu")));
			
			for(CmsSite site :list) {
				try{
					Map<String,Integer> rankMap = SearchEngineUtil.baiduKeywordsRank(site.getKeyword(), site.getSiteDomain());
					int max = KeywordUtil.getMaxRank(site.getKeyword(),site.getSiteDomain());
					if(max != site.getRankBaidu()) {
						updatedSiteList.add(site);
						site.setRankBaidu(max);
					}
					site.setProperty(CmsSitePropertyEnum.PROP_KEYWORDS_RANK_BAIDU.getCode(), rankMap.toString());
					update(site);
				}catch(Exception e) {
					log.error("error updateSearchEngineKeywordMaxRank on :"+site.getSiteDomain(),e);
				}
			}
			return updatedSiteList;
		}

	}

	@Override
	public List<CmsSite> findSubSites(String domain) {
		return cmsSiteDao.findSubSites(domain);
	}

	@Override
	public void batchUpdateProperty(String[] sites, String key, String value) {
		for(String site : sites) {
			CmsSite cmsSite = getById(site);
			cmsSite.setProperty(key, value);
			update(cmsSite);
		}
	}
	
}
