package com.du.SpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testDemo {
	
	@GetMapping("/")
	public String home() {
		return "hello work";
		
	}

}
