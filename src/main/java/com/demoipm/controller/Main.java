package com.demoipm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.demoipm.dao.RoleAppDao;
import com.demoipm.service.UserRoleService;

@Controller
public class Main {

	@Autowired
	private RoleAppDao roleAppDao;

	@GetMapping(value = "/test")
	public String test() {
		
		 List<String> roleNames = roleAppDao.getRoleNames(1);
		
		System.out.println("ROLE: " + roleNames.get(0));
		return "hello";
	}
}
