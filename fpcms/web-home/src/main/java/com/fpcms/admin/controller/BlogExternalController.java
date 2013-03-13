/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2013
 */


package com.fpcms.admin.controller;

import static com.duowan.common.util.ValidationErrorsUtils.convert;

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

import com.duowan.common.exception.MessageException;
import com.duowan.common.util.page.Page;
import com.duowan.common.web.scope.Flash;
import com.fpcms.common.BaseController;
import com.fpcms.model.BlogExternal;
import com.fpcms.query.BlogExternalQuery;
import com.fpcms.service.BlogExternalService;


/**
 * [BlogExternal] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 *
 */
@Controller
@RequestMapping("/admin/blogexternal")
public class BlogExternalController extends BaseController{
	
	private BlogExternalService blogExternalService;
	
	private final String LIST_ACTION = "redirect:/admin/blogexternal/index.do";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setBlogExternalService(BlogExternalService service) {
		this.blogExternalService = service;
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
	public String index(ModelMap model,BlogExternalQuery query,HttpServletRequest request) {
		Page<BlogExternal> page = this.blogExternalService.findPage(query);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/blogexternal/index";
	}
	
	/** 显示 */
	@RequestMapping
	public String show(ModelMap model,@RequestParam("blogUrl") String blogUrl, @RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
		BlogExternal blogExternal = (BlogExternal)blogExternalService.getById(blogUrl,username,password);
		model.addAttribute("blogExternal",blogExternal);
		return "/admin/blogexternal/show";
	}

	/** 进入新增 */
	@RequestMapping
	public String add(ModelMap model,BlogExternal blogExternal) throws Exception {
		model.addAttribute("blogExternal",blogExternal);
		return "/admin/blogexternal/add";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping
	public String create(ModelMap model,BlogExternal blogExternal,BindingResult errors) throws Exception {
		try {
			blogExternalService.create(blogExternal);
		}catch(ConstraintViolationException e) {
			convert(e, errors);
			return  "/admin/blogexternal/add";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			return  "/admin/blogexternal/add";
		}
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping
	public String edit(ModelMap model,@RequestParam("blogUrl") String blogUrl, @RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
		BlogExternal blogExternal = (BlogExternal)blogExternalService.getById(blogUrl,username,password);
		model.addAttribute("blogExternal",blogExternal);
		return "/admin/blogexternal/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping
	public String update(ModelMap model,@RequestParam("blogUrl") String blogUrl, @RequestParam("username") String username, @RequestParam("password") String password,BlogExternal blogExternal,BindingResult errors) throws Exception {
		try {
			blogExternalService.update(blogExternal);
		}catch(ConstraintViolationException e) {
			convert(e, errors);
			return  "/admin/blogexternal/edit";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			return  "/admin/blogexternal/edit";
		}
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 批量删除 */
	@RequestMapping
	public String delete(ModelMap model,@RequestParam("blogUrl") String blogUrl, @RequestParam("username") String username, @RequestParam("password") String password) {
		blogExternalService.removeById(blogUrl,username,password);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** testPostBlog*/
	@RequestMapping
	public String testPostBlog(ModelMap model,@RequestParam("blogUrl") String blogUrl, @RequestParam("username") String username, @RequestParam("password") String password) {
		Flash.current().success("发送成功");
		return LIST_ACTION;
	}	
	
}

