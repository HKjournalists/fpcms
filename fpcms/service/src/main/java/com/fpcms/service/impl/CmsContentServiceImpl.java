/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service.impl;

import static com.duowan.common.util.holder.BeanValidatorHolder.validateWithException;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.duowan.common.util.page.Page;
import com.fpcms.common.random_gen_article.RandomArticle;
import com.fpcms.common.random_gen_article.RandomArticleBuilder;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.dao.CmsContentDao;
import com.fpcms.model.CmsContent;
import com.fpcms.query.CmsContentQuery;
import com.fpcms.service.CmsContentService;
import com.fpcms.service.CmsPropertyService;


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
        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void checkCmsContent(CmsContent cmsContent) {
        	// Bean Validator检查,属性检查失败将抛异常
            validateWithException(cmsContent);
            
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }
    }

	@Override
	public List<CmsContent> findByChannelCode(String channelCode) {
		return cmsContentDao.findByChannelCode(channelCode);
	}

	public void genRandomCmsContent() {
		RandomArticleBuilder builder = new RandomArticleBuilder();
		RandomArticle article = builder.buildRandomArticle(null);
		
		String keyword = getAttachKeyword();
		CmsContent cmsContent = new CmsContent();
		cmsContent.setContent(article.getContent());
		String title = keyword + article.getPerfectKeyword();
		cmsContent.setTitle(title); //TODO 网站:关键字要附加进去
		cmsContent.setAuthor("admin_ramd");
		cmsContent.setChannelCode(Constants.CHANNED_CODE_NEWS);
		create(cmsContent);
		log.info("generate random news by finalSearchKeyword:"+article.getFinalSearchKeyword()+",new title:"+title);
	}
	
	
	private String getAttachKeyword() {
		if(RandomUtil.randomTrue(25)) {
			return "";
		}
		return RandomUtil.randomSelect(Constants.ATTACH_KEYWORD);
	}


}
