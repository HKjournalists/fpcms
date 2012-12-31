/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import com.duowan.common.util.page.Page;
import com.fpcms.CmsContentDataFactory;
import com.fpcms.common.base.BaseDaoTestCase;
import com.fpcms.dao.CmsContentDao;
import com.fpcms.model.CmsContent;
import com.fpcms.query.CmsContentQuery;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsContentDaoImplTest extends BaseDaoTestCase{
	
	@Rule public TestName testName = new TestName();
	
	private CmsContentDao dao;
	
	@Autowired
	public void setCmsContentDao(CmsContentDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过testName.getMethodName() 可以得到当前正在运行的测试方法名称
//		return new String[]{"classpath:testdata/common.xml","classpath:testdata/CmsContent.xml",
//		                    "classpath:testdata/CmsContent_"+testName.getMethodName()+".xml"};
		return null;
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		CmsContentQuery query = CmsContentDataFactory.newCmsContentQuery();
		Page page = dao.findPage(query);
		
		assertEquals(1,page.getPaginator().getPage());
		assertEquals(10,page.getPaginator().getPageSize());
		List resultList = (List)page.getItemList();
		assertNotNull(resultList);
		
	}
	
	@Test
	public void test_insert() {
		dao.insert(CmsContentDataFactory.newCmsContent());
	}
	
	@Test
	public void test_update() {
		dao.update(CmsContentDataFactory.newCmsContent());
	}
	
	@Test
	public void test_delete() {
		dao.deleteById(new java.lang.Long("1"));
	}
	
	@Test
	public void test_getById() {
		dao.getById(new java.lang.Long("1"));
	}
	
	@Test
	public void test_findByChannelCode() {
		assertNotNull(dao.findByChannelCode("localhost","news"));
	}
	
	@Test
	public void test_getNextCmsContent() {
		CmsContent nextCmsContent = dao.getNextCmsContent("localhost", 150);
		assertNotNull(nextCmsContent);
		assertTrue(nextCmsContent.getId() > 150);
	}
	
	@Test
	public void test_getPreCmsContent() {
		CmsContent preCmsContent = dao.getPreCmsContent("localhost", 150);
		assertNotNull(preCmsContent);
		assertTrue(preCmsContent.getId() < 150);
	}
	
}

