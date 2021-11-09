package com.du.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.du.model.RegisterBean;
import com.du.servise.RegisterServise;

@RestController
//@Controller
public class RegisterController {
	
	private static Logger Log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	RegisterServise registerServlise;

	// email 驗證---------------------------------------------------------
	@PostMapping("/register")
	public Object email(@RequestParam("email") String mail) {
		return registerServlise.email(mail);
	}


	// 註冊---------------------------------------------------------
	@PostMapping("/registerS")
	public String register(HttpServletRequest request) {

		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		if (password.length() != 0) {
		}

		registerServlise.register(mail, password);
		return "input";
	}

	// 登入---------------------------------------------------------
	@PostMapping("/input")
	public String input(HttpServletRequest request, Model model) {

		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		
		List<RegisterBean> list = registerServlise.input(mail, password);

		if (!list.get(0).getEmail().equals("error")) {
			model.addAttribute("error", "");
			request.setAttribute("lists", list);
			return "viewDate";
		} else {
			model.addAttribute("error", "使用者名稱或者密碼錯誤");
			return "input";
		}
	}

	// 找回密碼---------------------------------------------------------
	@PostMapping("into")
	public String search(HttpServletRequest request, Model model) {
		
		String email = request.getParameter("mail");
		Integer count = registerServlise.search(email);
		
		if(count == 1) {  //更新成功
			model.addAttribute("passworSsuccess", "更新成功");
			return "input";
		}else {
			model.addAttribute("passworError", "更新失敗");
			return "input";
		}
	}

	
	
	
	@GetMapping("/")
	public String home() {
		Log.info("返回註冊頁面...");
		return "index";
	}
	
	// 返回登入頁面---------------------------------------------------------
	@PostMapping("login")
	public String login() {
		Log.info("返回登入頁面...");
		return "input";
	}

	// 返回註冊頁面---------------------------------------------------------
	@PostMapping("index")
	public String head() {
		Log.info("返回註冊頁面...");
		return "index";
	}
	// 返回找回密碼頁面---------------------------------------------------------
	@PostMapping("search")
	public String search() {
		Log.info("返回找回密碼頁面...");
		return "search";
	}
}
