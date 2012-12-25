package com.fpcms.admin.controller;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/system")
public class SystemController {

	@RequestMapping
	public void systemProperties(HttpServletResponse response) throws IOException {
		PrintStream out = new PrintStream(response.getOutputStream());
		System.getProperties().list(out);
		out.flush();
	}
	
}
