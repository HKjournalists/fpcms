/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */


package com.fpcms.admin.controller;

import static com.github.rapid.common.util.ValidationErrorsUtil.convert;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.rapid.common.exception.MessageException;
import com.github.rapid.common.util.page.Page;
import com.github.rapid.common.web.scope.Flash;
import com.fpcms.common.BaseController;
import com.fpcms.common.util.Constants;
import com.fpcms.model.CmsChannel;
import com.fpcms.query.CmsChannelQuery;
import com.fpcms.service.CmsChannelService;


/**
 * [CmsChannel] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 *
 */
@Controller
@RequestMapping("/admin/cmschannel")
public class CmsChannelController extends BaseController{
	
	private CmsChannelService cmsChannelService;
	
	private final String LIST_ACTION = "redirect:/admin/cmschannel/refreshParent.jsp";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setCmsChannelService(CmsChannelService service) {
		this.cmsChannelService = service;
	}
	
	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));  
	}
	   
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	public void init(ModelMap model) {
	}
	
	/** 列表 */
	@RequestMapping()
	public String index(ModelMap model,CmsChannelQuery query,HttpServletRequest request) {
//		Page<CmsChannel> page = this.cmsChannelService.findPage(query);
//		model.addAllAttributes(toModelMap(page, query));
		Assert.hasText(query.getSite(),"site parameter must be not empty");
		model.addAttribute("site", query.getSite());
		return "/admin/cmschannel/index";
	}
	
	/** 显示 */
	@RequestMapping()
	public String show(ModelMap model,String site,long id) throws Exception {
		CmsChannel cmsChannel = (CmsChannel)cmsChannelService.getById(site,id);
		model.addAttribute("cmsChannel",cmsChannel);
		return "/admin/cmschannel/show";
	}

	/** 进入新增 */
	@RequestMapping()
	public String add(ModelMap model,CmsChannel cmsChannel) throws Exception {
		model.addAttribute("cmsChannel",cmsChannel);
		return "/admin/cmschannel/add";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping()
	public String create(ModelMap model,CmsChannel cmsChannel,BindingResult errors) throws Exception {
		try {
			cmsChannelService.create(cmsChannel);
		}catch(ConstraintViolationException e) {
			convert(e, errors);
			return  "/admin/cmschannel/add";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			return  "/admin/cmschannel/add";
		}
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION+"?site="+cmsChannel.getSite();
	}
	
	/** 编辑 */
	@RequestMapping()
	public String edit(ModelMap model,long id,String site) throws Exception {
		CmsChannel cmsChannel = (CmsChannel)cmsChannelService.getById(site,id);
		model.addAttribute("cmsChannel",cmsChannel);
		return "/admin/cmschannel/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping()
	public String update(ModelMap model,String site,long id,CmsChannel cmsChannel,BindingResult errors) throws Exception {
		try {
			cmsChannelService.update(cmsChannel);
		}catch(ConstraintViolationException e) {
			convert(e, errors);
			return  "/admin/cmschannel/edit";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			return  "/admin/cmschannel/edit";
		}
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION+"?site="+cmsChannel.getSite();
	}
	
	@RequestMapping
	public String manageContentList(String site) {
		Assert.hasText(site,"site parameter must be not empty");
		return "/admin/cmschannel/manageContentList";
	}
	
	/** 批量删除 */
	@RequestMapping()
	public String delete(ModelMap model,long id,String site) {
		cmsChannelService.removeById(site,id);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION+"?site="+site;
	}
	
	@RequestMapping
	public void treeXml(String site,HttpServletResponse response,ModelMap model) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		String treeXml = cmsChannelService.createTreeXmlString(site);
		response.getWriter().println(treeXml);
	}
	
}

