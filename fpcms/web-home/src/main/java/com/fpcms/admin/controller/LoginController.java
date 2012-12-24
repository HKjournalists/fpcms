package com.fpcms.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpcms.common.util.Constants;
import com.fpcms.model.SysUser;
import com.fpcms.service.SysUserService;

@Controller
@RequestMapping("/admin")
public class LoginController {
	private SysUserService sysUserService;
	
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	@RequestMapping
	public String login(String username,String password,HttpServletRequest request){
		SysUser sysUser = sysUserService.authUser(username,password);
		request.getSession().setAttribute(Constants.ADMIN_LOGIN_USER, sysUser.getUsername());
		return "redirect:/admin/index.jsp";
	}
	
}
