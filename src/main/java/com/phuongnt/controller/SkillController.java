package com.phuongnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.phuongnt.dto.UserAppDto;
import com.phuongnt.entities.UserApp;
import com.phuongnt.service.JobService;
import com.phuongnt.service.JobSkillService;
import com.phuongnt.service.SkillService;
import com.phuongnt.service.UserAppService;

@Controller
public class SkillController {

	@Autowired
	private UserAppService userAppService;
	
	@GetMapping(value = "/")
	public String hello() {
		
		return "hello";
	}
	
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
	
	@GetMapping(value = "/home")
	public String home() {
		
		return "homepage";
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