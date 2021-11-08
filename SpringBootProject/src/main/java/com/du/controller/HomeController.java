package com.du.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	private static Logger Log = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/")
	public @ResponseBody String home() {
		Log.info("test.......");
		return "HelloWorld";
		
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value="name", required = false) String visitor, Model model) {
		
		String message = visitor != null ? visitor + " 您好 " : "訪客  您好";
		 model.addAttribute("helloMessage", message);
		return "greeting";
		
	}
	
}
