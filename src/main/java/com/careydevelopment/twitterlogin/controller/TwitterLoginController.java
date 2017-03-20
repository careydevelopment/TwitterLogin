package com.careydevelopment.twitterlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwitterLoginController {
	
	@RequestMapping("/")
	public String twiterLogin(Model model) {
		return "twitterLogin";
	}

}
