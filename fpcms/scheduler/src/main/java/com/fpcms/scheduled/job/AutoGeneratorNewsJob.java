package com.fpcms.scheduled.job;

import java.util.Map;
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
import com.fpcms.common.random_gen_article.RandomArticle;
import com.fpcms.common.random_gen_article.RandomArticleBuilder;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.model.CmsContent;
import com.fpcms.service.CmsChannelService;
import com.fpcms.service.CmsContentService;
import com.fpcms.service.CmsPropertyService;

@Service
public class AutoGeneratorNewsJob implements InitializingBean{
	static Logger logger = LoggerFactory.getLogger(AutoGeneratorNewsJob.class);

	private CmsContentService cmsContentService;
	private CmsChannelService cmsChannelService;
	private CmsPropertyService cmsPropertyService;
	private ScheduledExecutorService scheduledExecutorService;
	
	public void setCmsContentService(CmsContentService cmsContentService) {
		this.cmsContentService = cmsContentService;
	}

	public void setCmsChannelService(CmsChannelService cmsChannelService) {
		this.cmsChannelService = cmsChannelService;
	}

	public void setCmsPropertyService(CmsPropertyService cmsPropertyService) {
		this.cmsPropertyService = cmsPropertyService;
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
				genCmsContent();
			}catch(Exception e) {
				logger.error("randon gen error",e);
			}
		}
		Profiler.release();
		
		logger.info(Profiler.dump());
	}

	private void genCmsContent() {
		RandomArticleBuilder builder = new RandomArticleBuilder();
		RandomArticle article = builder.buildRandomArticle(null);
		
		String keyword = getAttachKeyword() + getSiteKeyword();
		CmsContent cmsContent = new CmsContent();
		cmsContent.setContent(article.getContent());
		String title = keyword + article.getPerfectKeyword();
		cmsContent.setTitle(title); //TODO 网站:关键字要附加进去
		cmsContent.setAuthor("admin_ramd");
		cmsContent.setChannelCode(Constants.CHANNED_CODE_NEWS);
		cmsContentService.create(cmsContent);
		logger.info("generate random news by finalSearchKeyword:"+article.getFinalSearchKeyword()+",new title:"+title);
	}
	
	private String getAttachKeyword() {
		if(RandomUtil.randomTrue(35)) {
			return "";
		}
		return RandomUtil.randomSelect(Constants.ATTACH_KEYWORD);
	}

	/**
	 * 随机一定概率生成有关键字的title
	 * @return
	 */
	private String getSiteKeyword() {
		Map<String,String> map = cmsPropertyService.findByGroup(Constants.PROPERTY_DEFAULT_GROUP);
		if(map == null) {
			throw new IllegalArgumentException("not found default group");
		}
		String keyword = map.get("keyword") + " ";
		return RandomUtils.nextInt(3) == 1 ? keyword : " ";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
//		Assert.notNull(scheduledExecutorService,"scheduledExecutorService must be not null");
		Assert.notNull(cmsContentService,"cmsContentService must be not null");
		Assert.notNull(cmsChannelService,"cmsChannelService must be not null");
		Assert.notNull(cmsPropertyService,"cmsPropertyService must be not null");
		
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

}
