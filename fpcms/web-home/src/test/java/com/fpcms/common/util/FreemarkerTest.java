package com.fpcms.common.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.tika.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerTest extends Assert{

	@Test
	public void test() throws Exception {
		
		
		Map model = new HashMap();
		model.put("age", 101);
		model.put("username", "badqiu");
		String templateString = "[#if age > 100] hello:@{username} ${username} [/#if]";
		
		Template t = newSyntaxFreemarkerTemplate(new StringReader(templateString),"@{");
		String result = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
		System.out.println(result);
	}
	
	public Template newSyntaxFreemarkerTemplate(Reader reader,String newExpressionSyntax) throws IOException {
		String templateString = IOUtils.toString(reader);
		Configuration conf = new Configuration();
		conf.setTagSyntax(Configuration.SQUARE_BRACKET_TAG_SYNTAX);
//		templateString = StringUtils.replace("[#assign doller='$']"+templateString, "${", "${doller}{");
		templateString = StringUtils.replace(templateString, "${", "${'$'}{");
		templateString = StringUtils.replace(templateString, "@{", "${");
		Template t = new Template("A",new StringReader(templateString),conf);
		return t;
	}
}
