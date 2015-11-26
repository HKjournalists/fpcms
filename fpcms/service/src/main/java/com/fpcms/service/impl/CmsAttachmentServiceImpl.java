/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service.impl;

import static com.github.rapid.common.util.holder.BeanValidatorHolder.validateWithException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.rapid.common.util.page.Page;
import com.fpcms.dao.CmsAttachmentDao;
import com.fpcms.model.CmsAttachment;
import com.fpcms.query.CmsAttachmentQuery;
import com.fpcms.service.CmsAttachmentService;


/**
 * [CmsAttachment] 的业务操作实现类
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service("cmsAttachmentService")
@Transactional
public class CmsAttachmentServiceImpl implements CmsAttachmentService {

	protected static final Logger log = LoggerFactory.getLogger(CmsAttachmentServiceImpl.class);
	
	//
	// 请删除无用的方法，代码生成器只是为你生成一个架子
	//
	
	private CmsAttachmentDao cmsAttachmentDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setCmsAttachmentDao(CmsAttachmentDao dao) {
		this.cmsAttachmentDao = dao;
	}
	
	/** 
	 * 创建CmsAttachment
	 **/
	public CmsAttachment create(CmsAttachment cmsAttachment) {
	    Assert.notNull(cmsAttachment,"'cmsAttachment' must be not null");
	    initDefaultValuesForCreate(cmsAttachment);
	    new CmsAttachmentChecker().checkCreateCmsAttachment(cmsAttachment);
	    cmsAttachmentDao.insert(cmsAttachment);
	    return cmsAttachment;
	}
	
	/** 
	 * 更新CmsAttachment
	 **/	
    public CmsAttachment update(CmsAttachment cmsAttachment) {
        Assert.notNull(cmsAttachment,"'cmsAttachment' must be not null");
        new CmsAttachmentChecker().checkUpdateCmsAttachment(cmsAttachment);
        cmsAttachmentDao.update(cmsAttachment);
        return cmsAttachment;
    }	
    
	/** 
	 * 删除CmsAttachment
	 **/
    public void removeById(long id) {
        cmsAttachmentDao.deleteById(id);
    }
    
	/** 
	 * 根据ID得到CmsAttachment
	 **/    
    public CmsAttachment getById(long id) {
        return cmsAttachmentDao.getById(id);
    }
    
	/** 
	 * 分页查询: CmsAttachment
	 **/      
	@Transactional(readOnly=true)
	public Page<CmsAttachment> findPage(CmsAttachmentQuery query) {
	    Assert.notNull(query,"'query' must be not null");
		return cmsAttachmentDao.findPage(query);
	}
	
    
	/**
	 * 为创建时初始化相关默认值 
	 **/
    public void initDefaultValuesForCreate(CmsAttachment cmsAttachment) {
    }
    
    /**
     * CmsAttachment的属性检查类,根据自己需要编写自定义检查
     **/
    public class CmsAttachmentChecker {
        /**可以在此检查只有更新才需要的特殊检查 */
        public void checkUpdateCmsAttachment(CmsAttachment cmsAttachment) {
            checkCmsAttachment(cmsAttachment);
        }
    
        /**可以在此检查只有创建才需要的特殊检查 */
        public void checkCreateCmsAttachment(CmsAttachment cmsAttachment) {
            checkCmsAttachment(cmsAttachment);
        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void checkCmsAttachment(CmsAttachment cmsAttachment) {
        	// Bean Validator检查,属性检查失败将抛异常
            validateWithException(cmsAttachment);
            
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }
    }
}
