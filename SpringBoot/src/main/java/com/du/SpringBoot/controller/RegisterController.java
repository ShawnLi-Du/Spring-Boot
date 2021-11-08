package com.du.SpringBoot.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.du.SpringBoot.model.RegisterBean;
import com.du.SpringBoot.service.RegisterServise;

@Controller
public class RegisterController {
	
	@Autowired
	RegisterServise registerServlise;

	// email 驗證---------------------------------------------------------
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Object email(@RequestParam("email") String mail) {
		Object bl = registerServlise.email(mail);
		return bl;
	}


	// 註冊---------------------------------------------------------
	@RequestMapping(value = "/registerS", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request) {

		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		if (password.length() != 0) {
		}

		registerServlise.register(mail, password);
		ModelAndView modelAndView = new ModelAndView("input");
		return modelAndView;
	}

	// 登入---------------------------------------------------------
	@RequestMapping(value = "/input", method = RequestMethod.POST)
	public ModelAndView input(HttpServletRequest request, Model model) {

		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		
		List<RegisterBean> list = registerServlise.input(mail, password);
		ModelAndView modelAndView = new ModelAndView();

		if (!list.get(0).getEmail().equals("error")) {
			model.addAttribute("error", "");
			request.setAttribute("lists", list);
			modelAndView.setViewName("viewDate");
			return modelAndView;
		} else {
			model.addAttribute("error", "使用者名稱或者密碼錯誤");
			modelAndView.setViewName("input");
			return modelAndView;
		}
	}

	// 找回密碼---------------------------------------------------------
	@RequestMapping(value = "into", method = RequestMethod.POST)
	public ModelAndView search(HttpServletRequest request, Model model) {
		
		String email = request.getParameter("mail");
		Integer count = registerServlise.search(email);
		ModelAndView modelAndView = new ModelAndView();
		
		if(count == 1) {  //更新成功
			model.addAttribute("passworSsuccess", "更新成功");
			modelAndView.setViewName("input");
			return modelAndView;
		}else {
			model.addAttribute("passworError", "更新失敗");
			modelAndView.setViewName("input");
			return modelAndView;
		}
	}

	
	// 返回登入頁面---------------------------------------------------------
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login() {
		return new ModelAndView("input");
	}

	// 返回註冊頁面---------------------------------------------------------
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ModelAndView head() {
		return new ModelAndView("/index");
	}
	
	// 返回找回密碼頁面---------------------------------------------------------
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search() {
		return new ModelAndView("/search");
	}

}
