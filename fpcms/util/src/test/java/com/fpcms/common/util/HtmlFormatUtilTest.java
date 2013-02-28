package com.fpcms.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;


public class HtmlFormatUtilTest extends Assert{
	
	String result;
	@Test
	public void test() throws FileNotFoundException, IOException {
		result = HtmlFormatUtil.htmlBeauty(StringUtils.repeat("中国人民银行,是个好银行。",100));
		String expected = FileUtils.readFileToString(ResourceUtils.getFile("classpath:html_format_test.txt"));
		assertEquals(expected.replace("\r\n", "").replace("\n", "").trim(),result.replace("\n", "").trim());
	}
	
}
