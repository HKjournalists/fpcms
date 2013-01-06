package com.fpcms.home.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpcms.common.util.IpUtil;

@Controller
@RequestMapping("/misc")
public class MiscController {

	@SuppressWarnings("unchecked")
	@RequestMapping
	public void httpHeader(HttpServletRequest req,HttpServletResponse rep) throws IOException {
		ArrayList<String> headers = Collections.list(req.getHeaderNames());
		PrintWriter out = rep.getWriter();
		out.println("ip="+IpUtil.getIpAddr(req));
		for(String name : headers) {
			String value = req.getHeader(name);
			out.println(name+"="+value);
		}
	}
	
	@RequestMapping
	public void ip(HttpServletRequest req,HttpServletResponse rep) throws IOException {
		PrintWriter out = rep.getWriter();
		out.println(IpUtil.getIpAddr(req));
	}
	
}
