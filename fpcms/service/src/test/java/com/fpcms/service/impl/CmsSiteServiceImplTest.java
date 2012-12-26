/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */


package com.fpcms.service.impl;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.fpcms.CmsSiteDataFactory;
import com.fpcms.common.base.BaseServiceTestCase;
import com.fpcms.dao.CmsSiteDao;
import com.fpcms.model.CmsSite;
import com.fpcms.service.impl.CmsSiteServiceImpl;


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
	
	
}

