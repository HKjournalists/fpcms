/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao.impl;

import static com.duowan.common.util.ObjectUtils.isNotEmpty;

import java.util.Date;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import com.duowan.common.util.page.Page;
import com.fpcms.common.dao.BaseSpringJdbcDao;
import com.fpcms.dao.CmsAttachmentDao;
import com.fpcms.model.CmsAttachment;
import com.fpcms.query.CmsAttachmentQuery;

/**
 * tableName: cms_attachment
 * [CmsAttachment] 的Dao操作 
 *  
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public class CmsAttachmentDaoImpl extends BaseSpringJdbcDao implements CmsAttachmentDao{
	
	private RowMapper<CmsAttachment> entityRowMapper = new BeanPropertyRowMapper<CmsAttachment>(getEntityClass());
	
	static final private String COLUMNS = "id,code,attachment,remarks,date_last_modified,author";
	static final private String SELECT_FROM = "select " + COLUMNS + " from cms_attachment";
	
	@Override
	public Class<CmsAttachment> getEntityClass() {
		return CmsAttachment.class;
	}
	
	@Override
	public String getIdentifierPropertyName() {
		return "id";
	}
	
	public RowMapper<CmsAttachment> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public void insert(CmsAttachment entity) {
		String sql = "insert into cms_attachment " 
			 + " (id,code,attachment,remarks,date_last_modified,author) " 
			 + " values "
			 + " (:id,:code,:attachment,:remarks,:dateLastModified,:author)";
		entity.setDateLastModified(new Date());
		insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		//insertWithAssigned(entity,sql) //手工分配
	}
	
	public int update(CmsAttachment entity) {
		String sql = "update cms_attachment set "
					+ " code=:code,attachment=:attachment,remarks=:remarks,date_last_modified=:dateLastModified,author=:author "
					+ " where  id = :id ";
		entity.setDateLastModified(new Date());
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public int deleteById(long id) {
		String sql = "delete from cms_attachment where  id = ? ";
		return  getSimpleJdbcTemplate().update(sql,  id);
	}

	public CmsAttachment getById(long id) {
		String sql = SELECT_FROM + " where  id = ? ";
		return (CmsAttachment)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),id));
	}
	

	public Page<CmsAttachment> findPage(CmsAttachmentQuery query) {
		
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from cms_attachment where 1=1 ");
		if(isNotEmpty(query.getId())) {
            sql.append(" and id = :id ");
        }
		if(isNotEmpty(query.getCode())) {
            sql.append(" and code = :code ");
        }
		if(isNotEmpty(query.getRemarks())) {
            sql.append(" and remarks = :remarks ");
        }
		if(isNotEmpty(query.getDateLastModifiedBegin())) {
		    sql.append(" and date_last_modified >= :dateLastModifiedBegin ");
		}
		if(isNotEmpty(query.getDateLastModifiedEnd())) {
            sql.append(" and date_last_modified <= :dateLastModifiedEnd ");
        }
		if(isNotEmpty(query.getAuthor())) {
            sql.append(" and author = :author ");
        }
		
        //sql.append(" order by :sortColumns ");
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}
}
