package com.demoipm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demoipm.dto.UserAppDto;
import com.demoipm.service.UserAppService;

@Controller
public class SkillController {

	@Autowired
	private UserAppService userAppService;
	
	@GetMapping(value = "/user-info")
	public String userInfo() {
		
		return "userInfo";
	}
	
	@GetMapping(value = "/candidate-info")
	public String candidateInfo() {
		
		return "candidateInfo";
	}
	
	@GetMapping(value = "/403")
	public String error403() {
		
		return "403Page";
	}
	
	@GetMapping(value = "/register")
	public String register(Model model) {
		
		model.addAttribute("userApp", new UserAppDto());
		return "register";
	}
	
	@PostMapping(value = "/register")
	public String register(@ModelAttribute(name = "userApp") UserAppDto userApp) {
		
		System.out.println("ID: " + userApp.getId());
		userAppService.create(userApp);
		return "homepage";
	}
	
	@GetMapping(value = "/login")
	public String login() {
		
		return "login";
	}
}
