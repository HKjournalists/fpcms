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
import com.fpcms.dao.CmsContentDao;
import com.fpcms.model.CmsContent;
import com.fpcms.query.CmsContentQuery;

/**
 * tableName: cms_content
 * [CmsContent] 的Dao操作 
 *  
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public class CmsContentDaoImpl extends BaseSpringJdbcDao implements CmsContentDao{
	
	private RowMapper<CmsContent> entityRowMapper = new BeanPropertyRowMapper<CmsContent>(getEntityClass());
	
	static final private String COLUMNS = "id,channel_code,tags,head_title,title,content,author,date_created,date_last_modified,site,level";
	static final private String SELECT_FROM = "select " + COLUMNS + " from cms_content";
	
	@Override
	public Class<CmsContent> getEntityClass() {
		return CmsContent.class;
	}
	
	@Override
	public String getIdentifierPropertyName() {
		return "id";
	}
	
	public RowMapper<CmsContent> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public void insert(CmsContent entity) {
		String sql = "insert into cms_content " 
			 + " (id,channel_code,tags,head_title,title,content,author,date_created,date_last_modified,site,level) " 
			 + " values "
			 + " (:id,:channelCode,:tags,:headTitle,:title,:content,:author,:dateCreated,:dateLastModified,:site,:level)";
		entity.setDateCreated(new Date());
		entity.setDateLastModified(new Date());
		insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		//insertWithAssigned(entity,sql) //手工分配
	}
	
	public int update(CmsContent entity) {
		String sql = "update cms_content set "
					+ " channel_code=:channelCode,tags=:tags,head_title=:headTitle,title=:title,content=:content,author=:author,date_created=:dateCreated,date_last_modified=:dateLastModified,site=:site,level=:level "
					+ " where  id = :id ";
		entity.setDateLastModified(new Date());
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public int deleteById(long id) {
		String sql = "delete from cms_content where  id = ? ";
		return  getSimpleJdbcTemplate().update(sql,  id);
	}

	public CmsContent getById(long id) {
		String sql = SELECT_FROM + " where  id = ? ";
		return (CmsContent)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),id));
	}
	

	public Page<CmsContent> findPage(CmsContentQuery query) {
		
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from cms_content where 1=1 ");
		if(isNotEmpty(query.getId())) {
            sql.append(" and id = :id ");
        }
		if(isNotEmpty(query.getChannelCode())) {
            sql.append(" and channel_code = :channelCode ");
        }
		if(isNotEmpty(query.getTags())) {
            sql.append(" and tags = :tags ");
        }
		if(isNotEmpty(query.getHeadTitle())) {
            sql.append(" and head_title = :headTitle ");
        }
		if(isNotEmpty(query.getTitle())) {
            sql.append(" and title = :title ");
        }
		if(isNotEmpty(query.getContent())) {
            sql.append(" and content = :content ");
        }
		if(isNotEmpty(query.getAuthor())) {
            sql.append(" and author = :author ");
        }
		if(isNotEmpty(query.getDateCreatedBegin())) {
		    sql.append(" and date_created >= :dateCreatedBegin ");
		}
		if(isNotEmpty(query.getDateCreatedEnd())) {
            sql.append(" and date_created <= :dateCreatedEnd ");
        }
		if(isNotEmpty(query.getDateLastModifiedBegin())) {
		    sql.append(" and date_last_modified >= :dateLastModifiedBegin ");
		}
		if(isNotEmpty(query.getDateLastModifiedEnd())) {
            sql.append(" and date_last_modified <= :dateLastModifiedEnd ");
        }
		if(isNotEmpty(query.getSite())) {
            sql.append(" and site = :site ");
        }
		
        sql.append(" order by date_created desc ");
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}

	@Override
	public List<CmsContent> findByChannelCode(String site,String channelCode) {
		String sql = SELECT_FROM+" where site = ? and channel_code = ? order by date_created desc";
		return getSimpleJdbcTemplate().query(sql,getEntityRowMapper(),site,channelCode);
	}
}
