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

import com.duowan.common.util.DateConvertUtils;
import com.duowan.common.util.Profiler;
import com.fpcms.common.util.SpringContext;
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
		Profiler.start("AutoGeneratorNewsJob.execute");
		try {
			cmsContentService.genRandomCmsContent();
		}finally {
			Profiler.release();
		}
		
		logger.info(Profiler.dump());
	}


	@Override
	public void afterPropertiesSet() throws Exception {
//		Assert.notNull(scheduledExecutorService,"scheduledExecutorService must be not null");
		Assert.notNull(cmsContentService,"cmsContentService must be not null");
		
		scheduledExecutorService = Executors.newScheduledThreadPool(2);
		int period = 6 * 60;
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					int IGNORE_HOUR = 7;
					if(new Date().getHours() <= IGNORE_HOUR) {
						logger.info("ignore execute AutoGeneratorNewsJob by hours < "+IGNORE_HOUR);
						return;
					}
					execute();
				}catch(Exception e) {
					logger.error("AutoGeneratorNewsJob error",e);
				}
			}
		},30,period,TimeUnit.MINUTES);
		logger.info("scheduled "+AutoGeneratorNewsJob.class.getSimpleName());
	}
	
	//FIXME 线程会卡住，因为线程池的问题
	public static void main(String[] args) {
		System.out.println(new Date().getHours());
		System.out.println(DateConvertUtils.parse("2012-10-10 22:10:10", "yyyy-MM-dd HH:mm:ss").getHours());
//		AutoGeneratorNewsJob job = SpringContext.getBean(AutoGeneratorNewsJob.class);
//		job.execute();
	}

}
