/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service.impl;

import static com.github.rapid.common.util.holder.BeanValidatorHolder.validateWithException;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.rapid.common.util.DateRange;
import com.github.rapid.common.util.page.Page;
import com.github.rapid.common.util.page.PageQuery;
import com.fpcms.common.random_gen_article.RandomArticle;
import com.fpcms.common.random_gen_article.RandomArticleBuilder;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.DomainUtil;
import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.common.util.TextLangUtil;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;
import com.fpcms.dao.CmsContentDao;
import com.fpcms.model.CmsContent;
import com.fpcms.model.CmsDomain;
import com.fpcms.model.CmsSite;
import com.fpcms.query.CmsContentQuery;
import com.fpcms.service.CmsContentService;
import com.fpcms.service.CmsDomainService;
import com.fpcms.service.CmsPropertyService;
import com.fpcms.service.CmsSiteService;


/**
 * [CmsContent] 的业务操作实现类
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service("cmsContentService")
@Transactional
public class CmsContentServiceImpl implements CmsContentService {

	protected static final Logger log = LoggerFactory.getLogger(CmsContentServiceImpl.class);
	
	private CmsPropertyService cmsPropertyService;
	private CmsSiteService cmsSiteService;
	private CmsDomainService cmsDomainService;
	//
	// 请删除无用的方法，代码生成器只是为你生成一个架子
	//
	
	private CmsContentDao cmsContentDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setCmsContentDao(CmsContentDao dao) {
		this.cmsContentDao = dao;
	}
	
	public void setCmsPropertyService(CmsPropertyService cmsPropertyService) {
		this.cmsPropertyService = cmsPropertyService;
	}
	
	public void setCmsSiteService(CmsSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}
	
	public void setCmsDomainService(CmsDomainService cmsDomainService) {
		this.cmsDomainService = cmsDomainService;
	}

	public CmsContent createWithRandomLink(CmsContent cmsContent) {
		return createWithRandomLink(cmsContent,100);
	}
	/** 
	 * 创建CmsContent,并且附加随机链接
	 **/
	public CmsContent createWithRandomLink(CmsContent cmsContent,int randomLinkPercent) {
		if(RandomUtil.randomTrue(randomLinkPercent)) {
			String link = getRandomSiteLink();
			if(StringUtils.isNotBlank(link)) {
			    String linkedContent = cmsContent.getContent();
			    linkedContent = linkedContent + "<a href='"+link+"'>"+link+"</a>";
			    cmsContent.setContent(linkedContent);
			}
		}
	    return create(cmsContent);
	}
	
	private String getRandomSiteLink() {
		CmsDomain domain = RandomUtil.randomSelect(cmsDomainService.findAll());
		if(domain != null ) {
			return domain.getYesterdayOuterLinked();
		}
		return null;
	}

	/** 
	 * 创建CmsContent
	 **/
	public CmsContent create(CmsContent cmsContent) {
	    Assert.notNull(cmsContent,"'cmsContent' must be not null");
	    initDefaultValuesForCreate(cmsContent);
	    new CmsContentChecker().checkCreateCmsContent(cmsContent);
	    cmsContentDao.insert(cmsContent);
	    return cmsContent;
	}
	
	/** 
	 * 更新CmsContent
	 **/	
    public CmsContent update(CmsContent cmsContent) {
        Assert.notNull(cmsContent,"'cmsContent' must be not null");
        new CmsContentChecker().checkUpdateCmsContent(cmsContent);
        cmsContentDao.update(cmsContent);
        return cmsContent;
    }	
    
	/** 
	 * 删除CmsContent
	 **/
    public void removeById(long id) {
        cmsContentDao.deleteById(id);
    }
    
	/** 
	 * 根据ID得到CmsContent
	 **/    
    public CmsContent getById(long id) {
        return cmsContentDao.getById(id);
    }
    
	/** 
	 * 分页查询: CmsContent
	 **/      
	@Transactional(readOnly=true)
	public Page<CmsContent> findPage(CmsContentQuery query) {
		Assert.notNull(query,"'query' must be not null");
//	    Assert.hasText(query.getSite(),"'query.site' must be not empty");
		return cmsContentDao.findPage(query);
	}
	
    
	/**
	 * 为创建时初始化相关默认值 
	 **/
    public void initDefaultValuesForCreate(CmsContent cmsContent) {
    }
    
    /**
     * CmsContent的属性检查类,根据自己需要编写自定义检查
     **/
    public class CmsContentChecker {
        /**可以在此检查只有更新才需要的特殊检查 */
        public void checkUpdateCmsContent(CmsContent cmsContent) {
            checkCmsContent(cmsContent);
        }
    
        /**可以在此检查只有创建才需要的特殊检查 */
        public void checkCreateCmsContent(CmsContent cmsContent) {
        	
            checkCmsContent(cmsContent);
            
            Date start = DateUtils.addDays(new Date(),-360);
            int countByTitle = cmsContentDao.countByTitle(start,new Date(),cmsContent.getTitle());
            if(countByTitle > 0) {
            	throw new IllegalStateException("already exist same title:"+cmsContent.getTitle()+" CmsContent");
            }
            int countBySearchKeyword = cmsContentDao.countBySearchKeyword(start,new Date(),cmsContent.getSearchKeyword());
            if(countBySearchKeyword > 0) {
            	throw new IllegalStateException("already exist same searchKeyword:"+cmsContent.getSearchKeyword()+" CmsContent");
            }
			Assert.isTrue(TextLangUtil.chineseCountPercent(cmsContent.getContent()) > 60,"chineseCountPercent > 60 must be true on content:"+cmsContent.getContent());

        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void checkCmsContent(CmsContent cmsContent) {
        	// Bean Validator检查,属性检查失败将抛异常
            validateWithException(cmsContent);
            int contentLength = cmsContent.getContent().length();
			Assert.isTrue(contentLength > 200,"cmsContent.title:" + cmsContent.getTitle() + " length > 200 false,current length:"+contentLength);
            
			KeywordUtil.assertSensitiveKeyword(cmsContent.getTitle());
			KeywordUtil.assertSensitiveKeyword(cmsContent.getContent());
			
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }
    }

	public synchronized void genRandomCmsContent() {
		List<CmsSite> siteList = cmsSiteService.findAll();
		for(CmsSite cmsSite : siteList) {
			if(DomainUtil.isMainSite(cmsSite.getSiteDomain())) {
				log.info("ignore genRandomCmsContent for site:"+cmsSite.getSiteDomain()+" by isMainSite,main site example:www.example.com example.com");
				continue;
			}
			for(int i = 0; i < 5; i++) {
				try {
					genSiteRandomCmsContent(cmsSite.getSiteDomain());
					break;
				}catch(Exception e) {
					log.error("error genSiteRandomCmsContent for site:"+cmsSite.getSiteDomain()+" cmsSite:"+cmsSite,e);
				}
			}
		}
	}

	public void genSiteRandomCmsContent(String site) {
		CmsSite cmsSite = cmsSiteService.getById(site);
		Assert.notNull(cmsSite,"not found cmsSite by site:"+site);
		if(StringUtils.isNotBlank(cmsSite.getRedirectSite())) {
			log.info("ignore_genSiteRandomCmsContent for site:"+site+" by StringUtils.isNotBlank(cmsSite.getRedirectSite())");
			return;
		}
		genSiteRandomCmsContent(cmsSite.getSiteDomain(),cmsSite.getCity());
	}
	
	private void genSiteRandomCmsContent(String site,String city) {
		RandomArticleBuilder builder = new RandomArticleBuilder();
		
		RandomArticle article = builder.buildRandomArticle(city);
		Assert.hasText(article.getPerfectKeyword(),"article.getPerfectKeyword() must be not emtpy,final search keyword:"+article.getFinalSearchKeyword());
		Assert.isTrue(article.getContent().length() > 500,"article.getContent().length > 500 must be true,final search keyword:"+article.getFinalSearchKeyword());
		
		CmsContent cmsContent = new CmsContent();
		cmsContent.setContent(article.getContent());
		String title = article.getPerfectKeyword();
		cmsContent.setTitle(StringUtils.trim(title)); //TODO 网站:关键字要附加进去
		cmsContent.setAuthor("ramd");
		cmsContent.setChannelCode(Constants.CHANNED_CODE_NEWS);
		cmsContent.setSite(site);
		cmsContent.setSearchKeyword(article.getKeyword());
		createWithRandomLink(cmsContent,40);
		log.info("generate_random_news by finalSearchKeyword:"+article.getFinalSearchKeyword()+",new title:"+title);
//		CmsContent.baiduBlogPing(cmsContent);
	}

//	/** 
//	 * 1. 增加文本及URL链接
//	 * 2. 打乱文章
//	 **/
//	public String buildOriginalArticle(String content,List<Anchor> anchorList) {
//		
//		StringBuilder sb = new StringBuilder(content);
//		int index = 0;
//		while(index >= 0) {
//			index = content.indexOf(",");
//			if(anchorList.isEmpty()) {
//				break;
//			}
//			Anchor anchor = anchorList.remove(0);
//		}
//		return "";
//	}
//	
	@Override
	public CmsContent getNextCmsContent(Date dateCreated,String site,long id) {
		return cmsContentDao.getNextCmsContent(dateCreated,site,id);
	}

	@Override
	public CmsContent getPreCmsContent(Date dateCreated,String site,long id) {
		return cmsContentDao.getPreCmsContent(dateCreated,site,id);
	}

	@Override
	public Page<CmsContent> findPage(PageQuery pageQuery, String site,String channelCode, DateRange createdRange) {
		return cmsContentDao.findPage(pageQuery, site,channelCode,createdRange);
	}

	@Override
	public Page findBySiteLike(PageQuery pageQuery, String site,
			String channelCode, DateRange createdRange) {
		return cmsContentDao.findBySiteLike(pageQuery, site,channelCode,createdRange);
	}
	
	@Override
	public CmsContent getById(Date dateCreated, long id) {
		return cmsContentDao.getById(dateCreated,id);
	}

	@Override
	public CmsContent findLastBySite(String site) {
		return cmsContentDao.findLastBySite(site);
	}

	public int countBySourceUrl(Date start, Date end, String sourceUrl) {
		return cmsContentDao.countBySourceUrl(start, end, sourceUrl);
	}

	@Override
	public CmsContent findFirstByCreatedDay(String site,Date createdDay) {
		return cmsContentDao.findFirstByCreatedDay(site,createdDay);
	}

	public List<Map<String, Object>> statSite(DateRange range) {
		return cmsContentDao.statSite(range);
	}

}
