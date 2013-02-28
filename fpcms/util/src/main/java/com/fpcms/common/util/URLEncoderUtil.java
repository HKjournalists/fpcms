package com.fpcms.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class URLEncoderUtil {

	public static String encode(final String str) {
		try {
			return URLEncoder.encode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UnsupportedEncodingException,str="+str,e);
		}
	}
	
}
