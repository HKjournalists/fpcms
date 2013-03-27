package com.fpcms.scheduled.job;

import java.util.ArrayList;
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
import com.fpcms.common.util.BlogPingUtil;
import com.fpcms.common.util.BlogUtil;
import com.fpcms.common.util.Constants;
import com.fpcms.common.util.RandomUtil;
import com.fpcms.common.util.ThreadUtil;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;
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
		super("0 1 * * * *");
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
		
		for(int i = 0; i < Math.floor(blogExternalList.size() * 5 / 24); i++) {
			BlogExternal be = RandomUtil.randomSelect(blogExternalList);
			postBlog(blogExternalList, be);
		}
		
	}

	private void postBlog(List<BlogExternal> blogExternalList, BlogExternal be) {
		if(!be.isEnabled()) {
			return;
		}
		String blogUrl = be.getBlogUrl();
		try {
			for(int retry = 0; retry < 10; retry++) {
				CmsContent cc = findCmsContent();
				if(cc == null) continue;
				
				String blogContent = buildBlogContent(blogExternalList, cc);
				
				blogExternalService.postNewBlog(be,new Blog(cc.getTitle(),blogContent));
				
				pingLastNewBlog(blogUrl);
				break;
			}
			ThreadUtil.sleep(1000 * 3);
		}catch(Exception e) {
			logger.error("error_postNewBlog_on:"+blogUrl+" by_username:"+be.getUsername(),e);
		}
	}

	private static void pingLastNewBlog(String blogUrl) {
		List<Anchor> blogLinkList = BlogUtil.getValidBlogLinks(blogUrl, 8);
		for(int i = 0; i < blogLinkList.size() && i < 2; i++) {
			Anchor a = RandomUtil.randomRemove(blogLinkList);
			logger.info("pingLastNewBlog,anchor:"+a);
			BlogPingUtil.baiduPing(blogUrl, blogUrl, a.getHref(), "");
		}
	}

	private String buildBlogContent(List<BlogExternal> blogExternalList,
			CmsContent cc) {
		String blogContent = "原文请查看:" + cc.getUrl() + "\n<br /> "+cmsDomainService.insertRandomLinks(cc.getContent(),2) +" <br/>\n" ;
		if(RandomUtil.randomTrue(100)) {
			try {
				BlogExternal randomBe = RandomUtil.randomSelect(getByHasTag(blogExternalList,"needAd"));
				
				List<Anchor> validBlogLinks = BlogUtil.getValidBlogLinks(randomBe.getBlogUrl(),8);
				Anchor randomBlogLink = RandomUtil.randomSelect(validBlogLinks);
				blogContent += "<br/> "+String.valueOf(randomBlogLink)+" ; ";
			}catch(Exception e) {
				logger.error("error_on_insert_randomBlogLink into blog:"+cc.getId()+" title:"+cc.getTitle(),e);
			}
		}
		
		return blogContent;
	}

	private static List<BlogExternal> getByHasTag(List<BlogExternal> blogExternalList,
			String tag) {
		List<BlogExternal> result = new ArrayList<BlogExternal>();
		for(BlogExternal be : blogExternalList) {
			if(be.getTagSet().contains(tag)) {
				result.add(be);
			}
		}
		return result;
	}

	private CmsContent findCmsContent() {
		DateRange createdRange = new DateRange(DateUtils.addDays(new Date(),-40),DateUtils.addDays(new Date(),-30));
		CmsDomain domain = cmsDomainService.randomSelectDomain();
		Page<CmsContent> page = cmsContentService.findPage(new PageQuery(100), "www."+domain.getDomain(), Constants.CHANNED_CODE_NEWS, createdRange);
		return RandomUtil.randomSelect(page.getItemList());
	}

}
