/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao.impl;

import static com.duowan.common.util.ObjectUtils.isNotEmpty;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.util.Assert;

import com.duowan.common.util.DateRange;
import com.duowan.common.util.page.Page;
import com.duowan.common.util.page.PageQuery;
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
	
	static final private String COLUMNS = "id,channel_code,tags,head_title,title,content,author,date_created,date_last_modified,site,level,search_keyword,source_url";
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
			 + " (id,channel_code,tags,head_title,title,content,author,date_created,date_last_modified,site,level,search_keyword,source_url) " 
			 + " values "
			 + " (:id,:channelCode,:tags,:headTitle,:title,:content,:author,:dateCreated,:dateLastModified,:site,:level,:searchKeyword,:sourceUrl)";
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
					+ " channel_code=:channelCode,tags=:tags,head_title=:headTitle,title=:title,content=:content,author=:author,date_created=:dateCreated,date_last_modified=:dateLastModified,site=:site,level=:level,search_keyword=:searchKeyword,source_url=:sourceUrl"
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
		
        sql.append(" order by date_created desc");
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}

	@Override
	public CmsContent getNextCmsContent(Date dateCreated,String site,long id) {
		Date start = DateUtils.addDays(dateCreated, -1);
		Date end = DateUtils.addDays(dateCreated, 14);
		String sql = SELECT_FROM+" where site = ? and id > ? and date_created between ? and ? order by id asc limit 1";
		return (CmsContent)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),site,id,start,end));
	}

	@Override // 
	public CmsContent getPreCmsContent(Date dateCreated,String site,long id) {
		Date start = DateUtils.addDays(dateCreated, -14);
		Date end = DateUtils.addDays(dateCreated, 1);
		String sql = SELECT_FROM+" where site = ? and id < ? and date_created between ? and ? order by id desc limit 1";
		return (CmsContent)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),site,id,start,end));
	}

	@Override // 己调优 
	public int countByTitle(Date start, Date end, String title) {
		String sql = "select count(*) from cms_content where date_created between ? and ? and title = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, start,end,title);
	}

	@Override
	public Page<CmsContent> findPage(PageQuery pageQuery, String site,
			String channelCode, DateRange createdRange) {
		Assert.hasText(site,"site must be not empty");
		Assert.hasText(channelCode,"channelCode must be not empty");
		Assert.notNull(createdRange,"createdRange must be not null");
		Assert.notNull(createdRange.getStartDate(),"createdRange.getStartDate() must be not null");
		Assert.notNull(createdRange.getEndDate(),"createdRange.getEndDate() must be not null");
		
		String sql = SELECT_FROM +" where site=:site and channel_code = :channelCode and date_created between :dateCreatedBegin and :dateCreatedEnd order by date_created desc ";
		Map param = new HashMap();
		param.put("site", site);
		param.put("channelCode", channelCode);
		param.put("dateCreatedBegin", createdRange.getStartDate());
		param.put("dateCreatedEnd", createdRange.getEndDate());
		return pageQuery(sql.toString(),param,pageQuery.getPageSize(),pageQuery.getPage(),getEntityRowMapper());
	}

	@Override
	public int countBySearchKeyword(Date start, Date end, String searchKeyword) {
		String sql = "select count(*) from cms_content where date_created between ? and ? and search_keyword = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, start,end,searchKeyword);
	}

	@Override
	public int countBySourceUrl(Date start, Date end, String sourceUrl) {
		String sql = "select count(*) from cms_content where date_created between ? and ? and source_url = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, start,end,sourceUrl);
	}
	
	@Override
	public CmsContent getById(Date dateCreated, long id) {
		Date end = DateUtils.addDays(dateCreated, 1);
		String sql = SELECT_FROM + " where  id = ?  and date_created between ? and ?";
		return (CmsContent)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),id,dateCreated,end));
	}

	@Override
	public CmsContent findLastBySite(String site) {
		Date start = DateUtils.addDays(new Date(), -13);
		Date end = new Date();
		String sql = SELECT_FROM + " where site = ? and date_created between ? and ? order by date_created desc limit 1";
		return (CmsContent)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),site,start,end));
	}
	
}
