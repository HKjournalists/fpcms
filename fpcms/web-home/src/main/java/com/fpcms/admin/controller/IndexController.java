package com.fpcms.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class IndexController {
	
	@RequestMapping
	public String index(String username,String password,HttpServletRequest request){
		return "/admin/index";
	}
	
}
