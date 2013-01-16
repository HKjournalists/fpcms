package com.fpcms.home.webservice.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.fpcms.common.util.StringHelper;
import com.fpcms.home.webservice.ImageWebService;

public class ImageWebServiceImpl implements ImageWebService{
	/**
	 * 用于生成 显示美女图片的javascript代码
	 */
	public String[] getFemailImageList() throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = ImageWebServiceImpl.class.getResourceAsStream("/img_url.txt");
			List<String> lines = IOUtils.readLines(inputStream);
			
			
			lines = StringHelper.removeEmptyLines(lines);
			
			return lines.toArray(new String[lines.size()]);
		}finally {
			IOUtils.closeQuietly(inputStream);
		}
	}
	
}
