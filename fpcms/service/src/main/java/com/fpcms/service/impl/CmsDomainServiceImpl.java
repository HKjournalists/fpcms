/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.service.impl;

import static com.duowan.common.util.holder.BeanValidatorHolder.validateWithException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.duowan.common.util.page.Page;
import com.fpcms.common.util.IpUtil;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.common.util.StringHelper;
import com.fpcms.dao.CmsDomainDao;
import com.fpcms.model.CmsDomain;
import com.fpcms.query.CmsDomainQuery;
import com.fpcms.service.CmsDomainService;


/**
 * [CmsDomain] 的业务操作实现类
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
@Service("cmsDomainService")
@Transactional
public class CmsDomainServiceImpl implements CmsDomainService {

	protected static final Logger log = LoggerFactory.getLogger(CmsDomainServiceImpl.class);
	
	//
	// 请删除无用的方法，代码生成器只是为你生成一个架子
	//
	
	private CmsDomainDao cmsDomainDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setCmsDomainDao(CmsDomainDao dao) {
		this.cmsDomainDao = dao;
	}
	
	/** 
	 * 创建CmsDomain
	 **/
	public CmsDomain create(CmsDomain cmsDomain) {
	    Assert.notNull(cmsDomain,"'cmsDomain' must be not null");
	    initDefaultValuesForCreate(cmsDomain);
	    new CmsDomainChecker().checkCreateCmsDomain(cmsDomain);
	    cmsDomainDao.insert(cmsDomain);
	    return cmsDomain;
	}
	
	/** 
	 * 更新CmsDomain
	 **/	
    public CmsDomain update(CmsDomain cmsDomain) {
        Assert.notNull(cmsDomain,"'cmsDomain' must be not null");
        new CmsDomainChecker().checkUpdateCmsDomain(cmsDomain);
        cmsDomain.setIp(IpUtil.getIp(cmsDomain.getDomain()));
        cmsDomainDao.update(cmsDomain);
        return cmsDomain;
    }	
    
	/** 
	 * 删除CmsDomain
	 **/
    public void removeById(String domain) {
        cmsDomainDao.deleteById(domain);
    }
    
	/** 
	 * 根据ID得到CmsDomain
	 **/    
    public CmsDomain getById(String domain) {
        return cmsDomainDao.getById(domain);
    }
    
	/** 
	 * 分页查询: CmsDomain
	 **/      
	@Transactional(readOnly=true)
	public Page<CmsDomain> findPage(CmsDomainQuery query) {
	    Assert.notNull(query,"'query' must be not null");
		return cmsDomainDao.findPage(query);
	}
	
	@Transactional(readOnly=true)
	public CmsDomain getByRemarks(String remarks) {
		return cmsDomainDao.getByRemarks(remarks);
	}	
	
    
	/**
	 * 为创建时初始化相关默认值 
	 **/
    public void initDefaultValuesForCreate(CmsDomain cmsDomain) {
    	cmsDomain.setIp(IpUtil.getIp(cmsDomain.getDomain()));
    }
    
    /**
     * CmsDomain的属性检查类,根据自己需要编写自定义检查
     **/
    public class CmsDomainChecker {
        /**可以在此检查只有更新才需要的特殊检查 */
        public void checkUpdateCmsDomain(CmsDomain cmsDomain) {
            checkCmsDomain(cmsDomain);
        }
    
        /**可以在此检查只有创建才需要的特殊检查 */
        public void checkCreateCmsDomain(CmsDomain cmsDomain) {
            checkCmsDomain(cmsDomain);
        }
        
        /** 检查到有错误请直接抛异常，不要使用 return errorCode的方式 */
        public void checkCmsDomain(CmsDomain cmsDomain) {
        	// Bean Validator检查,属性检查失败将抛异常
            validateWithException(cmsDomain);
            
        	//复杂的属性的检查一般需要分开写几个方法，如 checkProperty1(v),checkProperty2(v)
        }
    }

	@Override
	public List<CmsDomain> findAll() {
		return cmsDomainDao.findAll();
	}
	
	public void updateCmsDomainStatus() {
		for(CmsDomain domain : findAll()) {
			domain.getHttpStatus();
			update(domain);
		}
	}

	@Override
	public CmsDomain randomSelectDomain() {
		return RandomUtil.randomSelect(findAll());
	}

	@Override
	public String insertRandomLinks(String article,int randomLinkCount) {
		StringBuilder result = new StringBuilder(article);
		int fromIndex = 0;
		for(int i = 0 ; i < randomLinkCount; i++) {
			int index = StringHelper.indexOf(result,fromIndex,"。",".");
			if(index >= 0) {
				String link = randomDomainLink();
				fromIndex = index + 1 + link.length();
				result.insert(index + 1, link);
			}
		}
		return result.toString();
	}

	private String randomDomainLink() {
		CmsDomain domain = randomSelectDomain();
		Assert.notNull(domain);
		String link = domain.getYesterdayOuterLinked();
		return link;
	}
	
}
