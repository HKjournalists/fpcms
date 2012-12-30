/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao.impl;

import static com.duowan.common.util.ObjectUtils.isNotEmpty;

import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import com.duowan.common.util.page.Page;
import com.fpcms.common.cache.Cache;
import com.fpcms.common.cache.CacheManager;
import com.fpcms.common.cache.ValueCallback;
import com.fpcms.common.dao.BaseSpringJdbcDao;
import com.fpcms.dao.CmsPropertyDao;
import com.fpcms.model.CmsProperty;
import com.fpcms.query.CmsPropertyQuery;

/**
 * tableName: cms_property
 * [CmsProperty] 的Dao操作 
 *  
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public class CmsPropertyDaoImpl extends BaseSpringJdbcDao implements CmsPropertyDao{
	
	private RowMapper<CmsProperty> entityRowMapper = new BeanPropertyRowMapper<CmsProperty>(getEntityClass());
	
	static final private String COLUMNS = "prop_group,prop_key,prop_value,ramarks";
	static final private String SELECT_FROM = "select " + COLUMNS + " from cms_property";
	
	Cache cache = CacheManager.createCache(1000);
	
	@Override
	public Class<CmsProperty> getEntityClass() {
		return CmsProperty.class;
	}
	
	@Override
	public String getIdentifierPropertyName() {
		return "propGroup";
	}
	
	public RowMapper<CmsProperty> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public void insert(CmsProperty entity) {
		String sql = "insert into cms_property " 
			 + " (prop_group,prop_key,prop_value,ramarks) " 
			 + " values "
			 + " (:propGroup,:propKey,:propValue,:ramarks)";
//		insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		insertWithAssigned(entity,sql); //手工分配
		cache.clear();
	}
	
	public int update(CmsProperty entity) {
		cache.clear();
		String sql = "update cms_property set "
					+ " prop_value=:propValue,ramarks=:ramarks "
					+ " where  prop_group = :propGroup and prop_key = :propKey ";
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public int deleteById(String propGroup, String propKey) {
		cache.clear();
		String sql = "delete from cms_property where  prop_group = ? and prop_key = ? ";
		return  getSimpleJdbcTemplate().update(sql,  propGroup,propKey);
	}

	public CmsProperty getById(String propGroup, String propKey) {
		String sql = SELECT_FROM + " where  prop_group = ? and prop_key = ? ";
		return (CmsProperty)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),propGroup,propKey));
	}
	

	public Page<CmsProperty> findPage(CmsPropertyQuery query) {
		
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from cms_property where 1=1 ");
		if(isNotEmpty(query.getPropGroup())) {
            sql.append(" and prop_group = :propGroup ");
        }
		if(isNotEmpty(query.getPropKey())) {
            sql.append(" and prop_key = :propKey ");
        }
		if(isNotEmpty(query.getPropValue())) {
            sql.append(" and prop_value = :propValue ");
        }
		if(isNotEmpty(query.getRamarks())) {
            sql.append(" and ramarks = :ramarks ");
        }
		
        //sql.append(" order by :sortColumns ");
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}

	@Override
	public List<CmsProperty> findByGroup(final String group) {
		return cache.get("findByGroup:"+group, 3600, new ValueCallback<List<CmsProperty>>() {
			public List<CmsProperty> create(String key) {
				return getSimpleJdbcTemplate().query(SELECT_FROM + " where prop_group = ?", getEntityRowMapper(),group);
			}
		});
	}
	
}
