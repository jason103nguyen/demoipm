package com.demoipm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewDto;
import com.demoipm.dto.SkillDto;
import com.demoipm.service.CandidateService;
import com.demoipm.service.SkillService;

@Controller
public class CandidateController {

	@Autowired
	private CandidateService candidateServiceImpl;
	
	@Autowired
	private SkillService skillServiceImpl;
	
	@GetMapping(value = {"/view-candidate-information"})
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String viewCandidateInformation(Model model) {
		
		List<CandidateDto> listCandidate = candidateServiceImpl.readCandidatePassEntryTest();
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidate(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		List<SkillDto> listSkillDto = new ArrayList<SkillDto>();
		try {
			listSkillDto = skillServiceImpl.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("listSkill", listSkillDto);
		model.addAttribute("listCandidate", listCandidate);
		return "candidate/viewCandidateInformation";
	}
	
	@GetMapping(value = "/find-candidate")
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String findCandidate(@RequestParam(name = "content") String content,
			Model model) {
		
		if (content == null) {
			return "redirect:/view-candidate-information";
		}
		List<CandidateDto> listCandidate = candidateServiceImpl.searchCandidate(content);
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidate(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		if (listCandidate.isEmpty()) {
			
		} else {
			model.addAttribute("listCandidate", listCandidate);
		}
		
		return "candidate/viewCandidateInformation";
	}
	
	@GetMapping(value = "/filter-candidate-age")
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String filterCandidateByAge(@RequestParam(name = "minAge") int minAge,
			@RequestParam(name = "maxAge") int maxAge, Model model) {
		
		List<CandidateDto> listCandidate = candidateServiceImpl.filterCandidateByAge(minAge, maxAge);
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidate(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		model.addAttribute("listCandidate", listCandidate);
		
		return "candidate/viewCandidateInformation";
	}
	
	@GetMapping(value = "/filter-candidate-skill")
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String filterCandidateBySkill(@RequestParam(name = "skillId") List<Integer> listId, Model model) {
		
		List<CandidateDto> listCandidate = new ArrayList<CandidateDto>();
		
		try {
			listCandidate = candidateServiceImpl.filterCandidateBySkill(listId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidate(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		List<SkillDto> listSkillDto = new ArrayList<SkillDto>();
		try {
			listSkillDto = skillServiceImpl.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("listSkill", listSkillDto);
		model.addAttribute("listCandidate", listCandidate);
		
		return "candidate/viewCandidateInformation";
	}
	
	@GetMapping(value = "/view-candidate-information/{id}")
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String viewCandidateInfoById(@PathVariable(name = "id") int id, Model model) {
		
		CandidateDto candidateDto = null;
		try {
			candidateDto = candidateServiceImpl.readById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("candidate", candidateDto);
		return "candidate/viewInfoDetailCandidate";
	}
	
}
