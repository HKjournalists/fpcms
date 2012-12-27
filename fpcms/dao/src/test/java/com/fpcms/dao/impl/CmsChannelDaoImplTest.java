/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import com.duowan.common.util.page.Page;
import com.fpcms.CmsChannelDataFactory;
import com.fpcms.common.base.BaseDaoTestCase;
import com.fpcms.common.util.Constants;
import com.fpcms.dao.CmsChannelDao;
import com.fpcms.model.CmsChannel;
import com.fpcms.query.CmsChannelQuery;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsChannelDaoImplTest extends BaseDaoTestCase{
	
	@Rule public TestName testName = new TestName();
	
	private CmsChannelDao dao;
	
	@Autowired
	public void setCmsChannelDao(CmsChannelDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过testName.getMethodName() 可以得到当前正在运行的测试方法名称
//		return new String[]{"classpath:testdata/common.xml","classpath:testdata/CmsChannel.xml",
//		                    "classpath:testdata/CmsChannel_"+testName.getMethodName()+".xml"};
		return null;
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		CmsChannelQuery query = CmsChannelDataFactory.newCmsChannelQuery();
		Page page = dao.findPage(query);
		
		assertEquals(1,page.getPaginator().getPage());
		assertEquals(10,page.getPaginator().getPageSize());
		List resultList = (List)page.getItemList();
		assertNotNull(resultList);
		
	}
	
	@Test
	public void test_insert() {
		CmsChannel channel = CmsChannelDataFactory.newCmsChannel();
		channel.setId(System.currentTimeMillis());
		dao.insert(channel);
	}
	
	@Test
	public void test_update() {
		dao.update(CmsChannelDataFactory.newCmsChannel());
	}
	
	@Test
	public void test_delete() {
		dao.deleteById("localhost",new java.lang.Long("1"));
	}
	
	@Test
	public void test_getById() {
		dao.getById("localhost",new java.lang.Long("1"));
	}
	
	@Test
	public void test_findChildsByChannelId() {
		assertFalse(dao.findChildsByChannelId("localhost",1).isEmpty());
	}
	
	@Test
	public void test_countBySite() {
		dao.countBySite("localhost");
	}
	
	@Test
	public void test_findBySite_and_parentChannelId() {
		assertNotNull(dao.findBySite("localhost", Constants.TREE_ROOT_PARENT_ID));
	}
}

