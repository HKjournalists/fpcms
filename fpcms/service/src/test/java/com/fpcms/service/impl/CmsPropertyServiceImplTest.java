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

import com.fpcms.CmsPropertyDataFactory;
import com.fpcms.common.base.BaseServiceTestCase;
import com.fpcms.dao.CmsPropertyDao;
import com.fpcms.model.CmsProperty;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsPropertyServiceImplTest extends BaseServiceTestCase{

	//mock框架使用Mockito 具体使用请查看: http://code.google.com/p/mockito/wiki/MockitoVSEasyMock
	
	private CmsPropertyServiceImpl service = new CmsPropertyServiceImpl();
	private CmsPropertyDao cmsPropertyDao = mock(CmsPropertyDao.class);
	
	@Before
	public void setUp() {
		service.setCmsPropertyDao(cmsPropertyDao);
	}
	
	@Test
	public void test_create() {
		CmsProperty obj = CmsPropertyDataFactory.newCmsProperty();
		service.create(obj);
		
		verify(cmsPropertyDao).insert(obj); //验证执行了该语句
	}
	
	@Test
	public void test_update() {
		CmsProperty obj = CmsPropertyDataFactory.newCmsProperty();
		service.update(obj);
		
		verify(cmsPropertyDao).update(obj); //验证执行了该语句
	}
	
	@Test
	public void test_removeById() {
		service.removeById(new java.lang.String("1"),new java.lang.String("1"));
		
		verify(cmsPropertyDao).deleteById(new java.lang.String("1"),new java.lang.String("1")); //验证执行了该语句
	}
	
	@Test
	public void test_getById() {
		when(cmsPropertyDao.getById(new java.lang.String("1"),new java.lang.String("1"))).thenReturn(CmsPropertyDataFactory.newCmsProperty()); // mock方法调用
		
		CmsProperty cmsProperty = service.getById(new java.lang.String("1"),new java.lang.String("1"));
		
		verify(cmsPropertyDao).getById(new java.lang.String("1"),new java.lang.String("1")); //验证执行了该语句
		assertNotNull(cmsProperty);
	}
	
	
}

