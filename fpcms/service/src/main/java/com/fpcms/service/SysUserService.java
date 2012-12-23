/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.service;

import com.duowan.common.util.page.Page;
import com.fpcms.model.SysUser;
import com.fpcms.query.SysUserQuery;


/**
 * [SysUser] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public interface SysUserService {

	/** 
	 * 创建SysUser
	 **/
	public SysUser create(SysUser sysUser);
	
	/** 
	 * 更新SysUser
	 **/	
    public SysUser update(SysUser sysUser);
    
	/** 
	 * 删除SysUser
	 **/
    public void removeById(long id);
    
	/** 
	 * 根据ID得到SysUser
	 **/    
    public SysUser getById(long id);
    
	/** 
	 * 分页查询: SysUser
	 **/      
	public Page<SysUser> findPage(SysUserQuery query);

	public SysUser findByUsername(String username);
	
    public SysUser authUser(String username,String password);
}
