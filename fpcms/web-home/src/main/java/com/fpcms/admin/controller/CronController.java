package com.fpcms.admin.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.github.rapid.common.web.scope.Flash;
import com.fpcms.common.util.ApplicationContextUtil;
import com.fpcms.scheduled.job.BaseCronJob;

/**
 * 用于手工调定时任务
 * 
 * @author badqiu
 *
 */
@Controller
@RequestMapping("/admin/cron")
public class CronController {
	@Autowired(required=true)
	private ServletContext servletContext;
	
	@RequestMapping
	public String index(ModelMap model,HttpServletRequest request){
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		List<BaseCronJob> cronJobs = ApplicationContextUtil.getBeans(wac, BaseCronJob.class);
		model.put("cronJobs", cronJobs);
		return "/admin/cron/index";
	}
	
	@RequestMapping
	public String exec(ModelMap model,String jobName){
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		List<BaseCronJob> cronJobs = ApplicationContextUtil.getBeans(wac, BaseCronJob.class);
		for(BaseCronJob job : cronJobs) {
			if(job.getClass().getName().equals(jobName)) {
				job.execute();
				Flash.current().success("执行任务:"+jobName+" 成功,任务描述:"+job.getJobRemark());
				return "redirect:/admin/cron/index.do";
			}
		}
		Flash.current().error("没有找到任务:"+jobName);
		return "redirect:/admin/cron/index.do";
	}
}
