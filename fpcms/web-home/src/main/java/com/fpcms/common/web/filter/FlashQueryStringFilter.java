package com.fpcms.common.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.github.rapid.common.web.scope.Flash;
import com.fpcms.common.util.Constants;

public class FlashQueryStringFilter extends OncePerRequestFilter implements Filter{

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Flash current = Flash.current();
		current.put(Constants.QUERY_STRING,current.get(Constants.QUERY_STRING));
		filterChain.doFilter(request, response);
	}

}
