package com.fpcms.scheduled.job;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath*:/spring/*.xml"})  
public class AutoGeneratorNewsJobTest extends AbstractJUnit4SpringContextTests{
//public class AutoGeneratorNewsJobTest extends AbstractTransactionalJUnit4SpringContextTests{
	@Autowired
	private AutoGeneratorNewsJob autoGeneratorNewsJob;
	
	public AutoGeneratorNewsJobTest() {
	}
	@Test
	public void test() {
		autoGeneratorNewsJob.execute();
	}
}
