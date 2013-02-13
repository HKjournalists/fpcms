/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao.impl;

import static com.duowan.common.util.ObjectUtils.isNotEmpty;
import static com.duowan.common.util.ObjectUtils.isEmpty;

import java.util.Date;
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
import com.fpcms.dao.CmsSiteDao;
import com.fpcms.model.CmsSite;
import com.fpcms.query.CmsSiteQuery;

/**
 * tableName: cms_site
 * [CmsSite] 的Dao操作 
 *  
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public class CmsSiteDaoImpl extends BaseSpringJdbcDao implements CmsSiteDao{
	
	private RowMapper<CmsSite> entityRowMapper = new BeanPropertyRowMapper<CmsSite>(getEntityClass());
	
	static final private String COLUMNS = "site_domain,site_name,site_desc,city,keyword,remarks,company,contact_name,mobile,qq,email,date_created,date_last_modified,author,record_baidu,record_360,record_google,rank_baidu,rank_360,rank_google,redirect_site,daily_gen_content_count,props,ip,http_status";
	static final private String SELECT_FROM = "select " + COLUMNS + " from cms_site";
	private Cache cache = CacheManager.createCache(CmsSiteDaoImpl.class,1000);
	
	@Override
	public Class<CmsSite> getEntityClass() {
		return CmsSite.class;
	}
	
	@Override
	public String getIdentifierPropertyName() {
		return "siteDomain";
	}
	
	public RowMapper<CmsSite> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public void insert(CmsSite entity) {
		cache.clear();
		entity.setDateCreated(new Date());
		entity.setDateLastModified(new Date());
		String sql = "insert into cms_site " 
			 + " (site_domain,site_name,site_desc,city,keyword,remarks,company,contact_name,mobile,qq,email,date_created,date_last_modified,author,record_baidu,record_360,record_google,rank_baidu,rank_360,rank_google,redirect_site,daily_gen_content_count,props,ip,http_status) " 
			 + " values "
			 + " (:siteDomain,:siteName,:siteDesc,:city,:keyword,:remarks,:company,:contactName,:mobile,:qq,:email,:dateCreated,:dateLastModified,:author,:recordBaidu,:record360,:recordGoogle,:rankBaidu,:rank360,:rankGoogle,:redirectSite,:dailyGenContentCount,:props,:ip,:httpStatus)";
//		insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		insertWithAssigned(entity,sql); //手工分配
	}
	
	public int update(CmsSite entity) {
		cache.clear();
		entity.setDateLastModified(new Date());
		String sql = "update cms_site set "
					+ " site_name=:siteName,site_desc=:siteDesc,city=:city,keyword=:keyword,remarks=:remarks,company=:company,contact_name=:contactName,mobile=:mobile,qq=:qq,email=:email,date_created=:dateCreated,date_last_modified=:dateLastModified,author=:author,record_baidu=:recordBaidu,record_360=:record360,record_google=:recordGoogle,rank_baidu=:rankBaidu,rank_360=:rank360,rank_google=:rankGoogle,redirect_site=:redirectSite,daily_gen_content_count=:dailyGenContentCount,props=:props,ip=:ip,http_status=:httpStatus "
					+ " where  site_domain = :siteDomain ";
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public int deleteById(String siteDomain) {
		cache.clear();
		String sql = "delete from cms_site where  site_domain = ? ";
		return  getSimpleJdbcTemplate().update(sql,  siteDomain);
	}

	public CmsSite getById(final String siteDomain) {
		return cache.get(siteDomain, 3600 * 24, new ValueCallback<CmsSite>() {
			@Override
			public CmsSite create(String key) {
				String sql = SELECT_FROM + " where  site_domain = ? ";
				return (CmsSite)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),siteDomain));
			}
		});
	}

	public Page<CmsSite> findPage(CmsSiteQuery query) {
		
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from cms_site where 1=1 ");
		if(isNotEmpty(query.getSiteDomain())) {
            sql.append(" and site_domain like concat('%',:siteDomain,'%') ");
        }
		if(isNotEmpty(query.getSiteName())) {
            sql.append(" and site_name like concat('%',:siteName,'%') ");
        }
		if(isNotEmpty(query.getSiteDesc())) {
            sql.append(" and site_desc = :siteDesc ");
        }
		if(isNotEmpty(query.getCity())) {
            sql.append(" and city = :city ");
        }
		if(isNotEmpty(query.getKeyword())) {
            sql.append(" and keyword = :keyword ");
        }
		if(isNotEmpty(query.getRemarks())) {
            sql.append(" and remarks = :remarks ");
        }
		if(isNotEmpty(query.getCompany())) {
            sql.append(" and company = :company ");
        }
		if(isNotEmpty(query.getContactName())) {
            sql.append(" and contact_name = :contactName ");
        }
		if(isNotEmpty(query.getMobile())) {
            sql.append(" and mobile = :mobile ");
        }
		if(isNotEmpty(query.getQq())) {
            sql.append(" and qq = :qq ");
        }
		if(isNotEmpty(query.getEmail())) {
            sql.append(" and email = :email ");
        }
		if(isEmpty(query.getSortColumns())) {
			query.setSortColumns("rank_baidu desc,site_domain desc");
		}
		sql.append(" order by "+query.getSortColumns());
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}

	@Override
	public List<CmsSite> findAll() {
		return getSimpleJdbcTemplate().query(SELECT_FROM,getEntityRowMapper());
	}

	@Override
	public List<CmsSite> findSubSites(String domain) {
		String sql = SELECT_FROM + " where site_domain like concat('%',?)";
		return getSimpleJdbcTemplate().query(sql,getEntityRowMapper(),domain);
	}
	
}
