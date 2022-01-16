package com.demoipm.controller;

import java.util.List;

import com.demoipm.dto.CareerDto;
import com.demoipm.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

	@Autowired
	private CareerService careerService;

	@Secured("ROLE_HR")
	@GetMapping(value = {"/", "/home", "/homepage"})
	public String homepage(Model model) {
		List<CareerDto> careerList = null;
		try {
			careerList = careerService.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("careerList", careerList);
		return "homepage/homepage_v2";
	}

	@GetMapping(value = "/my-page")
	public String myPage() {
		return "myPage";
	}
	
}
