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
import com.fpcms.dao.CmsPropertyDao;
import com.fpcms.model.CmsProperty;
import com.fpcms.query.CmsPropertyQuery;
import com.fpcms.service.CmsPropertyService;


/**
 * [CmsProperty] 的业务操作实现类
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service("cmsPropertyService")
@Transactional
public class CmsPropertyServiceImpl implements CmsPropertyService {

	protected static final Logger log = LoggerFactory.getLogger(CmsPropertyServiceImpl.class);
	
	//
	// 请删除无用的方法，代码生成器只是为你生成一个架子
	//
	
	private CmsPropertyDao cmsPropertyDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setCmsPropertyDao(CmsPropertyDao dao) {
		this.cmsPropertyDao = dao;
	}
	
	/** 
	 * 创建CmsProperty
	 **/
	public CmsProperty create(CmsProperty cmsProperty) {
	    Assert.notNull(cmsProperty,"'cmsProperty' must be not null");
	    initDefaultValuesForCreate(cmsProperty);
	    new CmsPropertyChecker().checkCreateCmsProperty(cmsProperty);
	    cmsPropertyDao.insert(cmsProperty);
	    return cmsProperty;
	}
	
	/** 
	 * 更新CmsProperty
	 **/	
    public CmsProperty update(CmsProperty cmsProperty) {
        Assert.notNull(cmsProperty,"'cmsProperty' must be not null");
        new CmsPropertyChecker().checkUpdateCmsProperty(cmsProperty);
        cmsPropertyDao.update(cmsProperty);
        return cmsProperty;
    }	
    
	/** 
	 * 删除CmsProperty
	 **/
    public void removeById(String propGroup, String propKey) {
        cmsPropertyDao.deleteById(propGroup,propKey);
    }
    
	/** 
	 * 根据ID得到CmsProperty
	 **/    
    public CmsProperty getById(String propGroup, String propKey) {
        return cmsPropertyDao.getById(propGroup,propKey);
    }
    
	/** 
	 * 分页查询: CmsProperty
	 **/      
	@Transactional(readOnly=true)
	public Page<CmsProperty> findPage(CmsPropertyQuery query) {
	    Assert.notNull(query,"'query' must be not null");
		return cmsPropertyDao.findPage(query);
	}
	
    
	/**
	 * 为创建时初始化相关默认值 
	 **/
    public void initDefaultValuesForCreate(CmsProperty cmsProperty) {
    }
    
    /**
     * CmsProperty的属性检查类,根据自己需要编写自定义检查
     **/
    public class CmsPropertyChecker {
        /**可以在此检查只有更新才需要的特殊检查 */
        public void checkUpdateCmsProperty(CmsProperty cmsProperty) {
            checkCmsProperty(cmsProperty);
        }
    
        /**可以在此检查只有创建才需要的特殊检查 */
        public void checkCreateCmsProperty(CmsProperty cmsProperty) {
            checkCmsProperty(cmsProperty);
        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void checkCmsProperty(CmsProperty cmsProperty) {
        	// Bean Validator检查,属性检查失败将抛异常
            validateWithException(cmsProperty);
            
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }
    }


	@Override
	public Map<String, String> findByGroup(String group) {
		List<CmsProperty> list = cmsPropertyDao.findByGroup(group);
		Map<String,String> map = new HashMap<String,String>();
		for(CmsProperty p : list) {
			map.put(p.getPropKey(), p.getPropValue());
		}
		return map;
	}
}
