/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */


package com.fpcms.service.impl;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.fpcms.CmsDomainDataFactory;
import com.fpcms.common.base.BaseServiceTestCase;
import com.fpcms.dao.CmsDomainDao;
import com.fpcms.model.CmsDomain;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsDomainServiceImplTest extends BaseServiceTestCase{

	//mock框架使用Mockito 具体使用请查看: http://code.google.com/p/mockito/wiki/MockitoVSEasyMock
	
	private CmsDomainServiceImpl service = new CmsDomainServiceImpl();
	private CmsDomainDao cmsDomainDao = mock(CmsDomainDao.class);
	
	@Before
	public void setUp() {
		service.setCmsDomainDao(cmsDomainDao);
	}
	
	@Test
	public void test_create() {
		CmsDomain obj = CmsDomainDataFactory.newCmsDomain();
		service.create(obj);
		
		verify(cmsDomainDao).insert(obj); //验证执行了该语句
	}
	
	@Test
	public void test_update() {
		CmsDomain obj = CmsDomainDataFactory.newCmsDomain();
		service.update(obj);
		
		verify(cmsDomainDao).update(obj); //验证执行了该语句
	}
	
	@Test
	public void test_removeById() {
		service.removeById(new java.lang.String("1"));
		
		verify(cmsDomainDao).deleteById(new java.lang.String("1")); //验证执行了该语句
	}
	
	@Test
	public void test_getById() {
		when(cmsDomainDao.getById(new java.lang.String("1"))).thenReturn(CmsDomainDataFactory.newCmsDomain()); // mock方法调用
		
		CmsDomain cmsDomain = service.getById(new java.lang.String("1"));
		
		verify(cmsDomainDao).getById(new java.lang.String("1")); //验证执行了该语句
		assertNotNull(cmsDomain);
	}
	
	
}

