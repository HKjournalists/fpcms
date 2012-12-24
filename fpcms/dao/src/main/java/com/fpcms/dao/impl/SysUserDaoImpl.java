/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao.impl;

import static com.duowan.common.util.ObjectUtils.isNotEmpty;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import com.duowan.common.util.page.Page;
import com.fpcms.common.dao.BaseSpringJdbcDao;
import com.fpcms.dao.SysUserDao;
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
public class SysUserDaoImpl extends BaseSpringJdbcDao implements SysUserDao{
	
	private RowMapper<SysUser> entityRowMapper = new BeanPropertyRowMapper<SysUser>(getEntityClass());
	
	static final private String COLUMNS = "id,username,password,remark,enabled";
	static final private String SELECT_FROM = "select " + COLUMNS + " from sys_user";
	
	@Override
	public Class<SysUser> getEntityClass() {
		return SysUser.class;
	}
	
	@Override
	public String getIdentifierPropertyName() {
		return "id";
	}
	
	public RowMapper<SysUser> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public void insert(SysUser entity) {
		String sql = "insert into sys_user " 
			 + " (id,username,password,remark,enabled) " 
			 + " values "
			 + " (:id,:username,:password,:remark,:enabled)";
		insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		//insertWithAssigned(entity,sql) //手工分配
	}
	
	public int update(SysUser entity) {
		String sql = "update sys_user set "
					+ " username=:username,password=:password,remark=:remark,enabled=:enabled "
					+ " where  id = :id ";
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public int deleteById(long id) {
		String sql = "delete from sys_user where  id = ? ";
		return  getSimpleJdbcTemplate().update(sql,  id);
	}

	public SysUser getById(long id) {
		String sql = SELECT_FROM + " where  id = ? ";
		return (SysUser)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),id));
	}
	

	public Page<SysUser> findPage(SysUserQuery query) {
		
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from sys_user where 1=1 ");
		if(isNotEmpty(query.getId())) {
            sql.append(" and id = :id ");
        }
		if(isNotEmpty(query.getUsername())) {
            sql.append(" and username = :username ");
        }
		if(isNotEmpty(query.getPassword())) {
            sql.append(" and password = :password ");
        }
		if(isNotEmpty(query.getRemark())) {
            sql.append(" and remark = :remark ");
        }
		if(isNotEmpty(query.getEnabled())) {
            sql.append(" and enabled = :enabled ");
        }
		
        //sql.append(" order by :sortColumns ");
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}

	@Override
	public SysUser findByUsername(String username) {
		String sql = SELECT_FROM + " where  username = ? ";
		return (SysUser)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),username));
	}
	
}
