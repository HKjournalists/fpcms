package com.fpcms.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LogoutController {
	@RequestMapping
	public String logout(String username,String password,HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/admin/login.jsp";
	}
}
