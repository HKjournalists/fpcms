package com.fpcms.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public abstract class AbstractHttpInvokerRequestExecutor  {

	/**
	 * Default content type: "application/x-java-serialized-object"
	 */
	public static final String CONTENT_TYPE_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
//	public static final String CONTENT_TYPE_SERIALIZED_OBJECT = "application/x-java-serialized-object";

	/**
	 * Default timeout value if no HttpClient is explicitly provided.
	 */
	protected static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = (10 * 1000);
	protected static final int DEFAULT_CONNECTION_TIMEOUT_MILLISECONDS = (3 * 1000);
	

	protected static final String HTTP_HEADER_ACCEPT_LANGUAGE = "Accept-Language";

	protected static final String HTTP_HEADER_ACCEPT_ENCODING = "Accept-Encoding";

	protected static final String HTTP_HEADER_CONTENT_ENCODING = "Content-Encoding";

	protected static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";

	protected static final String HTTP_HEADER_CONTENT_LENGTH = "Content-Length";

	protected static final String ENCODING_GZIP = "gzip";

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private String contentType = CONTENT_TYPE_WWW_FORM_URLENCODED;

	private boolean acceptGzipEncoding = true;

	private ClassLoader beanClassLoader;
	
	protected int readTimeout = DEFAULT_READ_TIMEOUT_MILLISECONDS;
	protected int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT_MILLISECONDS;
	private String requestMethod = "GET";

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	/**
	 * Specify the content type to use for sending HTTP invoker requests.
	 * <p>Default is "application/x-java-serialized-object".
	 */
	public void setContentType(String contentType) {
		Assert.notNull(contentType, "'contentType' must not be null");
		this.contentType = contentType;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		if (readTimeout < 0) {
			throw new IllegalArgumentException("timeout must be a non-negative value");
		}
		this.readTimeout = readTimeout;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		if (connectionTimeout < 0) {
			throw new IllegalArgumentException("timeout must be a non-negative value");
		}
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * Return the content type to use for sending HTTP invoker requests.
	 */
	public String getContentType() {
		return this.contentType;
	}

	/**
	 * Set whether to accept GZIP encoding, that is, whether to
	 * send the HTTP "Accept-Encoding" header with "gzip" as value.
	 * <p>Default is "true". Turn this flag off if you do not want
	 * GZIP response compression even if enabled on the HTTP server.
	 */
	public void setAcceptGzipEncoding(boolean acceptGzipEncoding) {
		this.acceptGzipEncoding = acceptGzipEncoding;
	}

	/**
	 * Return whether to accept GZIP encoding, that is, whether to
	 * send the HTTP "Accept-Encoding" header with "gzip" as value.
	 */
	public boolean isAcceptGzipEncoding() {
		return this.acceptGzipEncoding;
	}

}