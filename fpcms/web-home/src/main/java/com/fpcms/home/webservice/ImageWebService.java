package com.fpcms.home.webservice;

import java.io.IOException;

public interface ImageWebService {
	/**
	 * 用于生成 显示美女图片的javascript代码
	 */
	public String[] getFemailImageList() throws IOException;
	
}
