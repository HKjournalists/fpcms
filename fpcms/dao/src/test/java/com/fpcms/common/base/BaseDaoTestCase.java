package com.fpcms.common.base;


import static junit.framework.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Before;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.util.ResourceUtils;

import com.duowan.common.test.dbunit.DBUnitFlatXmlHelper;

/**
 * 本基类主要为子类指定好要装载的spring配置文件
 * 及在运行测试前通过dbunit插入测试数据在数据库中,运行完测试删除测试数据 
 *
 * @author badqiu
 */
public class BaseDaoTestCase {
	
}
