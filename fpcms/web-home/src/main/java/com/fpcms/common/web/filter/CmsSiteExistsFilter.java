package com.fpcms.common.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fpcms.common.util.CmsSiteUtil;
import com.fpcms.service.CmsSiteService;

/**
 * 过滤网站是否存在的filter
 * 
 * @author badqiu
 * 
 */
public class CmsSiteExistsFilter extends OncePerRequestFilter implements Filter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		CmsSiteService cmsSiteService = wac.getBean(CmsSiteService.class);
		String site = CmsSiteUtil.getSite(request, cmsSiteService);
		if(site == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden,Site Not Exists by CmsSiteExistsFilter");
			return;
		}
		filterChain.doFilter(request, response);
	}

}
