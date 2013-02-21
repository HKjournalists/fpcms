package com.fpcms.home.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpcms.model.CmsDomain;
import com.fpcms.model.CmsSite;
import com.fpcms.service.CmsDomainService;
import com.fpcms.service.CmsSiteService;

@Controller
@RequestMapping("monitor")
public class MonitorController {
	@Autowired(required=true)
	private CmsDomainService cmsDomainService;
	@Autowired(required=true)
	private CmsSiteService cmsSiteService;
	
	@RequestMapping
	public void monitorAllDomain(HttpServletResponse response) throws IOException {
		List<String> failList = new ArrayList();
		for(CmsDomain domain : cmsDomainService.findAll()) {
			if(!domain.isHttpSuccess()) {
				failList.add(domain.getDomain()+"{"+domain.getHttpStatus()+"}");
			}
		}
		
		if(!failList.isEmpty()) {
			 throw new RuntimeException("error domain http status:"+failList);
		}else {
			response.getWriter().println("success");
		}
	}
	
	@RequestMapping
	public void monitorAllSite(HttpServletResponse response) throws IOException {
		List<String> failList = new ArrayList();
		for(CmsSite site : cmsSiteService.findAll()) {
			if(!site.isHttpSuccess()) {
				failList.add(site.getSiteDomain()+":"+site.getHttpStatus());
			}
		}
		
		if(!failList.isEmpty()) {
			 throw new RuntimeException("error site http status:"+failList);
		}else {
			response.getWriter().println("success");
		}
	}
}
