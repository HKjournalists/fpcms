/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao.impl;

import static com.duowan.common.util.ObjectUtils.isNotEmpty;

import java.util.Date;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import com.duowan.common.util.page.Page;
import com.fpcms.common.dao.BaseSpringJdbcDao;
import com.fpcms.dao.CmsChannelDao;
import com.fpcms.model.CmsChannel;
import com.fpcms.query.CmsChannelQuery;

/**
 * tableName: cms_channel
 * [CmsChannel] 的Dao操作 
 *  
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public class CmsChannelDaoImpl extends BaseSpringJdbcDao implements CmsChannelDao{
	
	private RowMapper<CmsChannel> entityRowMapper = new BeanPropertyRowMapper<CmsChannel>(getEntityClass());
	
	static final private String COLUMNS = "id,channel_name,channel_code,channel_desc,parent_id,date_last_modified,author,site,level,content";
	static final private String SELECT_FROM = "select " + COLUMNS + " from cms_channel";
	
	@Override
	public Class<CmsChannel> getEntityClass() {
		return CmsChannel.class;
	}
	
	@Override
	public String getIdentifierPropertyName() {
		return "id";
	}
	
	public RowMapper<CmsChannel> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public void insert(CmsChannel entity) {
		String sql = "insert into cms_channel " 
			 + " (id,channel_name,channel_code,channel_desc,parent_id,date_last_modified,author,site,level,content) " 
			 + " values "
			 + " (:id,:channelName,:channelCode,:channelDesc,:parentId,:dateLastModified,:author,:site,:level,:content)";
		entity.setDateLastModified(new Date());
		insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		//insertWithAssigned(entity,sql) //手工分配
	}
	
	public int update(CmsChannel entity) {
		entity.setDateLastModified(new Date());
		
		String sql = "update cms_channel set "
					+ " channel_name=:channelName,channel_code=:channelCode,channel_desc=:channelDesc,parent_id=:parentId,date_last_modified=:dateLastModified,author=:author,site=:site,level=:level,content=:content "
					+ " where  id = :id ";
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public int deleteById(long id) {
		String sql = "delete from cms_channel where  id = ? ";
		return  getSimpleJdbcTemplate().update(sql,  id);
	}

	public CmsChannel getById(long id) {
		String sql = SELECT_FROM + " where  id = ? ";
		return (CmsChannel)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),id));
	}
	

	public Page<CmsChannel> findPage(CmsChannelQuery query) {
		
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from cms_channel where 1=1 ");
		if(isNotEmpty(query.getId())) {
            sql.append(" and id = :id ");
        }
		if(isNotEmpty(query.getChannelName())) {
            sql.append(" and channel_name = :channelName ");
        }
		if(isNotEmpty(query.getChannelCode())) {
            sql.append(" and channel_code = :channelCode ");
        }
		if(isNotEmpty(query.getChannelDesc())) {
            sql.append(" and channel_desc = :channelDesc ");
        }
		if(isNotEmpty(query.getParentId())) {
            sql.append(" and parent_id = :parentId ");
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
		if(isNotEmpty(query.getSite())) {
            sql.append(" and site = :site ");
        }
		
        //sql.append(" order by :sortColumns ");
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}

	@Override
	public List<CmsChannel> findAll() {
		return getSimpleJdbcTemplate().query(SELECT_FROM,getEntityRowMapper());
	}

	@Override
	public List<CmsChannel> findChildsByChannelId(long channelId) {
		return getSimpleJdbcTemplate().query(SELECT_FROM + " where parent_id = ? order by level ",getEntityRowMapper(),channelId);
	}


}
