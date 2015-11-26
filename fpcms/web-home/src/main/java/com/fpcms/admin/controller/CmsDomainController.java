/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */


package com.fpcms.admin.controller;

import static com.github.rapid.common.util.ValidationErrorsUtil.convert;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.fpcms.model.CmsDomain;
import com.fpcms.query.CmsDomainQuery;
import com.fpcms.service.CmsDomainService;


/**
 * [CmsDomain] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 *
 */
@Controller
@RequestMapping("/admin/cmsdomain")
public class CmsDomainController extends BaseController{
	
	private CmsDomainService cmsDomainService;
	
	private final String LIST_ACTION = "redirect:/admin/cmsdomain/index.do";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setCmsDomainService(CmsDomainService service) {
		this.cmsDomainService = service;
	}
	
	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));  
	}
	   
	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	public void init(ModelMap model) {
	}
	
	/** 列表 */
	@RequestMapping
	public String index(ModelMap model,CmsDomainQuery query,HttpServletRequest request) {
		query.setPageSize(Math.max(100, query.getPageSize()));
		
		Page<CmsDomain> page = this.cmsDomainService.findPage(query);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/cmsdomain/index";
	}
	
	/** 显示 */
	@RequestMapping
	public String show(ModelMap model,@RequestParam("domain") String domain) throws Exception {
		CmsDomain cmsDomain = (CmsDomain)cmsDomainService.getById(domain);
		model.addAttribute("cmsDomain",cmsDomain);
		return "/admin/cmsdomain/show";
	}

	/** 进入新增 */
	@RequestMapping
	public String add(ModelMap model,CmsDomain cmsDomain) throws Exception {
		model.addAttribute("cmsDomain",cmsDomain);
		return "/admin/cmsdomain/add";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping
	public String create(ModelMap model,CmsDomain cmsDomain,BindingResult errors) throws Exception {
		try {
			cmsDomainService.create(cmsDomain);
		}catch(ConstraintViolationException e) {
			convert(e, errors);
			return  "/admin/cmsdomain/add";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			return  "/admin/cmsdomain/add";
		}
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping
	public String edit(ModelMap model,@RequestParam("domain") String domain) throws Exception {
		CmsDomain cmsDomain = (CmsDomain)cmsDomainService.getById(domain);
		model.addAttribute("cmsDomain",cmsDomain);
		return "/admin/cmsdomain/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping
	public String update(ModelMap model,@RequestParam("domain") String domain,CmsDomain cmsDomain,BindingResult errors) throws Exception {
		try {
			cmsDomainService.update(cmsDomain);
		}catch(ConstraintViolationException e) {
			convert(e, errors);
			return  "/admin/cmsdomain/edit";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			return  "/admin/cmsdomain/edit";
		}
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 批量删除 */
	@RequestMapping
	public String delete(ModelMap model,@RequestParam("domain") String domain) {
		cmsDomainService.removeById(domain);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}

	/** 显示列表用于签名 */
	@RequestMapping
	public String signature(ModelMap model,CmsDomainQuery query,HttpServletRequest request) {
		model.put("cmsDomainList", cmsDomainService.findAll());
		return "/admin/cmsdomain/signature";
	}
	
}

