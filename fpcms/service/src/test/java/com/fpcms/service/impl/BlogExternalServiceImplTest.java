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

import com.fpcms.BlogExternalDataFactory;
import com.fpcms.common.base.BaseServiceTestCase;
import com.fpcms.dao.BlogExternalDao;
import com.fpcms.model.BlogExternal;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class BlogExternalServiceImplTest extends BaseServiceTestCase{

	//mock框架使用Mockito 具体使用请查看: http://code.google.com/p/mockito/wiki/MockitoVSEasyMock
	
	private BlogExternalServiceImpl service = new BlogExternalServiceImpl();
	private BlogExternalDao blogExternalDao = mock(BlogExternalDao.class);
	
	@Before
	public void setUp() {
		service.setBlogExternalDao(blogExternalDao);
	}
	
	@Test
	public void test_create() {
		BlogExternal obj = BlogExternalDataFactory.newBlogExternal();
		service.create(obj);
		
		verify(blogExternalDao).insert(obj); //验证执行了该语句
	}
	
	@Test
	public void test_update() {
		BlogExternal obj = BlogExternalDataFactory.newBlogExternal();
		service.update(obj);
		
		verify(blogExternalDao).update(obj); //验证执行了该语句
	}
	
	@Test
	public void test_removeById() {
		service.removeById(new java.lang.String("1"),new java.lang.String("1"));
		
		verify(blogExternalDao).deleteById(new java.lang.String("1"),new java.lang.String("1")); //验证执行了该语句
	}
	
	@Test
	public void test_getById() {
		when(blogExternalDao.getById(new java.lang.String("1"),new java.lang.String("1"))).thenReturn(BlogExternalDataFactory.newBlogExternal()); // mock方法调用
		
		BlogExternal blogExternal = service.getById(new java.lang.String("1"),new java.lang.String("1"));
		
		verify(blogExternalDao).getById(new java.lang.String("1"),new java.lang.String("1")); //验证执行了该语句
		assertNotNull(blogExternal);
	}
	
	
}

