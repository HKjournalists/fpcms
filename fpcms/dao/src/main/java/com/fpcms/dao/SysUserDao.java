/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao;

import com.github.rapid.common.util.page.Page;
import com.fpcms.model.SysUser;
import com.fpcms.query.SysUserQuery;

/**
 * tableName: sys_user
 * [SysUser] 的Dao操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public interface SysUserDao {
	
	public void insert(SysUser entity);
	
	public int update(SysUser entity);

	public int deleteById(long id);
	
	public SysUser getById(long id);
	

	public Page<SysUser> findPage(SysUserQuery query);

	public SysUser findByUsername(String username);	
	
}
