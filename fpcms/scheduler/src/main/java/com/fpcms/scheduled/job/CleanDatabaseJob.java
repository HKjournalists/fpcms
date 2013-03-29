package com.fpcms.scheduled.job;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fpcms.service.CmsKeyValueService;

/**
 * 清理数据库过期数据
 * @author badqiu
 *
 */
@Service
public class CleanDatabaseJob extends BaseCronJob implements InitializingBean{

	private CmsKeyValueService cmsKeyValueService;
	
	public CleanDatabaseJob() {
		super("0 1 3 * * *");
	}
	
	public void setCmsKeyValueService(CmsKeyValueService cmsKeyValueService) {
		this.cmsKeyValueService = cmsKeyValueService;
	}

	@Override
	public synchronized void executeInternal() {
		cmsKeyValueService.deleteBy(DateUtils.addDays(new Date(),-180));
	}
	
	@Override
	public String getJobRemark() {
		return "清理数据库过期数据";
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		Assert.notNull(cmsKeyValueService,"cmsKeyValueService must be not null");
	}
}
