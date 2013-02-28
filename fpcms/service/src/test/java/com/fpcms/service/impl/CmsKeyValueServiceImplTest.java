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

import com.fpcms.CmsKeyValueDataFactory;
import com.fpcms.common.base.BaseServiceTestCase;
import com.fpcms.dao.CmsKeyValueDao;
import com.fpcms.model.CmsKeyValue;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsKeyValueServiceImplTest extends BaseServiceTestCase{

	//mock框架使用Mockito 具体使用请查看: http://code.google.com/p/mockito/wiki/MockitoVSEasyMock
	
	private CmsKeyValueServiceImpl service = new CmsKeyValueServiceImpl();
	private CmsKeyValueDao cmsKeyValueDao = mock(CmsKeyValueDao.class);
	
	@Before
	public void setUp() {
		service.setCmsKeyValueDao(cmsKeyValueDao);
	}
	
	@Test
	public void test_create() {
		CmsKeyValue obj = CmsKeyValueDataFactory.newCmsKeyValue();
		service.create(obj);
		
		verify(cmsKeyValueDao).insert(obj); //验证执行了该语句
	}
	
	@Test
	public void test_update() {
		CmsKeyValue obj = CmsKeyValueDataFactory.newCmsKeyValue();
		service.update(obj);
		
		verify(cmsKeyValueDao).update(obj); //验证执行了该语句
	}
	
	@Test
	public void test_removeById() {
		service.removeById(new java.lang.String("1"),new java.lang.String("1"));
		
		verify(cmsKeyValueDao).deleteById(new java.lang.String("1"),new java.lang.String("1")); //验证执行了该语句
	}
	
	@Test
	public void test_getById() {
		when(cmsKeyValueDao.getById(new java.lang.String("1"),new java.lang.String("1"))).thenReturn(CmsKeyValueDataFactory.newCmsKeyValue()); // mock方法调用
		
		CmsKeyValue cmsKeyValue = service.getById(new java.lang.String("1"),new java.lang.String("1"));
		
		verify(cmsKeyValueDao).getById(new java.lang.String("1"),new java.lang.String("1")); //验证执行了该语句
		assertNotNull(cmsKeyValue);
	}
	
	
}

