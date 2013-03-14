package com.fpcms.scheduled.job;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.duowan.common.util.DateRange;
import com.duowan.common.util.page.Page;
import com.duowan.common.util.page.PageQuery;
import com.fpcms.common.blog_post.Blog;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.model.BlogExternal;
import com.fpcms.model.CmsContent;
import com.fpcms.model.CmsDomain;
import com.fpcms.service.BlogExternalService;
import com.fpcms.service.CmsContentService;
import com.fpcms.service.CmsDomainService;
@Service
public class ReproducedBlog2ExternalJob extends BaseCronJob{
	private static Logger logger = LoggerFactory.getLogger(ReproducedBlog2ExternalJob.class);
	
	private CmsDomainService cmsDomainService;
	private CmsContentService cmsContentService;
	private BlogExternalService blogExternalService;
	
	public ReproducedBlog2ExternalJob() {
		super("0 1 3,6 * * *");
	}
	
	public void setCmsDomainService(CmsDomainService cmsDomainService) {
		this.cmsDomainService = cmsDomainService;
	}

	public void setCmsContentService(CmsContentService cmsContentService) {
		this.cmsContentService = cmsContentService;
	}

	public void setBlogExternalService(BlogExternalService blogExternalService) {
		this.blogExternalService = blogExternalService;
	}

	@Override
	public void execute() {
		List<BlogExternal> blogExternalList = blogExternalService.findAll();
		for(BlogExternal be : blogExternalList) {
			if(!be.isEnabled()) {
				continue;
			}
			try {
				for(int retry = 0; retry < 10; retry++) {
					CmsContent cc = findCmsContent();
					if(cc == null) continue;
					
					String blogContent = "原文请查看:" + cc.getUrl() + "\n<br /> "+cc.getContent() +" <br/>\n" ;
					blogExternalService.postNewBlog(be,new Blog(cc.getTitle(),blogContent));
					break;
				}
			}catch(Exception e) {
				logger.error("error_postNewBlog_on:"+be.getBlogUrl()+" by_username:"+be.getUsername(),e);
			}
		}
	}

	private CmsContent findCmsContent() {
		DateRange createdRange = new DateRange(DateUtils.addDays(new Date(),-10),DateUtils.addDays(new Date(),-5));
		CmsDomain domain = cmsDomainService.randomSelectDomain();
		Page<CmsContent> page = cmsContentService.findPage(new PageQuery(100), "www."+domain.getDomain(), Constants.CHANNED_CODE_NEWS, createdRange);
		return RandomUtil.randomSelect(page.getItemList());
	}

}
