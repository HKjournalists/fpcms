/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
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
import com.fpcms.model.CmsProperty;
import com.fpcms.query.CmsPropertyQuery;
import com.fpcms.service.CmsPropertyService;


/**
 * [CmsProperty] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 *
 */
@Controller
@RequestMapping("/admin/cmsproperty")
public class CmsPropertyController extends BaseController{
	
	private CmsPropertyService cmsPropertyService;
	
	private final String LIST_ACTION = "redirect:/admin/cmsproperty/index.do";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setCmsPropertyService(CmsPropertyService service) {
		this.cmsPropertyService = service;
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
	@RequestMapping(value="/index")
	public String index(ModelMap model,CmsPropertyQuery query,HttpServletRequest request) {
		Page<CmsProperty> page = this.cmsPropertyService.findPage(query);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/cmsproperty/index";
	}
	
	/** 显示 */
	@RequestMapping(value="/show")
	public String show(ModelMap model,@RequestParam("propGroup") String propGroup, @RequestParam("propKey") String propKey) throws Exception {
		CmsProperty cmsProperty = (CmsProperty)cmsPropertyService.getById(propGroup,propKey);
		model.addAttribute("cmsProperty",cmsProperty);
		return "/admin/cmsproperty/show";
	}

	/** 进入新增 */
	@RequestMapping(value="/add")
	public String add(ModelMap model,CmsProperty cmsProperty) throws Exception {
		model.addAttribute("cmsProperty",cmsProperty);
		return "/admin/cmsproperty/add";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/create")
	public String create(ModelMap model,CmsProperty cmsProperty,BindingResult errors) throws Exception {
		try {
			cmsPropertyService.create(cmsProperty);
		}catch(ConstraintViolationException e) {
			convert(e, errors);
			return  "/admin/cmsproperty/add";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			return  "/admin/cmsproperty/add";
		}
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION;
	}
	
	/** 编辑 */
	@RequestMapping(value="/edit")
	public String edit(ModelMap model,@RequestParam("propGroup") String propGroup, @RequestParam("propKey") String propKey) throws Exception {
		CmsProperty cmsProperty = (CmsProperty)cmsPropertyService.getById(propGroup,propKey);
		model.addAttribute("cmsProperty",cmsProperty);
		return "/admin/cmsproperty/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping(value="/update")
	public String update(ModelMap model,@RequestParam("propGroup") String propGroup, @RequestParam("propKey") String propKey,CmsProperty cmsProperty,BindingResult errors) throws Exception {
		try {
			cmsPropertyService.update(cmsProperty);
		}catch(ConstraintViolationException e) {
			convert(e, errors);
			return  "/admin/cmsproperty/edit";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			return  "/admin/cmsproperty/edit";
		}
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION;
	}
	
	/** 批量删除 */
	@RequestMapping(value="/delete")
	public String delete(ModelMap model,@RequestParam("propGroup") String propGroup, @RequestParam("propKey") String propKey) {
		cmsPropertyService.removeById(propGroup,propKey);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION;
	}
	
}

