package com.fpcms.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkedController {

	@RequestMapping("/linked/{number}.do")
	public String linked(@PathVariable("number") String number) {
		return "/linked";
	}
	
}
