/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.dao.impl;

import static com.duowan.common.util.ObjectUtils.isEmpty;
import static com.duowan.common.util.ObjectUtils.isNotEmpty;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import com.duowan.common.util.page.Page;
import com.fpcms.common.dao.BaseSpringJdbcDao;
import com.fpcms.dao.CmsDomainDao;
import com.fpcms.model.CmsDomain;
import com.fpcms.query.CmsDomainQuery;

/**
 * tableName: cms_domain
 * [CmsDomain] 的Dao操作 
 *  
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public class CmsDomainDaoImpl extends BaseSpringJdbcDao implements CmsDomainDao{
	
	private RowMapper<CmsDomain> entityRowMapper = new BeanPropertyRowMapper<CmsDomain>(getEntityClass());
	
	static final private String COLUMNS = "domain,remarks,props,ip";
	static final private String SELECT_FROM = "select " + COLUMNS + " from cms_domain";
	
	@Override
	public Class<CmsDomain> getEntityClass() {
		return CmsDomain.class;
	}
	
	@Override
	public String getIdentifierPropertyName() {
		return "domain";
	}
	
	public RowMapper<CmsDomain> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public void insert(CmsDomain entity) {
		String sql = "insert into cms_domain " 
			 + " (domain,remarks,props,ip) " 
			 + " values "
			 + " (:domain,:remarks,:props,:ip)";
//		insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		insertWithAssigned(entity,sql); //手工分配
	}
	
	public int update(CmsDomain entity) {
		String sql = "update cms_domain set "
					+ " remarks=:remarks,props=:props,ip=:ip "
					+ " where  domain = :domain ";
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public int deleteById(String domain) {
		String sql = "delete from cms_domain where  domain = ? ";
		return  getSimpleJdbcTemplate().update(sql,  domain);
	}

	public CmsDomain getById(String domain) {
		String sql = SELECT_FROM + " where  domain = ? ";
		return (CmsDomain)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),domain));
	}
	
	public CmsDomain getByRemarks(String remarks) {
		String sql =  SELECT_FROM + " where remarks=?";
		return (CmsDomain)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(), remarks));
	}	
	

	public Page<CmsDomain> findPage(CmsDomainQuery query) {
		
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from cms_domain where 1=1 ");
		if(isNotEmpty(query.getDomain())) {
            sql.append(" and domain = :domain ");
        }
		if(isNotEmpty(query.getRemarks())) {
            sql.append(" and remarks = :remarks ");
        }
		if(isNotEmpty(query.getProps())) {
            sql.append(" and props = :props ");
        }
		if(isNotEmpty(query.getIp())) {
            sql.append(" and ip = :ip ");
        }
		
		if(isEmpty(query.getSortColumns())) {
			query.setSortColumns("ip desc,domain desc");
		}
		sql.append(" order by "+query.getSortColumns());
		
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}

	@Override
	public List<CmsDomain> findAll() {
		return getSimpleJdbcTemplate().query(SELECT_FROM, getEntityRowMapper());
	}
	
}
