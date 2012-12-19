package com.fpcms.common;

import org.springframework.ui.ModelMap;

import com.duowan.common.util.page.Page;
import com.duowan.common.util.page.PageQuery;
import com.fpcms.common.util.SpringMVCUtils;

public class BaseController {
	
	public static String UPDATE_SUCCESS = "UPDATE_SUCCESS";
	public static String DELETE_SUCCESS = "DELETE_SUCCESS";
	public static String CREATED_SUCCESS = "CREATED_SUCCESS";
	
	protected ModelMap toModelMap(Page page, PageQuery query) {
		return SpringMVCUtils.toModelMap(page, query);
	}
	
}
