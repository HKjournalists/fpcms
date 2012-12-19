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

import com.fpcms.CmsContentDataFactory;
import com.fpcms.common.base.BaseServiceTestCase;
import com.fpcms.dao.CmsContentDao;
import com.fpcms.model.CmsContent;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsContentServiceImplTest extends BaseServiceTestCase{

	//mock框架使用Mockito 具体使用请查看: http://code.google.com/p/mockito/wiki/MockitoVSEasyMock
	
	private CmsContentServiceImpl service = new CmsContentServiceImpl();
	private CmsContentDao cmsContentDao = mock(CmsContentDao.class);
	
	@Before
	public void setUp() {
		service.setCmsContentDao(cmsContentDao);
	}
	
	@Test
	public void test_create() {
		CmsContent obj = CmsContentDataFactory.newCmsContent();
		service.create(obj);
		
		verify(cmsContentDao).insert(obj); //验证执行了该语句
	}
	
	@Test
	public void test_update() {
		CmsContent obj = CmsContentDataFactory.newCmsContent();
		service.update(obj);
		
		verify(cmsContentDao).update(obj); //验证执行了该语句
	}
	
	@Test
	public void test_removeById() {
		service.removeById(new java.lang.Long("1"));
		
		verify(cmsContentDao).deleteById(new java.lang.Long("1")); //验证执行了该语句
	}
	
	@Test
	public void test_getById() {
		when(cmsContentDao.getById(new java.lang.Long("1"))).thenReturn(CmsContentDataFactory.newCmsContent()); // mock方法调用
		
		CmsContent cmsContent = service.getById(new java.lang.Long("1"));
		
		verify(cmsContentDao).getById(new java.lang.Long("1")); //验证执行了该语句
		assertNotNull(cmsContent);
	}
	
	
}

