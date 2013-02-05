package com.fpcms.scheduled.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.fpcms.service.CmsSiteService;

/**
 * 更新cms_site的搜索引擎记录的Job
 * 
 * @author badqiu
 *
 */
@Service
public class UpdateCmsSiteJob extends BaseCronJob implements InitializingBean{
	static Logger logger = LoggerFactory.getLogger(UpdateCmsSiteJob.class);
	
	private CmsSiteService cmsSiteService;

	public UpdateCmsSiteJob() {
		super("0 1 7,12,18 * * *");
	}
	
	public void setCmsSiteService(CmsSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}

	public void execute() {
		cmsSiteService.updateSearchEngineRecord();
		cmsSiteService.updateSearchEngineKeywordMaxRank();
		cmsSiteService.updateHttpStatus();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cmsSiteService,"cmsSiteService must be not null");
		super.afterPropertiesSet();
	}
	
	@Override
	public String getJobRemark() {
		return "更新CmsSite网站状态";
	}
}
