package com.demoipm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.demoipm.dao.RoleAppDao;

@Controller
public class Main {

	@Autowired
	private RoleAppDao roleAppDao;

	@GetMapping(value = "/test")
	public String test() {
		
		int i = 3/0;
		return "hello";
	}
}
