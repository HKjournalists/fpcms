/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */


package com.fpcms.service.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fpcms.CmsSiteDataFactory;
import com.fpcms.common.base.BaseServiceTestCase;
import com.fpcms.common.util.KeywordUtil;
import com.fpcms.dao.CmsSiteDao;
import com.fpcms.model.CmsSite;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsSiteServiceImplTest extends BaseServiceTestCase{

	//mock框架使用Mockito 具体使用请查看: http://code.google.com/p/mockito/wiki/MockitoVSEasyMock
	
	private CmsSiteServiceImpl service = new CmsSiteServiceImpl();
	private CmsSiteDao cmsSiteDao = mock(CmsSiteDao.class);
	
	@Before
	public void setUp() {
		service.setCmsSiteDao(cmsSiteDao);
	}
	
	@Test
	public void test_create() {
		CmsSite obj = CmsSiteDataFactory.newCmsSite();
		service.create(obj);
		
		verify(cmsSiteDao).insert(obj); //验证执行了该语句
	}
	
	@Test
	public void test_update() {
		CmsSite obj = CmsSiteDataFactory.newCmsSite();
		service.update(obj);
		
		verify(cmsSiteDao).update(obj); //验证执行了该语句
	}
	
	@Test
	public void test_removeById() {
		service.removeById(new java.lang.String("1"));
		
		verify(cmsSiteDao).deleteById(new java.lang.String("1")); //验证执行了该语句
	}
	
	@Test
	public void test_getById() {
		when(cmsSiteDao.getById(new java.lang.String("1"))).thenReturn(CmsSiteDataFactory.newCmsSite()); // mock方法调用
		
		CmsSite cmsSite = service.getById(new java.lang.String("1"));
		
		verify(cmsSiteDao).getById(new java.lang.String("1")); //验证执行了该语句
		assertNotNull(cmsSite);
	}
	
	@Test
	public void test_updateSearchEngineKeywordMaxRank() {
		List<CmsSite> returnList = getTestCmsSiteList();
		when(cmsSiteDao.findAll()).thenReturn(returnList); // mock方法调用
		
		List<CmsSite> list = service.updateSearchEngineKeywordMaxRank();
		assertEquals(list.get(0).getRankBaidu(),1);
		
		verify(cmsSiteDao).findAll(); //验证执行了该语句
		verify(cmsSiteDao,atLeast(2)).update(any(CmsSite.class)); //验证执行了该语句
	}

	private List<CmsSite> getTestCmsSiteList() {
		List<CmsSite> returnList = new ArrayList();
		CmsSite cmsSite = CmsSiteDataFactory.newCmsSite();
		cmsSite.setSiteDomain("news.163.com");
		cmsSite.setKeyword("网易新闻");
		cmsSite.setRecordBaidu(20);
		cmsSite.setRankBaidu(20);
		returnList.add(cmsSite);
		
		cmsSite = CmsSiteDataFactory.newCmsSite();
		cmsSite.setSiteDomain("news.qq.com");
		cmsSite.setKeyword("腾讯新闻");
		cmsSite.setRecordBaidu(10);
		cmsSite.setRankBaidu(10);
		returnList.add(cmsSite);
		
		return returnList;
	}
	
	@Test
	public void test_updateSearchEngineRecord() {
		List<CmsSite> returnList = getTestCmsSiteList();
		when(cmsSiteDao.findAll()).thenReturn(returnList); // mock方法调用
		
		List<CmsSite> list = service.updateSearchEngineRecord();
		assertTrue(list.get(0).getRecordBaidu()> 100000);
		
		verify(cmsSiteDao).findAll(); //验证执行了该语句
		verify(cmsSiteDao,atLeast(2)).update(any(CmsSite.class)); //验证执行了该语句
	}
	
	
}

