package com.fpcms.home.webservice.impl;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.fpcms.home.webservice.ImageWebService;


public class ImageWebServiceImplTest extends Assert{

	ImageWebService ws = new ImageWebServiceImpl();
	
	@Test
	public void test() throws IOException {
		String[] lines = ws.getFemailImageList();
		assertNotNull(lines);
		for(String line : lines) {
			System.out.println(line);
		}
	}
	
}
