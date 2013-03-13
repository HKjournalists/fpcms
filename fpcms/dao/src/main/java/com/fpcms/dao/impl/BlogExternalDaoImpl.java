/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */

package com.fpcms.dao.impl;

import static com.duowan.common.util.ObjectUtils.isNotEmpty;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import com.duowan.common.util.page.Page;
import com.fpcms.common.dao.BaseSpringJdbcDao;
import com.fpcms.dao.BlogExternalDao;
import com.fpcms.model.BlogExternal;
import com.fpcms.query.BlogExternalQuery;

/**
 * tableName: blog_external
 * [BlogExternal] 的Dao操作 
 *  
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
*/
public class BlogExternalDaoImpl extends BaseSpringJdbcDao implements BlogExternalDao{
	
	private RowMapper<BlogExternal> entityRowMapper = new BeanPropertyRowMapper<BlogExternal>(getEntityClass());
	
	static final private String COLUMNS = "blog_url,blog_rpc_url,blog_name,username,password,tags,categories,blog_rpc_api,blog_desc,blog_post_count,blog_rpc_api_class";
	static final private String SELECT_FROM = "select " + COLUMNS + " from blog_external";
	
	@Override
	public Class<BlogExternal> getEntityClass() {
		return BlogExternal.class;
	}
	
	@Override
	public String getIdentifierPropertyName() {
		return "blogUrl";
	}
	
	public RowMapper<BlogExternal> getEntityRowMapper() {
		return entityRowMapper;
	}
	
	public void insert(BlogExternal entity) {
		String sql = "insert into blog_external " 
			 + " (blog_url,blog_rpc_url,blog_name,username,password,tags,categories,blog_rpc_api,blog_desc,blog_post_count,blog_rpc_api_class) " 
			 + " values "
			 + " (:blogUrl,:blogRpcUrl,:blogName,:username,:password,:tags,:categories,:blogRpcApi,:blogDesc,:blogPostCount,:blogRpcApiClass)";
//		insertWithGeneratedKey(entity,sql); //for sqlserver:identity and mysql:auto_increment
		
		insertWithAssigned(entity,sql); //手工分配
	}
	
	public int update(BlogExternal entity) {
		String sql = "update blog_external set "
					+ " blog_rpc_url=:blogRpcUrl,blog_name=:blogName,tags=:tags,categories=:categories,blog_rpc_api=:blogRpcApi,blog_desc=:blogDesc,blog_post_count=:blogPostCount,blog_rpc_api_class=:blogRpcApiClass "
					+ " where  blog_url = :blogUrl and username = :username and password = :password ";
		return getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(entity));
	}
	
	public int deleteById(String blogUrl, String username, String password) {
		String sql = "delete from blog_external where  blog_url = ? and username = ? and password = ? ";
		return  getSimpleJdbcTemplate().update(sql,  blogUrl,username,password);
	}

	public BlogExternal getById(String blogUrl, String username, String password) {
		String sql = SELECT_FROM + " where  blog_url = ? and username = ? and password = ? ";
		return (BlogExternal)DataAccessUtils.singleResult(getSimpleJdbcTemplate().query(sql, getEntityRowMapper(),blogUrl,username,password));
	}
	

	public Page<BlogExternal> findPage(BlogExternalQuery query) {
		
		StringBuilder sql = new StringBuilder("select "+ COLUMNS + " from blog_external where 1=1 ");
		if(isNotEmpty(query.getBlogUrl())) {
            sql.append(" and blog_url = :blogUrl ");
        }
		if(isNotEmpty(query.getBlogRpcUrl())) {
            sql.append(" and blog_rpc_url = :blogRpcUrl ");
        }
		if(isNotEmpty(query.getBlogName())) {
            sql.append(" and blog_name = :blogName ");
        }
		if(isNotEmpty(query.getUsername())) {
            sql.append(" and username = :username ");
        }
		if(isNotEmpty(query.getPassword())) {
            sql.append(" and password = :password ");
        }
		if(isNotEmpty(query.getTags())) {
            sql.append(" and tags = :tags ");
        }
		if(isNotEmpty(query.getCategories())) {
            sql.append(" and categories = :categories ");
        }
		if(isNotEmpty(query.getBlogRpcApi())) {
            sql.append(" and blog_rpc_api = :blogRpcApi ");
        }
		if(isNotEmpty(query.getBlogDesc())) {
            sql.append(" and blog_desc = :blogDesc ");
        }
		
        //sql.append(" order by :sortColumns ");
		
		return pageQuery(sql.toString(),query,getEntityRowMapper());				
	}
}
