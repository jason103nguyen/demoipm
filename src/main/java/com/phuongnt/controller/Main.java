package com.phuongnt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.phuongnt.dto.JobSkillDto;
import com.phuongnt.dto.SkillDto;
import com.phuongnt.service.SkillService;

@Controller
public class Main {

	@Autowired
	private SkillService skillServiceImpl;

	@GetMapping(value = "/test")
	public String test() {
		System.out.println("Hello phuong!");
		
		SkillDto skillDto = null;
		try {
			skillDto = skillServiceImpl.readById(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(skillDto.getName());
		List<JobSkillDto> listJobSkillDto = skillDto.getListJobSkillDto();
		
		
		return "hello";
	}
}
