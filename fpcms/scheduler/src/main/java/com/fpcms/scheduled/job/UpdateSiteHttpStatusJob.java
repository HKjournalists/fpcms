package com.fpcms.scheduled.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fpcms.service.CmsSiteService;
@Service
public class UpdateSiteHttpStatusJob extends BaseCronJob implements InitializingBean{
	static Logger logger = LoggerFactory.getLogger(UpdateCmsSiteJob.class);
	
	private CmsSiteService cmsSiteService;

	public UpdateSiteHttpStatusJob() {
		super("0 1/20 * * * *");
	}
	
	public void setCmsSiteService(CmsSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}

	public synchronized void execute() {
		cmsSiteService.updateHttpStatus();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cmsSiteService,"cmsSiteService must be not null");
		super.afterPropertiesSet();
	}
	
	@Override
	public String getJobRemark() {
		return "更新CmsSite网站http状态";
	}
	
}
