/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.dao.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import com.duowan.common.util.page.Page;
import com.fpcms.CmsKeyValueDataFactory;
import com.fpcms.common.base.BaseDaoTestCase;
import com.fpcms.dao.CmsKeyValueDao;
import com.fpcms.query.CmsKeyValueQuery;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsKeyValueDaoImplTest extends BaseDaoTestCase{
	
	@Rule public TestName testName = new TestName();
	
	private CmsKeyValueDao dao;
	
	@Autowired
	public void setCmsKeyValueDao(CmsKeyValueDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过testName.getMethodName() 可以得到当前正在运行的测试方法名称
//		return new String[]{"classpath:testdata/common.xml","classpath:testdata/CmsKeyValue.xml",
//		                    "classpath:testdata/CmsKeyValue_"+testName.getMethodName()+".xml"};
		return null;
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		CmsKeyValueQuery query = CmsKeyValueDataFactory.newCmsKeyValueQuery();
		Page page = dao.findPage(query);
		
		assertEquals(1,page.getPaginator().getPage());
		assertEquals(10,page.getPaginator().getPageSize());
		List resultList = (List)page.getItemList();
		assertNotNull(resultList);
		
	}
	
	@Test
	public void test_insert() {
		dao.insert(CmsKeyValueDataFactory.newCmsKeyValue());
	}
	
	@Test
	public void test_update() {
		dao.update(CmsKeyValueDataFactory.newCmsKeyValue());
	}
	
	@Test
	public void test_delete() {
		dao.deleteById(new java.lang.String("1"),new java.lang.String("1"));
		dao.deleteBy(DateUtils.addDays(new Date(),3650));
	}
	
	@Test
	public void test_getById() {
		dao.getById(new java.lang.String("1"),new java.lang.String("1"));
	}
	
	
}

