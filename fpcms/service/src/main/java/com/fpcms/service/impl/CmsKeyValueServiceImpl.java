/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.service.impl;

import static com.github.rapid.common.util.holder.BeanValidatorHolder.validateWithException;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.rapid.common.util.page.Page;
import com.fpcms.dao.CmsKeyValueDao;
import com.fpcms.model.CmsKeyValue;
import com.fpcms.query.CmsKeyValueQuery;
import com.fpcms.service.CmsKeyValueService;


/**
 * [CmsKeyValue] 的业务操作实现类
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service("cmsKeyValueService")
@Transactional
public class CmsKeyValueServiceImpl implements CmsKeyValueService {

	protected static final Logger log = LoggerFactory.getLogger(CmsKeyValueServiceImpl.class);
	
	//
	// 请删除无用的方法，代码生成器只是为你生成一个架子
	//
	
	private CmsKeyValueDao cmsKeyValueDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setCmsKeyValueDao(CmsKeyValueDao dao) {
		this.cmsKeyValueDao = dao;
	}
	
	/** 
	 * 创建CmsKeyValue
	 **/
	public CmsKeyValue create(CmsKeyValue cmsKeyValue) {
	    Assert.notNull(cmsKeyValue,"'cmsKeyValue' must be not null");
	    initDefaultValuesForCreate(cmsKeyValue);
	    new CmsKeyValueChecker().checkCreateCmsKeyValue(cmsKeyValue);
	    cmsKeyValueDao.insert(cmsKeyValue);
	    return cmsKeyValue;
	}
	
	/** 
	 * 更新CmsKeyValue
	 **/	
    public CmsKeyValue update(CmsKeyValue cmsKeyValue) {
        Assert.notNull(cmsKeyValue,"'cmsKeyValue' must be not null");
        new CmsKeyValueChecker().checkUpdateCmsKeyValue(cmsKeyValue);
        cmsKeyValueDao.update(cmsKeyValue);
        return cmsKeyValue;
    }	
    
	/** 
	 * 删除CmsKeyValue
	 **/
    public void removeById(String keyGroup, String key) {
        cmsKeyValueDao.deleteById(keyGroup,key);
    }
    
	/** 
	 * 根据ID得到CmsKeyValue
	 **/    
    public CmsKeyValue getById(String keyGroup, String key) {
        return cmsKeyValueDao.getById(keyGroup,key);
    }
    
	/** 
	 * 分页查询: CmsKeyValue
	 **/      
	@Transactional(readOnly=true)
	public Page<CmsKeyValue> findPage(CmsKeyValueQuery query) {
	    Assert.notNull(query,"'query' must be not null");
		return cmsKeyValueDao.findPage(query);
	}
	
    
	/**
	 * 为创建时初始化相关默认值 
	 **/
    public void initDefaultValuesForCreate(CmsKeyValue cmsKeyValue) {
    	cmsKeyValue.setDateCreated(new Date());
    }
    
    /**
     * CmsKeyValue的属性检查类,根据自己需要编写自定义检查
     **/
    public class CmsKeyValueChecker {
        /**可以在此检查只有更新才需要的特殊检查 */
        public void checkUpdateCmsKeyValue(CmsKeyValue cmsKeyValue) {
            checkCmsKeyValue(cmsKeyValue);
        }
    
        /**可以在此检查只有创建才需要的特殊检查 */
        public void checkCreateCmsKeyValue(CmsKeyValue cmsKeyValue) {
            checkCmsKeyValue(cmsKeyValue);
        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void checkCmsKeyValue(CmsKeyValue cmsKeyValue) {
        	// Bean Validator检查,属性检查失败将抛异常
            validateWithException(cmsKeyValue);
            
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }
    }

	@Override
	public boolean exist(CmsKeyValue cmsKeyValue) {
		CmsKeyValue result =  cmsKeyValueDao.getById(cmsKeyValue.getKeyGroup(), cmsKeyValue.getStrKey());
		if(result == null) {
			return false;
		}
		return true;
	}

	@Override
	public int deleteBy(Date beforeDateCreated) {
		return cmsKeyValueDao.deleteBy(beforeDateCreated);
	}
	
}
