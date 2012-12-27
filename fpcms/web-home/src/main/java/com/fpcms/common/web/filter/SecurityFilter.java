package com.fpcms.common.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.fpcms.common.util.AppModeUtil;
import com.fpcms.common.util.Constants;

public class SecurityFilter extends BaseIncludeExcludeFilter implements Filter{

	private String loginPage = "/admin/login.jsp";
	
	@Override
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
		excludeSet.add(loginPage);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(AppModeUtil.hasDevPassword()) {
			filterChain.doFilter(request, response);
			return;
		}
		if(!isMatch(request, excludeSet)) {
			String username = (String)request.getSession().getAttribute(Constants.ADMIN_LOGIN_USER);
			if(StringUtils.isBlank(username)) {
				response.sendRedirect(request.getContextPath()+loginPage);
				return;
			}
			filterChain.doFilter(request, response);
		}else {
			filterChain.doFilter(request, response);
		}
	}

}
