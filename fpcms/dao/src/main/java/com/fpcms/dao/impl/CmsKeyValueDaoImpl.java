/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.dao.impl;

import static com.duowan.common.util.ObjectUtils.isEmpty;
import static com.duowan.common.util.ObjectUtils.isNotEmpty;

import java.util.Date;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import com.duowan.common.util.page.Page;
import com.fpcms.common.dao.BaseSpringJdbcDao;
import com.fpcms.dao.CmsKeyValueDao;
import com.fpcms.model.CmsKeyValue;
import com.fpcms.query.CmsKeyValueQuery;

/**
 * tableName: cms_key_value
 * [CmsKeyValue] 的Dao操作 
 *  
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public class CmsKeyValueDaoImpl extends BaseSpringJdbcDao implements CmsKeyValueDao{
	
	private RowMapper<CmsKeyValue> entityRowMapper = new BeanPropertyRowMapper<CmsKeyValue>(getEntityClass());
	
	static final private String COLUMNS = "date_created,key_group,str_key,value";
	static final private String SELECT_FROM = "select " + COLUMNS + " from cms_key_value";
	
	@Override
	public Class<CmsKeyValue> getEntityClass() {
		return CmsKeyValue.class;
	}
	
	@Override
	public String getIdentifierPropertyName() {
		return "keyGroup";
	}
	
	public RowMapper<CmsKeyValue> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public void insert(CmsKeyValue entity) {
		String sql = "insert into cms_key_value " 
			 + " (date_created,key_group,str_key,value) " 
			 + " values "
			 + " (:dateCreated,:keyGroup,:strKey,:value)";
		insertWithAssigned(entity,sql); //手工分配
	}
	
	public int update(CmsKeyValue entity) {
		String sql = "update cms_key_value set "
					+ " date_created=:dateCreated,value=:value "
					+ " where  key_group = :keyGroup and str_key = :strKey ";
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public int deleteById(String keyGroup, String key) {
		String sql = "delete from cms_key_value where  key_group = ? and str_key = ? ";
		return  getSimpleJdbcTemplate().update(sql,  keyGroup,key);
	}

	public CmsKeyValue getById(String keyGroup, String key) {
		String sql = SELECT_FROM + " where  key_group = ? and str_key = ? ";
		return (CmsKeyValue)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),keyGroup,key));
	}
	
//	public int deleteBy(String keyGroup, Date dateCreated) {
//		String sql = "delete from cms_key_value where  key_group = ? and date_created >= ? ";
//		return  getSimpleJdbcTemplate().update(sql, keyGroup,dateCreated);
//	}

	public int deleteBy(Date beforeDateCreated) {
		String sql = "delete from cms_key_value where date_created <= ? ";
		return  getSimpleJdbcTemplate().update(sql,beforeDateCreated);
	}
	
	public Page<CmsKeyValue> findPage(CmsKeyValueQuery query) {
		
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from cms_key_value where 1=1 ");
		if(isNotEmpty(query.getDateCreatedBegin())) {
		    sql.append(" and date_created >= :dateCreatedBegin ");
		}
		if(isNotEmpty(query.getDateCreatedEnd())) {
            sql.append(" and date_created <= :dateCreatedEnd ");
        }
		if(isNotEmpty(query.getKeyGroup())) {
            sql.append(" and key_group = :keyGroup ");
        }
		if(isNotEmpty(query.getStrKey())) {
            sql.append(" and str_key = :strKey ");
        }
		if(isNotEmpty(query.getValue())) {
            sql.append(" and value = :value ");
        }
		if(isEmpty(query.getSortColumns())) {
			query.setSortColumns("date_created desc");
		}
		
        sql.append(" order by :sortColumns ");
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}
}
