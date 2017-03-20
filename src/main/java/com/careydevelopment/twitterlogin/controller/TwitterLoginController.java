package com.careydevelopment.twitterlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwitterLoginController {
	
	@RequestMapping("/twitterLogin")
	public String twiterLogin(Model model) {
		return "twitterLogin";
	}

}
