package com.fpcms.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	/** 显示 */
	@RequestMapping()
	public String index(ModelMap model) throws Exception {
		return "";
	}
}
