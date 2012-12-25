package com.fpcms.scheduled.job;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duowan.common.util.Profiler;
import com.fpcms.scheduled.job.util.SpringContext;
import com.fpcms.service.CmsContentService;

@Service
public class AutoGeneratorNewsJob implements InitializingBean{
	static Logger logger = LoggerFactory.getLogger(AutoGeneratorNewsJob.class);

	private CmsContentService cmsContentService;
	private ScheduledExecutorService scheduledExecutorService;
	
	public void setCmsContentService(CmsContentService cmsContentService) {
		this.cmsContentService = cmsContentService;
	}

	public void setScheduledExecutorService(
			ScheduledExecutorService scheduledExecutorService) {
		this.scheduledExecutorService = scheduledExecutorService;
	}

	public void execute() {
		logger.info("started execute AutoGeneratorNewsJob");
		int randonCmsContentNum = 1 + RandomUtils.nextInt(5);
		Profiler.start("AutoGeneratorNewsJob.execute",randonCmsContentNum);
		for(int i = 0; i < randonCmsContentNum; i++) {
			try {
				cmsContentService.genRandomCmsContent();
			}catch(Exception e) {
				logger.error("randon gen error",e);
			}
		}
		Profiler.release();
		
		logger.info(Profiler.dump());
	}


	@Override
	public void afterPropertiesSet() throws Exception {
//		Assert.notNull(scheduledExecutorService,"scheduledExecutorService must be not null");
		Assert.notNull(cmsContentService,"cmsContentService must be not null");
		
		scheduledExecutorService = Executors.newScheduledThreadPool(5);
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					execute();
				}catch(Exception e) {
					logger.error("AutoGeneratorNewsJob error",e);
				}
			}
		},0,24,TimeUnit.HOURS);
		logger.info("scheduled AutoGeneratorNewsJob");
	}
	
	//FIXME 线程会卡住，因为线程池的问题
	public static void main(String[] args) {
		AutoGeneratorNewsJob job = SpringContext.getBean(AutoGeneratorNewsJob.class);
		job.execute();
	}

}
