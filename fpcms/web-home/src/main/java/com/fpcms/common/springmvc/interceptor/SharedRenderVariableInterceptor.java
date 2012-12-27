package com.fpcms.common.springmvc.interceptor;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.duowan.common.web.httpinclude.HttpInclude;
import com.duowan.common.web.scope.Flash;
import com.fpcms.common.util.Constants;
import com.fpcms.service.CmsPropertyService;

/**
 * 拦截器,用于存放渲染视图时需要的的共享变量
 * @author badqiu
 *
 */
public class SharedRenderVariableInterceptor extends HandlerInterceptorAdapter implements InitializingBean{
	static Logger log = LoggerFactory.getLogger(SharedRenderVariableInterceptor.class);
	
	//系统启动并初始化一次的变量
	private Map<String,Object> globalRenderVariables = new HashMap<String,Object>();
	private CmsPropertyService cmsPropertyService;
	
	
	public void setCmsPropertyService(CmsPropertyService cmsPropertyService) {
		this.cmsPropertyService = cmsPropertyService;
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
		
		String requestHost = getRequestHost(request);
		model.put("requestHost", requestHost);
		model.put("now", new Date());
		model.put("share_current_login_username", "badqiu");
		model.put("ctx", request.getContextPath());
		Flash current = Flash.current();
		model.put("flash", current == null ? new HashMap() : current.getData());
		
		//为freemarker,velocity提供<jsp:include page="/some/page.jsp"/>功能,使用${httpInclude.include("/servlet/header.do")};
		model.put("httpInclude", new HttpInclude(request,response)); 
		
		Map<String,String> properties = cmsPropertyService.findByGroup(Constants.PROPERTY_DEFAULT_GROUP);
		model.putAll(properties);
		return model;
	}

	private static String getRequestHost(HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();
		try {
			return new URL(requestURL).getHost();
		} catch (Exception e) {
			log.error("getRequestHost() error,url:"+requestURL,e);
			return null;
		}
	}

	//用于初始化 sharedRenderVariables, 全局共享变量请尽量用global前缀
	private void initSharedRenderVariables() {
		globalRenderVariables.put("global_system_start_time", new Date());
		
		//也可以存放一些共享的工具类,以便视图使用,如StringUtils
		
	}
	
	//在系统启动时会执行
	public void afterPropertiesSet() throws Exception {
		initSharedRenderVariables();
	}
}
