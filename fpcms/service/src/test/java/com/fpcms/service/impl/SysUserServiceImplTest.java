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

import com.fpcms.SysUserDataFactory;
import com.fpcms.common.base.BaseServiceTestCase;
import com.fpcms.dao.SysUserDao;
import com.fpcms.model.SysUser;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class SysUserServiceImplTest extends BaseServiceTestCase{

	//mock框架使用Mockito 具体使用请查看: http://code.google.com/p/mockito/wiki/MockitoVSEasyMock
	
	private SysUserServiceImpl service = new SysUserServiceImpl();
	private SysUserDao sysUserDao = mock(SysUserDao.class);
	
	@Before
	public void setUp() {
		service.setSysUserDao(sysUserDao);
	}
	
	@Test
	public void test_create() {
		SysUser obj = SysUserDataFactory.newSysUser();
		service.create(obj);
		
		verify(sysUserDao).insert(obj); //验证执行了该语句
	}
	
	@Test
	public void test_update() {
		SysUser obj = SysUserDataFactory.newSysUser();
		service.update(obj);
		
		verify(sysUserDao).update(obj); //验证执行了该语句
	}
	
	@Test
	public void test_removeById() {
		service.removeById(new java.lang.Long("1"));
		
		verify(sysUserDao).deleteById(new java.lang.Long("1")); //验证执行了该语句
	}
	
	@Test
	public void test_getById() {
		when(sysUserDao.getById(new java.lang.Long("1"))).thenReturn(SysUserDataFactory.newSysUser()); // mock方法调用
		
		SysUser sysUser = service.getById(new java.lang.Long("1"));
		
		verify(sysUserDao).getById(new java.lang.Long("1")); //验证执行了该语句
		assertNotNull(sysUser);
	}
	
	
}

