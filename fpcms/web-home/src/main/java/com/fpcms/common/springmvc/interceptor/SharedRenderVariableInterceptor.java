package com.fpcms.common.springmvc.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.duowan.common.web.httpinclude.HttpInclude;
import com.duowan.common.web.scope.Flash;
import com.fpcms.common.util.CmsSiteUtil;
import com.fpcms.common.util.URLUtil;
import com.fpcms.model.CmsSitePropertyEnum;
import com.fpcms.service.CmsSiteService;

/**
 * 拦截器,用于存放渲染视图时需要的的共享变量
 * @author badqiu
 *
 */
public class SharedRenderVariableInterceptor extends HandlerInterceptorAdapter implements InitializingBean{
	static Logger log = LoggerFactory.getLogger(SharedRenderVariableInterceptor.class);
	
	//系统启动并初始化一次的变量
	private Map<String,Object> globalRenderVariables = new HashMap<String,Object>();
	private CmsSiteService cmsSiteService;
	
	
	public void setCmsSiteService(CmsSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(modelAndView == null) {
			return;
		}
		
        String viewName = modelAndView.getViewName();
        if(viewName != null && !viewName.startsWith("redirect:")) {
            modelAndView.addAllObjects(globalRenderVariables);
            modelAndView.addAllObjects(perRequest(request,response));
        }
	}
	
	protected Map<String,Object> perRequest(HttpServletRequest request,HttpServletResponse response) {
		HashMap<String,Object> model = new HashMap<String,Object>();
		
		model.put("now", new Date());
		model.put("share_current_login_username", "badqiu");
		model.put("requestHost", URLUtil.getHostSite(request.getRequestURL().toString())); // for wpa.qq.com
		model.put("ctx", request.getContextPath());
		Flash current = Flash.current();
		model.put("uptime", getUptime() );
		
		model.put("flash", current == null ? new HashMap() : current.getData());
		
		//为freemarker,velocity提供<jsp:include page="/some/page.jsp"/>功能,使用${httpInclude.include("/servlet/header.do")};
		model.put("httpInclude", new HttpInclude(request,response)); 
		
		String site = CmsSiteUtil.getSite(request, cmsSiteService);
		if(StringUtils.isNotBlank(site)) {
			Map<String,String> properties = cmsSiteService.getSiteProperties(site);
			model.putAll(properties);
		}
		return model;
	}

	private static long systemStartupTime = System.currentTimeMillis();
	private static double getUptime() {
		return (System.currentTimeMillis() - systemStartupTime) / 1000.0 / 3600 / 24;
	}

	//用于初始化 sharedRenderVariables, 全局共享变量请尽量用global前缀
	private void initSharedRenderVariables() {
		globalRenderVariables.put("systemStartupTime", new Date(systemStartupTime) );
		//也可以存放一些共享的工具类,以便视图使用,如StringUtils
		globalRenderVariables.put(CmsSitePropertyEnum.class.getSimpleName(), CmsSitePropertyEnum.values());
	}
	
	//在系统启动时会执行
	public void afterPropertiesSet() throws Exception {
		initSharedRenderVariables();
	}
}
