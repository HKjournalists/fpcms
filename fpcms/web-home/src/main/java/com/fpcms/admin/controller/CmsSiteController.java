/*
 * Copyright [duowan.com]
 * Web Site: http://www.duowan.com
 * Since 2005 - 2012
 */


package com.fpcms.admin.controller;

import static com.github.rapid.common.util.ValidationErrorsUtil.convert;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang.StringUtils;
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
import com.fpcms.common.util.Constants;
import com.fpcms.model.CmsSite;
import com.fpcms.query.CmsDomainQuery;
import com.fpcms.query.CmsSiteQuery;
import com.fpcms.service.CmsSiteService;


/**
 * [CmsSite] 的业务操作
 * 
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 *
 */
@Controller
@RequestMapping("/admin/cmssite")
public class CmsSiteController extends BaseController{
	String queryString = "queryString";
	
	private CmsSiteService cmsSiteService;
	private final String LIST_ACTION = "redirect:/admin/cmssite/index.do";
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setCmsSiteService(CmsSiteService service) {
		this.cmsSiteService = service;
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
	@RequestMapping()
	public String index(ModelMap model,CmsSiteQuery query,HttpServletRequest request) {
		Flash.current().put(queryString, getRequest().getQueryString());
		query.setPageSize(Math.max(100, query.getPageSize()));
		
		Page<CmsSite> page = this.cmsSiteService.findPage(query);
		
		model.addAllAttributes(toModelMap(page, query));
		return "/admin/cmssite/index";
	}
	
	/** 显示 */
	@RequestMapping()
	public String show(ModelMap model,@RequestParam("siteDomain") String siteDomain) throws Exception {
		CmsSite cmsSite = (CmsSite)cmsSiteService.getById(siteDomain);
		model.addAttribute("cmsSite",cmsSite);
		return "/admin/cmssite/show";
	}

	/** 进入新增 */
	@RequestMapping()
	public String add(ModelMap model,CmsSite cmsSite) throws Exception {
		model.addAttribute("cmsSite",cmsSite);
		return "/admin/cmssite/add";
	}
	
	/** 保存新增,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping()
	public String create(ModelMap model,CmsSite cmsSite,BindingResult errors) throws Exception {
		try {
			cmsSiteService.create(cmsSite);
		}catch(ConstraintViolationException e) {
			convert(e, errors);
			logger.error("create error,cmsSite:"+cmsSite,e);
			return  "/admin/cmssite/add";
		}catch(MessageException e) {
			logger.error("create error,cmsSite:"+cmsSite,e);
			Flash.current().error(e.getMessage());
			return  "/admin/cmssite/add";
		}
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return LIST_ACTION+"?"+Flash.current().get(Constants.QUERY_STRING);
	}
	
	/** 编辑 */
	@RequestMapping()
	public String edit(ModelMap model,@RequestParam("siteDomain") String siteDomain) throws Exception {
		CmsSite cmsSite = (CmsSite)cmsSiteService.getById(siteDomain);
		model.addAttribute("cmsSite",cmsSite);
		return "/admin/cmssite/edit";
	}
	
	/** 保存更新,@Valid标注spirng在绑定对象时自动为我们验证对象属性并存放errors在BindingResult  */
	@RequestMapping()
	public String update(ModelMap model,@RequestParam("siteDomain") String siteDomain,CmsSite cmsSite,BindingResult errors) throws Exception {
		try {
			cmsSiteService.update(cmsSite);
		}catch(ConstraintViolationException e) {
			convert(e, errors);
			logger.error("update error,cmsSite:"+cmsSite,e);
			return  "/admin/cmssite/edit";
		}catch(MessageException e) {
			Flash.current().error(e.getMessage());
			logger.error("update error,cmsSite:"+cmsSite,e);
			return  "/admin/cmssite/edit";
		}
		Flash.current().success(UPDATE_SUCCESS);
		return LIST_ACTION+"?"+Flash.current().get(Constants.QUERY_STRING);
	}
	
	/** 批量删除 */
	@RequestMapping()
	public String delete(ModelMap model,@RequestParam("siteDomain") String siteDomain) {
		cmsSiteService.removeById(siteDomain);
		Flash.current().success(DELETE_SUCCESS);
		return LIST_ACTION+"?"+Flash.current().get(Constants.QUERY_STRING);
	}
	
	
	@RequestMapping
	public String initAllSiteDefaultChannels(ModelMap model) {
		List<CmsSite> siteList = cmsSiteService.initAllSiteDefaultChannels();
		model.put("siteList", siteList);
		Flash.current().success("初始化所有网站频道成功");
		return "/admin/cmssite/initAllSiteDefaultChannels";
	}
	
	@RequestMapping
	public String updateSearchEngineRecord() {
		List<CmsSite> updateSuccess = cmsSiteService.updateSearchEngineRecord();
		Flash.current().success(updateSuccess.size()+"个网站有更新site记录");
		return "/commons/messages";
	}
	
	@RequestMapping
	public String updateSearchEngineKeywordMaxRank() {
		List<CmsSite> updateSuccess = cmsSiteService.updateSearchEngineKeywordMaxRank();
		Flash.current().success(updateSuccess.size()+"个网站有更新搜索引擎排名");
		return "/commons/messages";
	}
	
	@RequestMapping
	public String updateHttpStatus() {
		cmsSiteService.updateHttpStatus();
		Flash.current().success("更新所有网站http状态成功");
		return LIST_ACTION;
	}
	
	@RequestMapping
	public String showAllSiteLink(ModelMap model) {
		List<CmsSite> cityList = cmsSiteService.findAll();
		Collections.sort(cityList,new BeanComparator("recordBaidu"));
		model.put("cityList", cityList);
		return "/admin/cmssite/showAllSiteLink";
	}
	
	@RequestMapping
	public String batchUpdateProperty(ModelMap model,String[] sites,String key,String value) {
		if(sites != null && StringUtils.isNotBlank(key)) {
			cmsSiteService.batchUpdateProperty(sites, key, value);
			Flash.current().success("batchUpdateProperty成功");
		}
		
		return "/admin/cmssite/batchUpdateProperty";
	}
	
	/** 显示列表用于签名 */
	@RequestMapping
	public String signature(ModelMap model,CmsDomainQuery query,HttpServletRequest request) {
		model.put("cmsSiteList", cmsSiteService.findAll());
		return "/admin/cmssite/signature";
	}

}

