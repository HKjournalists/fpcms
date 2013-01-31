package com.fpcms.home.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpcms.common.util.SpiderUtil;

@Controller
public class LinkedController {
	
	@RequestMapping("/linked/{number}.do")
	public String linked(@PathVariable("number") String number,HttpServletRequest request) {
		if(SpiderUtil.isSpider(request)) {
			return "forward:/content/last.do";
		}else {
			return "/linked";
		}
	}
	
}
