package com.fpcms.home.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proxy")
public class ProxyController {
	static Logger logger = LoggerFactory.getLogger(ProxyController.class);
	
	@RequestMapping
	public void redirect(String url,HttpServletResponse response) throws IOException {
//		logger.info("redirect:"+url);
		response.sendRedirect(url);
	}
	
//	@RequestMapping("/redirect/{url}.do")
//	public void redirect2(@PathVariable("url") String url,HttpServletResponse response) throws IOException {
//		logger.info("redirect:"+url);
//		response.sendRedirect(url);
//	}
	
}
