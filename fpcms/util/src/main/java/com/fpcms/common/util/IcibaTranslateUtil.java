package com.fpcms.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class IcibaTranslateUtil {

	public static String autoTranslate(String input) throws UnsupportedEncodingException {
		Map params = new HashMap();
		params.put("q", URLEncoder.encode(input,"UTF-8"));
		params.put("type", "auto");
		String result = NetUtil.httpPost("http://fy.iciba.com/api.php",params);
		return result;
	}
	
}
