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

import com.fpcms.CmsChannelDataFactory;
import com.fpcms.common.base.BaseServiceTestCase;
import com.fpcms.dao.CmsChannelDao;
import com.fpcms.model.CmsChannel;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsChannelServiceImplTest extends BaseServiceTestCase{

	//mock框架使用Mockito 具体使用请查看: http://code.google.com/p/mockito/wiki/MockitoVSEasyMock
	
	private CmsChannelServiceImpl service = new CmsChannelServiceImpl();
	private CmsChannelDao cmsChannelDao = mock(CmsChannelDao.class);
	
	@Before
	public void setUp() {
		service.setCmsChannelDao(cmsChannelDao);
	}
	
	@Test
	public void test_create() {
		CmsChannel obj = CmsChannelDataFactory.newCmsChannel();
		service.create(obj);
		
		verify(cmsChannelDao).insert(obj); //验证执行了该语句
	}
	
	@Test
	public void test_update() {
		CmsChannel obj = CmsChannelDataFactory.newCmsChannel();
		service.update(obj);
		
		verify(cmsChannelDao).update(obj); //验证执行了该语句
	}
	
	@Test
	public void test_removeById() {
		service.removeById("localhost",new java.lang.Long("1"));
		
		verify(cmsChannelDao).deleteById("localhost",new java.lang.Long("1")); //验证执行了该语句
	}
	
	@Test
	public void test_getById() {
		when(cmsChannelDao.getById("localhost",new java.lang.Long("1"))).thenReturn(CmsChannelDataFactory.newCmsChannel()); // mock方法调用
		
		CmsChannel cmsChannel = service.getById("localhost",new java.lang.Long("1"));
		
		verify(cmsChannelDao).getById("localhost",new java.lang.Long("1")); //验证执行了该语句
		assertNotNull(cmsChannel);
	}
	
	
}

