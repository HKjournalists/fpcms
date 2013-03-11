/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */

package com.fpcms.dao.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import com.duowan.common.util.DateConvertUtils;
import com.duowan.common.util.DateRange;
import com.duowan.common.util.page.Page;
import com.duowan.common.util.page.PageQuery;
import com.fpcms.CmsContentDataFactory;
import com.fpcms.common.base.BaseDaoTestCase;
import com.fpcms.dao.CmsContentDao;
import com.fpcms.model.CmsContent;
import com.fpcms.query.CmsContentQuery;


/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */
public class CmsContentDaoImplTest extends BaseDaoTestCase{
	
	@Rule public TestName testName = new TestName();
	
	private CmsContentDao dao;
	
	@Autowired
	public void setCmsContentDao(CmsContentDao dao) {
		this.dao = dao;
	}

	@Override 
	protected String[] getDbUnitDataFiles() {
	    //通过testName.getMethodName() 可以得到当前正在运行的测试方法名称
//		return new String[]{"classpath:testdata/common.xml","classpath:testdata/CmsContent.xml",
//		                    "classpath:testdata/CmsContent_"+testName.getMethodName()+".xml"};
		return null;
	}
	
	//数据库单元测试前会开始事务，结束时会回滚事务，所以测试方法可以不用关心测试数据的删除
	@Test
	public void findPage() {

		CmsContentQuery query = CmsContentDataFactory.newCmsContentQuery();
		Page page = dao.findPage(query);
		
		assertEquals(1,page.getPaginator().getPage());
		assertEquals(10,page.getPaginator().getPageSize());
		List resultList = (List)page.getItemList();
		assertNotNull(resultList);
		
	}
	
	@Test
	public void test_insert() {
		CmsContent c = CmsContentDataFactory.newCmsContent();
		dao.insert(c);
		
	}
	
	@Test
	public void test_update() {
		dao.update(CmsContentDataFactory.newCmsContent());
	}
	
	@Test
	public void test_delete() {
		dao.deleteById(new java.lang.Long("1"));
	}
	
	@Test
	public void test_getById() {
		dao.getById(new java.lang.Long("1"));
	}
	
	@Test
	public void test_getNextCmsContent() {
		Date date = new Date();
		CmsContent nextCmsContent = dao.getNextCmsContent(date,"localhost", 150);
		assertNotNull(nextCmsContent);
		assertTrue(nextCmsContent.getId() > 150);
	}
	
	@Test
	public void test_getPreCmsContent() {
		Date date = new Date();
		CmsContent preCmsContent = dao.getPreCmsContent(date,"localhost", 150);
		assertNotNull(preCmsContent);
		assertTrue(preCmsContent.getId() < 150);
	}
	
	@Test
	public void test_findPage2() {
		Date endDate = new Date();
		Date startDate = DateUtils.addDays(endDate, -30);
		Page<CmsContent> page = dao.findPage(new PageQuery(1, 20), "localhost", "news", new DateRange(startDate, endDate));
		assertNotNull(page);
		assertFalse(page.getItemList().isEmpty());
	}

	@Test
	public void test_findBySiteLike() {
		Date endDate = new Date();
		Date startDate = DateUtils.addDays(endDate, -30);
		Page<CmsContent> page = dao.findBySiteLike(new PageQuery(1, 20), "localhost", "news", new DateRange(startDate, endDate));
		assertNotNull(page);
		assertFalse(page.getItemList().isEmpty());
	}
	
	@Test
	public void test_countByTitle() {
		test_insert();
		int result = dao.countByTitle(DateUtils.addDays(new Date(),-20), new Date(), CmsContentDataFactory.newCmsContent().getTitle());
		assertTrue(result == 1);
		
		result = dao.countByTitle(DateUtils.addDays(new Date(),-20), new Date(), "not exist title 1111111111111xcsdf");
		assertTrue(result == 0);
	}
	
	@Test
	public void test_countBySearchKeyword() {
		test_insert();
		int result = dao.countBySearchKeyword(DateUtils.addDays(new Date(),-20), new Date(), CmsContentDataFactory.newCmsContent().getSearchKeyword());
		assertTrue(result == 1);
		
		result = dao.countBySearchKeyword(DateUtils.addDays(new Date(),-20), new Date(), "not exist title 1111111111111xcsdf");
		assertTrue(result == 0);
	}
	
	@Test
	public void test_getByDateCreatedAndId() {
		dao.getById(new Date(), 1);
	}
	
	@Test
	public void test_findLastBySite() {
		assertNotNull(dao.findLastBySite("localhost"));
	}
	
	@Test
	public void test_countBySourceUrl() {
		test_insert();
		int result = dao.countBySourceUrl(DateUtils.addDays(new Date(),-20), new Date(), CmsContentDataFactory.newCmsContent().getSearchKeyword());
		assertTrue(result == 1);
		
		result = dao.countBySourceUrl(DateUtils.addDays(new Date(),-20), new Date(), "not_exist_source_url_xxxxxxxxxxx928");
		assertTrue(result == 0);
	}
	
	@Test
	public void test_findFirstByCreatedDay() {
		CmsContent createdDay = dao.findFirstByCreatedDay("localhost", DateConvertUtils.extract(new Date(), "yyyy-MM-dd"));
		assertNotNull(createdDay);
	}
	
	@Test
	public void statSite() {
		List<Map<String,Object>> list = dao.statSite(new DateRange());
		assertNotNull(list);
	}
	
}

