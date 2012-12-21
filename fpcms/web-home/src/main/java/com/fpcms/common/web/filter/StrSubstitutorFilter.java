package com.fpcms.common.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.shiro.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fpcms.common.util.FreemarkerUtil;
import com.fpcms.common.util.StrSubstitutorUtil;
import com.fpcms.service.CmsPropertyService;

public class StrSubstitutorFilter extends OncePerRequestFilter implements Filter {

	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	private Set<String> includeSet = new HashSet<String>();

	@Override
	protected void initFilterBean() throws ServletException {
		String includes = getFilterConfig().getInitParameter("includes");
		if (StringUtils.hasText(includes)) {
			for (String str : StringUtils.tokenizeToStringArray(includes, ", \t\n")) {
				includeSet.add(str);
			}
			logger.info("StrSubstitutorFilter includeSet:" + includeSet);
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (isInclude(request) && isNotExclude(request)) {
			FreemarkerServletResponseWrapper respWrapper = new FreemarkerServletResponseWrapper(
					response);
			filterChain.doFilter(request, respWrapper);
			respWrapper.flushBuffer();
		} else {
			filterChain.doFilter(request, response);
		}
	}

	private boolean isNotExclude(HttpServletRequest request) {
		return true;
	}

	private boolean isInclude(HttpServletRequest request) {
		String path = request.getServletPath();
		for(String pattern : includeSet) {
			if( antPathMatcher.match(pattern, path)) {
				return true;
			}
		}
		return false;
	}

	class FreemarkerServletResponseWrapper extends HttpServletResponseWrapper {

		private ServletOutputStream outputStream;
		private ByteArrayOutputStream cacheOutput = new ByteArrayOutputStream(
				1024 * 24);

		FreemarkerServletResponseWrapper(HttpServletResponse resp)
				throws IOException {
			super(resp);
		}

		public void flushBuffer() throws IOException {
			if (writer != null) {
				writer.flush();
			}

			outputStream = getResponse().getOutputStream();
			String charset = getCharacterEncoding();
			String replaced = StrSubstitutorUtil.strSubstitutor(cacheOutput.toString(charset), getVariables());
			outputStream.write((replaced).getBytes());
			outputStream.flush();

		}

		private Map getVariables() {
			final Map map = new HashMap();
			map.putAll(System.getProperties());
			map.put("env", System.getenv());
			map.putAll(getAllCmsProperty());
			return map;
		}

		private Map<String, Map<String, String>> getAllCmsProperty() {
			WebApplicationContext wac = WebApplicationContextUtils
					.getRequiredWebApplicationContext(getServletContext());
			CmsPropertyService cmdPropertyService = wac
					.getBean(CmsPropertyService.class);
			Map<String, Map<String, String>> appProperties = cmdPropertyService
					.findAllGroup();
			return appProperties;
		}

		public ServletOutputStream getOutputStream() throws IOException {
			if (cacheOutput == null) {
				cacheOutput = new ByteArrayOutputStream(1024 * 24);
			}
			return new ServletOutputStreamProxy(cacheOutput);
		}

		private PrintWriter writer = null;

		public PrintWriter getWriter() throws IOException {
			if (writer == null) {
				writer = new PrintWriter(new OutputStreamWriter(
						getOutputStream(), getCharacterEncoding())) {
					public void write(char[] cb, int off, int len) {
						super.write(cb, off, len);
						super.flush();
					}
				};
			}
			return writer;
		}

	}

	static class ServletOutputStreamProxy extends ServletOutputStream {
		private OutputStream proxy;

		public ServletOutputStreamProxy(OutputStream proxy) {
			super();
			this.proxy = proxy;
		}

		/* The first three methods must be overwritten */
		@Override
		public void write(int b) throws IOException {
			proxy.write(b);
		}

		@Override
		public void flush() throws IOException {
			proxy.flush();
		}

		@Override
		public void close() throws IOException {
			proxy.close();
		}

		/*
		 * These two are not absolutely needed. They are here simply because
		 * they were overriden by GZIPOutputStream.
		 */
		@Override
		public void write(byte[] b) throws IOException {
			proxy.write(b);
		}

		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			proxy.write(b, off, len);
		}

	}
}
