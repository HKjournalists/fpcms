package com.fpcms.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpcms.model.CmsSite;

@Controller
@RequestMapping("/admin")
public class EchoController {

	@RequestMapping()
	public String echo(ModelMap model,HttpServletRequest request,String username,String pwd,CmsSite site) {
		System.out.println("echo:"+username+" pwd:"+pwd);
		System.out.println("site:"+site);
		
		List<Integer> list = new ArrayList();
		for(int i = 0; i < 10; i++) {
			list.add(i);
		}
		
		model.put("list", list);
		return "/echo";
	}
}
