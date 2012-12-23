package com.fpcms.common.web.filter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class GzipFilter extends OncePerRequestFilter implements Filter{
	private static final Log log = LogFactory.getLog(GzipFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain chain)throws ServletException,IOException {
		if (isSupportsGzip(request)) {
			HttpServletResponse httpResp = (HttpServletResponse) response;
			// This does NOT work for Tomcat!
			// Tomcat JSP does not close the output stream upon finishing, so we
			// have to close it explicitly.
			// chain.doFilter(req, new GZIPServletResponseWrapper(httpResp));
			GZIPServletResponseWrapper wrapper = new GZIPServletResponseWrapper(httpResp);
			chain.doFilter(request, wrapper);
			wrapper.finishResponse();
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean isSupportsGzip(ServletRequest req) {
		Enumeration e = ((HttpServletRequest) req).getHeaders("Accept-Encoding");
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			if (StringUtils.contains(name,"gzip")) {
				return true;
			}
		}
		return false;
	}

	class GZIPServletResponseWrapper extends HttpServletResponseWrapper {
		private GZIPOutputStream gzipStream;
		private ServletOutputStream servletOutputStream;
		private PrintWriter printWriter;

		GZIPServletResponseWrapper(HttpServletResponse resp) throws IOException {
			super(resp);
		}

		@Override
	    public void flushBuffer() throws IOException {  
	    	if(printWriter != null) {
	    		printWriter.flush();
	    	}
	        if (gzipStream != null) {  
	        	gzipStream.flush();  
	        }  
	    }  
	    
		@Override
		public void setContentLength(int length) {  
	    }
		final static String HEADER_CONTENT_LENGTH = "Content-Length";
		@Override
		public void addIntHeader(String name, int value) {
			if(name != null && HEADER_CONTENT_LENGTH.equalsIgnoreCase(name.trim())) {
				return;
			}
			super.addIntHeader(name, value);
		}
		
		@Override
		public void addHeader(String name, String value) {
			if(name != null && HEADER_CONTENT_LENGTH.equalsIgnoreCase(name.trim())) {
				return;
			}
			super.addHeader(name, value);
		}
		
		@Override
		public void addDateHeader(String name, long date) {
			if(name != null && HEADER_CONTENT_LENGTH.equalsIgnoreCase(name.trim())) {
				return;
			}
			super.addDateHeader(name, date);
		}
		
		public void finishResponse() {  
	        try {  
	        	flushBuffer();
	            if (printWriter != null) {  
	            	printWriter.close();  
	            }
	            if (gzipStream != null) {  
	            	gzipStream.close();  
                }  
	        } catch (IOException e) { 
	        	logger.error("close error",e);
	        }  
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