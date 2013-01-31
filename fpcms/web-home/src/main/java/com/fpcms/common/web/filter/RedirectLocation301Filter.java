package com.fpcms.common.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fpcms.common.util.CmsSiteUtil;
import com.fpcms.common.util.WebUtil;
import com.fpcms.model.CmsSite;
import com.fpcms.service.CmsSiteService;

public class RedirectLocation301Filter  extends OncePerRequestFilter implements Filter{
	private CmsSiteService cmsSiteService;
	
	public void setCmsSiteService(CmsSiteService cmsSiteService) {
		this.cmsSiteService = cmsSiteService;
		Assert.notNull(cmsSiteService,"cmsSiteService must be not null");
	}
	
	@Override
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		setCmsSiteService(wac.getBean(CmsSiteService.class));
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(isNeedSendRedirect(request, response)) {
			return;
		}
		
		filterChain.doFilter(request, response);
	}

	private boolean isNeedSendRedirect(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String site = CmsSiteUtil.getSite(request,cmsSiteService);
		CmsSite cmsSite = cmsSiteService.getById(site);
		if(cmsSite == null) {
			return false;
		}
		
		String redirectSite = cmsSite.getRedirectSite();
		if(StringUtils.isNotBlank(redirectSite)) {
			String queryString = StringUtils.isBlank(request.getQueryString()) ? "" : "?" + request.getQueryString();
			String location = "http://"+redirectSite+request.getRequestURI()+queryString;
//			response.sendRedirect(location);
			WebUtil.send301Redirect(response, location);
			return true;
		}
		return false;
	}

}
