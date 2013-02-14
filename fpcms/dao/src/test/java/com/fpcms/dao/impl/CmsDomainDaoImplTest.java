/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.dao.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import com.duowan.common.util.page.Page;
import com.fpcms.CmsDomainDataFactory;
import com.fpcms.common.base.BaseDaoTestCase;
import com.fpcms.dao.CmsDomainDao;
import com.fpcms.query.CmsDomainQuery;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsDomainDaoImplTest extends BaseDaoTestCase{
	
	@Rule public TestName testName = new TestName();
	
	private CmsDomainDao dao;
	
	@Autowired
	public void setCmsDomainDao(CmsDomainDao dao) {
		this.dao = dao;
	}

	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		CmsDomainQuery query = CmsDomainDataFactory.newCmsDomainQuery();
		Page page = dao.findPage(query);
		
		assertEquals(1,page.getPaginator().getPage());
		assertEquals(10,page.getPaginator().getPageSize());
		List resultList = (List)page.getItemList();
		assertNotNull(resultList);
		
	}
	
	@Test
	public void test_insert() {
		dao.insert(CmsDomainDataFactory.newCmsDomain());
	}
	
	@Test
	public void test_update() {
		dao.update(CmsDomainDataFactory.newCmsDomain());
	}
	
	@Test
	public void test_delete() {
		dao.deleteById(new java.lang.String("1"));
	}
	
	@Test
	public void test_getById() {
		dao.getById(new java.lang.String("1"));
	}
	
	@Test
	public void test_getByRemarks() {
		dao.getByRemarks(new java.lang.String("1"));
	}
	
	@Test
	public void test_findAll() {
		dao.findAll();
	}
}

