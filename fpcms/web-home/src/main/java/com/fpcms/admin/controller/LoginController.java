package com.fpcms.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpcms.common.util.Constants;
import com.fpcms.common.util.IpUtil;
import com.fpcms.model.SysUser;
import com.fpcms.service.SysUserService;

@Controller
@RequestMapping("/admin")
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	private SysUserService sysUserService;
	
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	@RequestMapping
	public String login(String username,String password,HttpServletRequest request){
		SysUser sysUser = sysUserService.authUser(username,password);
		request.getSession().setAttribute(Constants.ADMIN_LOGIN_USER, sysUser.getUsername());
		logger.info("login success:"+username+" clientIp:"+IpUtil.getIpAddr(request));
		return "redirect:/admin/index.do";
	}
	
}
