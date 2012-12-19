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

import com.fpcms.CmsAttachmentDataFactory;
import com.fpcms.common.base.BaseServiceTestCase;
import com.fpcms.dao.CmsAttachmentDao;
import com.fpcms.model.CmsAttachment;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsAttachmentServiceImplTest extends BaseServiceTestCase{

	//mock框架使用Mockito 具体使用请查看: http://code.google.com/p/mockito/wiki/MockitoVSEasyMock
	
	private CmsAttachmentServiceImpl service = new CmsAttachmentServiceImpl();
	private CmsAttachmentDao cmsAttachmentDao = mock(CmsAttachmentDao.class);
	
	@Before
	public void setUp() {
		service.setCmsAttachmentDao(cmsAttachmentDao);
	}
	
	@Test
	public void test_create() {
		CmsAttachment obj = CmsAttachmentDataFactory.newCmsAttachment();
		service.create(obj);
		
		verify(cmsAttachmentDao).insert(obj); //验证执行了该语句
	}
	
	@Test
	public void test_update() {
		CmsAttachment obj = CmsAttachmentDataFactory.newCmsAttachment();
		service.update(obj);
		
		verify(cmsAttachmentDao).update(obj); //验证执行了该语句
	}
	
	@Test
	public void test_removeById() {
		service.removeById(new java.lang.Long("1"));
		
		verify(cmsAttachmentDao).deleteById(new java.lang.Long("1")); //验证执行了该语句
	}
	
	@Test
	public void test_getById() {
		when(cmsAttachmentDao.getById(new java.lang.Long("1"))).thenReturn(CmsAttachmentDataFactory.newCmsAttachment()); // mock方法调用
		
		CmsAttachment cmsAttachment = service.getById(new java.lang.Long("1"));
		
		verify(cmsAttachmentDao).getById(new java.lang.Long("1")); //验证执行了该语句
		assertNotNull(cmsAttachment);
	}
	
	
}

