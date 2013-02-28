package com.fpcms.scheduled.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.duowan.common.util.Profiler;
import com.fpcms.service.CmsContentService;

@Service
public class AutoGeneratorNewsJob extends BaseCronJob implements InitializingBean{
	
	private static Logger logger = LoggerFactory.getLogger(AutoGeneratorNewsJob.class);
	private CmsContentService cmsContentService;

	public AutoGeneratorNewsJob() {
		super("0 1 11 * * *");
	}
	
	public void setCmsContentService(CmsContentService cmsContentService) {
		this.cmsContentService = cmsContentService;
	}

	public synchronized void execute() {
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
		Assert.notNull(cmsContentService,"cmsContentService must be not null");
		super.afterPropertiesSet();
	}
	
	@Override
	public String getJobRemark() {
		return "自动生成随机文章";
	}
	
}
