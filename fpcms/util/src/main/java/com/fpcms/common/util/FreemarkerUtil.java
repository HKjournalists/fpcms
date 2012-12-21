package com.fpcms.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrLookup;
import org.apache.commons.lang.text.StrSubstitutor;

import com.duowan.common.beanutils.PropertyUtils;
import com.duowan.common.io.freemarker.FreemarkerInputStream;

public class FreemarkerUtil {

	public static String processByFreemarker(String content,String charset,Map variables) {
		try {
			FreemarkerInputStream input = new FreemarkerInputStream(new ByteArrayInputStream(content.getBytes("UTF-8")),charset,variables);
			return IOUtils.toString(input,"UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("process content error,content="+content,e);
		}
	}
	
}
