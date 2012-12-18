package com.fpcms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

	@RequestMapping()
	public void echo() {
		System.out.println("echo() xxxxxxxxxxxxx");
	}
	
	@RequestMapping()
	public void echo2() {
		System.out.println("echo2() xxxxxxxxxxxxx");
	}
}
