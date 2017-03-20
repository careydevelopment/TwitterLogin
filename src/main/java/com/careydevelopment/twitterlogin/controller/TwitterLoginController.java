package com.careydevelopment.twitterlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwitterLoginController {
	
	//starting page for Twitter login demo
	@RequestMapping("/twitterLogin")
	public String twiterLogin(Model model) {
		return "twitterLogin";
	}

	
	//redirect to demo if user hits the root
	@RequestMapping("/")
	public String home(Model model) {
		return "redirect:twitterLogin";
	}
}
