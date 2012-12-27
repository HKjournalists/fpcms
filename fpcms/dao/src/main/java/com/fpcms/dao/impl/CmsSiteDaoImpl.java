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
	
	static final private String COLUMNS = "site_domain,site_name,site_desc,city,keyword,remarks,company,contact_name,mobile,qq,email,date_created,date_last_modified,author";
	static final private String SELECT_FROM = "select " + COLUMNS + " from cms_site";
	
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
		entity.setDateCreated(new Date());
		entity.setDateLastModified(new Date());
		String sql = "insert into cms_site " 
			 + " (site_domain,site_name,site_desc,city,keyword,remarks,company,contact_name,mobile,qq,email,date_created,date_last_modified,author) " 
			 + " values "
			 + " (:siteDomain,:siteName,:siteDesc,:city,:keyword,:remarks,:company,:contactName,:mobile,:qq,:email,:dateCreated,:dateLastModified,:author)";
//		insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		
		//其它主键生成策略
		//insertWithOracleSequence(entity,"sequenceName",sql); //oracle sequence: 
		//insertWithDB2Sequence(entity,"sequenceName",sql); //db2 sequence:
		//insertWithUUID(entity,sql); //uuid
		insertWithAssigned(entity,sql); //手工分配
	}
	
	public int update(CmsSite entity) {
		entity.setDateLastModified(new Date());
		String sql = "update cms_site set "
					+ " site_name=:siteName,site_desc=:siteDesc,city=:city,keyword=:keyword,remarks=:remarks,company=:company,contact_name=:contactName,mobile=:mobile,qq=:qq,email=:email,date_created=:dateCreated,date_last_modified=:dateLastModified,author=:author "
					+ " where  site_domain = :siteDomain ";
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public int deleteById(String siteDomain) {
		String sql = "delete from cms_site where  site_domain = ? ";
		return  getSimpleJdbcTemplate().update(sql,  siteDomain);
	}

	public CmsSite getById(String siteDomain) {
		String sql = SELECT_FROM + " where  site_domain = ? ";
		return (CmsSite)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),siteDomain));
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
		
        //sql.append(" order by :sortColumns ");
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}
}
