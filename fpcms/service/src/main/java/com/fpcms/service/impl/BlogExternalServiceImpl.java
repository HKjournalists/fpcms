/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.service.impl;

import static com.github.rapid.common.util.holder.BeanValidatorHolder.validateWithException;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.rapid.common.util.page.Page;
import com.fpcms.common.blog_post.Blog;
import com.fpcms.common.blog_post.impl.MetaWeblogBlogPoster;
import com.fpcms.common.util.URLUtil;
import com.fpcms.dao.BlogExternalDao;
import com.fpcms.model.BlogExternal;
import com.fpcms.query.BlogExternalQuery;
import com.fpcms.service.BlogExternalService;


/**
 * [BlogExternal] 的业务操作实现类
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service("blogExternalService")
@Transactional
public class BlogExternalServiceImpl implements BlogExternalService {

	protected static final Logger log = LoggerFactory.getLogger(BlogExternalServiceImpl.class);
	
	//
	// 请删除无用的方法，代码生成器只是为你生成一个架子
	//
	
	private BlogExternalDao blogExternalDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setBlogExternalDao(BlogExternalDao dao) {
		this.blogExternalDao = dao;
	}
	
	/** 
	 * 创建BlogExternal
	 **/
	public BlogExternal create(BlogExternal blogExternal) {
	    Assert.notNull(blogExternal,"'blogExternal' must be not null");
	    initDefaultValuesForCreate(blogExternal);
	    new BlogExternalChecker().checkCreateBlogExternal(blogExternal);
	    blogExternalDao.insert(blogExternal);
	    return blogExternal;
	}
	
	/** 
	 * 更新BlogExternal
	 **/	
    public BlogExternal update(BlogExternal blogExternal) {
        Assert.notNull(blogExternal,"'blogExternal' must be not null");
        new BlogExternalChecker().checkUpdateBlogExternal(blogExternal);
        blogExternalDao.update(blogExternal);
        return blogExternal;
    }	
    
	/** 
	 * 删除BlogExternal
	 **/
    public void removeById(String blogUrl, String username) {
        blogExternalDao.deleteById(blogUrl,username);
    }
    
	/** 
	 * 根据ID得到BlogExternal
	 **/    
    public BlogExternal getById(String blogUrl, String username) {
        return blogExternalDao.getById(blogUrl,username);
    }
    
	/** 
	 * 分页查询: BlogExternal
	 **/      
	@Transactional(readOnly=true)
	public Page<BlogExternal> findPage(BlogExternalQuery query) {
	    Assert.notNull(query,"'query' must be not null");
		return blogExternalDao.findPage(query);
	}
	
    
	/**
	 * 为创建时初始化相关默认值 
	 **/
    public void initDefaultValuesForCreate(BlogExternal blogExternal) {
    }
    
    /**
     * BlogExternal的属性检查类,根据自己需要编写自定义检查
     **/
    public static class BlogExternalChecker {
        /**可以在此检查只有更新才需要的特殊检查 */
        public void checkUpdateBlogExternal(BlogExternal blogExternal) {
            checkBlogExternal(blogExternal);
        }
    
        /**可以在此检查只有创建才需要的特殊检查 */
        public void checkCreateBlogExternal(BlogExternal blogExternal) {
            checkBlogExternal(blogExternal);
        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void checkBlogExternal(BlogExternal blogExternal) {
        	// Bean Validator检查,属性检查失败将抛异常
            validateWithException(blogExternal);
            
            URLUtil.assertURL(blogExternal.getBlogUrl(),"blogUrl is error");
            URLUtil.assertURL(blogExternal.getBlogRpcUrl(),"blogRpcUrl is error");
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }

    }

	private MetaWeblogBlogPoster newMetaWeblogBlogPoster(BlogExternal be) {
		Assert.notNull(be,"BlogExternal must be not null");
		MetaWeblogBlogPoster bp = new MetaWeblogBlogPoster();
		bp.setUsername(be.getUsername());
		bp.setPassword(be.getPassword());
		bp.setBlogUrl(be.getBlogUrl());
		
		if(StringUtils.isNotBlank(be.getCategories())) {
			bp.setCategories(be.getCategories().split(","));
		}
		
		return bp;
	}

	@Override
	public List<BlogExternal> findAll() {
		return blogExternalDao.findAll();
	}

	@Override
	public void postNewBlog(BlogExternal be, Blog blog) {
		MetaWeblogBlogPoster blogPoster = newMetaWeblogBlogPoster(be);
		blogPoster.postBlog(blog);
		
		be.setBlogPostCount(be.getBlogPostCount() + 1);
		update(be);
	}
	
	
}
