package com.fpcms.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrLookup;
import org.apache.commons.lang.text.StrSubstitutor;

import com.github.rapid.common.beanutils.PropertyUtils;
import com.github.rapid.common.io.freemarker.FreemarkerInputStream;

public class FreemarkerUtil {

	public static String processByFreemarker(String content,String charset,Map variables) {
		try {
			FreemarkerInputStream input = new FreemarkerInputStream(new ByteArrayInputStream(content.getBytes("UTF-8")),charset,variables);
			return IOUtils.toString(input,"UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("process content error,content="+content,e);
		}
	}
	
	public static String readFreemarkerClassPathResource(String classpath,Map<String,Object> params)  {
		InputStream input = BlogPingUtil.class.getResourceAsStream(classpath);
		FreemarkerInputStream finput = new FreemarkerInputStream(input,params);
		try {
			return IOUtils.toString(finput,"UTF-8");
		}catch(IOException e) {
			throw new RuntimeException("error on readFreemarkerResource:"+classpath+" params:"+params,e);
		}finally {
			IOUtils.closeQuietly(input);
		}
	}
	
}
