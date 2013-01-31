package com.fpcms.scheduled.job;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
public class UpdateCmsSiteJob implements InitializingBean{
	static Logger logger = LoggerFactory.getLogger(UpdateCmsSiteJob.class);
	
	private CmsSiteService cmsSiteService;
	private ScheduledExecutorService scheduledExecutorService;
	
	public void setCmsSiteService(CmsSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}

	public void setScheduledExecutorService(
			ScheduledExecutorService scheduledExecutorService) {
		this.scheduledExecutorService = scheduledExecutorService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(cmsSiteService,"cmsSiteService must be not null");
		scheduledExecutorService = Executors.newScheduledThreadPool(2);
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					int curHour = new Date().getHours();
					boolean isRunHour = curHour == 7 || curHour == 11 || curHour == 18;
					if(isRunHour) {
						cmsSiteService.updateSearchEngineRecord();
						cmsSiteService.updateSearchEngineKeywordMaxRank();
						cmsSiteService.updateHttpStatus();
					}else {
						logger.info("ignore execute UpdateCmsSiteJob is not runHour:"+curHour);
					}
				}catch(Exception e) {
					logger.error("UpdateCmsSiteJob error",e);
				}
			}
		},1,1,TimeUnit.HOURS);
		logger.info("scheduled "+UpdateCmsSiteJob.class.getSimpleName());
		Assert.notNull(scheduledExecutorService,"scheduledExecutorService must be not null");
	}
	
}
