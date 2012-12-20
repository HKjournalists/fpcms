package com.fpcms.common.web.filter;

import java.io.*;
import java.util.*;
import java.util.zip.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GzipFilter implements Filter {
	private static final Log log = LogFactory.getLog(GzipFilter.class);

	public void init(FilterConfig cfg) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		if (isSupportsGzip(req)) {
			HttpServletResponse httpResp = (HttpServletResponse) resp;
			// This does NOT work for Tomcat!
			// Tomcat JSP does not close the output stream upon finishing, so we
			// have to close it explicitly.
			// chain.doFilter(req, new GZIPServletResponseWrapper(httpResp));
			HttpServletResponseWrapper wrapper = new GZIPServletResponseWrapper(httpResp);
			chain.doFilter(req, wrapper);
		} else {
			chain.doFilter(req, resp);
		}
	}

	private boolean isSupportsGzip(ServletRequest req) {
		boolean supportsGzip = false;
		Enumeration e = ((HttpServletRequest) req).getHeaders("Accept-Encoding");
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			if (StringUtils.contains(name,"gzip")) {
				supportsGzip = true;
				break;
			}
		}
		return supportsGzip;
	}

	class GZIPServletResponseWrapper extends HttpServletResponseWrapper {
		private GZIPOutputStream gzipStream;
		private ServletOutputStream servletOutputStream;
		private PrintWriter printWriter;

		GZIPServletResponseWrapper(HttpServletResponse resp) throws IOException {
			super(resp);
		}

		public ServletOutputStream getOutputStream() throws IOException {
			if (servletOutputStream == null) {
				servletOutputStream = createOutputStream();
			}
			return servletOutputStream;
		}

		public PrintWriter getWriter() throws IOException {
			if (printWriter == null) {
				printWriter = new PrintWriter(new OutputStreamWriter(
						getOutputStream(), getCharacterEncoding())) { // This is
																		// important
																		// for
																		// I18N
					// Workaround for Tomcat bug where flush is NOT called when
					// JSP output finished
					public void write(char[] cb, int off, int len) {
						super.write(cb, off, len);
						super.flush();
					}
				};
			}
			return printWriter;
		}

		private ServletOutputStream createOutputStream() throws IOException {
			ServletResponse resp = this.getResponse();
			gzipStream = new GZIPOutputStream(resp.getOutputStream());
			addHeader("Content-Encoding", "gzip");
			addHeader("Vary", "Accept-Encoding");
			return new ServletOutputStream() {
				/* The first three methods must be overwritten */
				@Override
				public void write(int b) throws IOException {
					gzipStream.write(b);
				}

				@Override
				public void flush() throws IOException {
					gzipStream.flush();
				}

				@Override
				public void close() throws IOException {
					gzipStream.close();
				}

				/*
				 * These two are not absolutely needed. They are here simply
				 * because they were overriden by GZIPOutputStream.
				 */
				@Override
				public void write(byte[] b) throws IOException {
					gzipStream.write(b);
				}

				@Override
				public void write(byte[] b, int off, int len)
						throws IOException {
					gzipStream.write(b, off, len);
				}
			};
		}
	}
}