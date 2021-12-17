package com.phuongnt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.phuongnt.dto.RecruitmentDto;
import com.phuongnt.dto.SkillDto;
import com.phuongnt.service.RecruitmentService;

@Controller
public class HomepageController {

	@Autowired
	private RecruitmentService recruimentServiceImpl;
	
	@GetMapping(value = {"/", "/home", "/homepage"})
	public String hello(Model model) {
		
		List<RecruitmentDto> listRecruitmentDto = null;
		try {
			listRecruitmentDto = recruimentServiceImpl.readAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(RecruitmentDto recruitmentDto : listRecruitmentDto) {
			System.out.println("Ngày: " + recruitmentDto.getStartRecruitment());
			System.out.println("Nghành: " + recruitmentDto.getCareer().getName());
			System.out.println("Vị trí: " + recruitmentDto.getJobDto().getName());
			
			List<SkillDto> listSkillDto = null;
			try {
				listSkillDto = recruimentServiceImpl.readAllSkillByRecruitment(recruitmentDto.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			recruitmentDto.setListSkill(listSkillDto);
			String skillName = "";
			for(SkillDto skill : listSkillDto) {
				skillName += skill.getName() + ", ";
			}
			
			System.out.println("Skill: " + skillName);
			System.out.println("Số lượng: " + recruitmentDto.getNumber());
			System.out.println("---");
		}
		
		model.addAttribute("listRecruitment", listRecruitmentDto);
		return "homepage/homepage";
	}
	
}
