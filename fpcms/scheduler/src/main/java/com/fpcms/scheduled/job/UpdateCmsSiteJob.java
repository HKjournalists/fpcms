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
		super("45 1 6,11,17,21,23 * * *");
	}
	
	public void setCmsSiteService(CmsSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}

	public synchronized void execute() {
		cmsSiteService.updateSearchEngineRecord();
		cmsSiteService.updateSearchEngineKeywordMaxRank();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cmsSiteService,"cmsSiteService must be not null");
		super.afterPropertiesSet();
	}
	
	@Override
	public String getJobRemark() {
		return "更新百度关键字排名等记录状态";
	}
}
