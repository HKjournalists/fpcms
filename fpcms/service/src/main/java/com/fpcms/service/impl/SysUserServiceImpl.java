/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service.impl;

import static com.duowan.common.util.holder.BeanValidatorHolder.validateWithException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.duowan.common.util.page.Page;
import com.fpcms.dao.SysUserDao;
import com.fpcms.model.SysUser;
import com.fpcms.query.SysUserQuery;
import com.fpcms.service.SysUserService;


/**
 * [SysUser] 的业务操作实现类
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service("sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService {

	protected static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
	
	//
	// 请删除无用的方法，代码生成器只是为你生成一个架子
	//
	
	private SysUserDao sysUserDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setSysUserDao(SysUserDao dao) {
		this.sysUserDao = dao;
	}
	
	/** 
	 * 创建SysUser
	 **/
	public SysUser create(SysUser sysUser) {
	    Assert.notNull(sysUser,"'sysUser' must be not null");
	    initDefaultValuesForCreate(sysUser);
	    new SysUserChecker().checkCreateSysUser(sysUser);
	    sysUserDao.insert(sysUser);
	    return sysUser;
	}
	
	/** 
	 * 更新SysUser
	 **/	
    public SysUser update(SysUser sysUser) {
        Assert.notNull(sysUser,"'sysUser' must be not null");
        new SysUserChecker().checkUpdateSysUser(sysUser);
        sysUserDao.update(sysUser);
        return sysUser;
    }	
    
	/** 
	 * 删除SysUser
	 **/
    public void removeById(long id) {
        sysUserDao.deleteById(id);
    }
    
	/** 
	 * 根据ID得到SysUser
	 **/    
    public SysUser getById(long id) {
        return sysUserDao.getById(id);
    }
    
	/** 
	 * 分页查询: SysUser
	 **/      
	@Transactional(readOnly=true)
	public Page<SysUser> findPage(SysUserQuery query) {
	    Assert.notNull(query,"'query' must be not null");
		return sysUserDao.findPage(query);
	}
	
    
	/**
	 * 为创建时初始化相关默认值 
	 **/
    public void initDefaultValuesForCreate(SysUser sysUser) {
    }
    
    /**
     * SysUser的属性检查类,根据自己需要编写自定义检查
     **/
    public class SysUserChecker {
        /**可以在此检查只有更新才需要的特殊检查 */
        public void checkUpdateSysUser(SysUser sysUser) {
            checkSysUser(sysUser);
        }
    
        /**可以在此检查只有创建才需要的特殊检查 */
        public void checkCreateSysUser(SysUser sysUser) {
            checkSysUser(sysUser);
        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void checkSysUser(SysUser sysUser) {
        	// Bean Validator检查,属性检查失败将抛异常
            validateWithException(sysUser);
            
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }
    }
}
