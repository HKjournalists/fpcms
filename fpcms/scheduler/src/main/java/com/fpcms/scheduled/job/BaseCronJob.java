package com.fpcms.scheduled.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.Assert;

public abstract class BaseCronJob implements InitializingBean{

	private static Logger logger = LoggerFactory.getLogger(BaseCronJob.class);
	private String cron;
	
	
	public BaseCronJob(String cron) {
		setCron(cron);
	}
	
	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public void scheduedCron() throws Exception {
		Assert.hasText(cron,"cron must be not empty on:" + getClass().getName());
		
		ConcurrentTaskScheduler taskScheduler = new ConcurrentTaskScheduler();
		Runnable task = new Runnable() {
			@Override
			public void run() {
				execute0();
			}

		};
		taskScheduler.schedule(task, new CronTrigger(cron));
		logger.info("scheduled with cron:["+cron+"] for \t"+getClass().getSimpleName());
	}
	
	private void execute0() {
		try {
			logger.info("start_execute_cron_job:"+getClass().getSimpleName());
			execute();
		}catch(Exception e) {
			logger.error("execute error",e);
		}
	}
	
	protected abstract void execute();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		scheduedCron();
	}
	
}
