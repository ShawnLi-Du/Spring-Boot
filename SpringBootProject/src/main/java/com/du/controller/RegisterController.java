package com.du.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.du.model.MemberAccount;
import com.du.model.RegisterBean;
import com.du.servise.RegisterServise;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class RegisterController {

	@Autowired
	RegisterServise registerServlise;

	// email 驗證---------------------------------------------------
	@PostMapping("/cheackEmail")
	public Object cheackEmail(@RequestBody RegisterBean registerBean) {
		Object numStr = registerServlise.email(registerBean.getEmail());
		// 0=可新增, 1=重複
		if (numStr == "0") {
			return "帳號可以新增";
		} else {
			return "帳號重複";
		}
	}

	// 註冊---------------------------------------------------------
	@PostMapping("/createMember")
	public String createMember(@RequestBody RegisterBean registerBean) {
		registerServlise.register(registerBean.getEmail(), registerBean.getPassword());
		return "註冊成功";
	}

	// 登入---------------------------------------------------------
	@PostMapping("/input")
	public String input(@RequestBody RegisterBean registerBean) {

		List<RegisterBean> list = registerServlise.input(registerBean.getEmail(), registerBean.getPassword());
		if (!list.get(0).getEmail().equals("error")) {
			return "登入成功";
		} else {
			return "登入失敗";
		}

//		String mail = request.getParameter("mail");
//		String password = request.getParameter("password");
//		List<RegisterBean> list = registerServlise.input(mail, password);
//		if (!list.get(0).getEmail().equals("error")) {
//			model.addAttribute("error", "");
//			request.setAttribute("lists", list);
//			return "viewDate";
//		} else {
//			model.addAttribute("error", "使用者名稱或者密碼錯誤");
//			return "input";
//		}
	}

	// 找回密碼---------------------------------------------------------
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestBody RegisterBean registerBean) {

		String rs = registerServlise.search(registerBean.getEmail());
		return rs;

		
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	// 返回登入頁面---------------------------------------------------------
	@PostMapping("login")
	public String login() {
		return "input";
	}

	// 返回註冊頁面---------------------------------------------------------
	@PostMapping("index")
	public String head() {
		return "index";
	}

	// 返回找回密碼頁面---------------------------------------------------------
	@PostMapping("search")
	public String search() {
		return "search";
	}
}
