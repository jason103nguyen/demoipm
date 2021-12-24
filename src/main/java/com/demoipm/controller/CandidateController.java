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

import com.demoipm.Constant.PaginationInfo;
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
	
	@GetMapping(value = {"/view-all-candidate", "/view-all-candidate/{page}"})
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String viewCandidateInformation(Model model, @PathVariable(name = "page", required = false) Integer page) {
		
		if (page == null){
			page = 0;
		}

		List<CandidateDto> listCandidate = candidateServiceImpl.readCandidatePassEntryTest(page);
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidate(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		showAllSkill(model);
		Integer totalPage = countCandidatePassEntryTest();
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("listCandidate", listCandidate);
		return "candidate/viewCandidateInformation";
	}
	
	private Integer countCandidatePassEntryTest() {
		
		Integer totalRow = candidateServiceImpl.countCandidatePassEntryTest();
		return Math.round(totalRow/PaginationInfo.MAX_RESULT);
	}
	
	private void showAllSkill(Model model) {
		
		List<SkillDto> listSkillDto = new ArrayList<SkillDto>();
		try {
			listSkillDto = skillServiceImpl.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("listSkill", listSkillDto);
	}
	
	@GetMapping(value = "/find-candidate")
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String findCandidate(@RequestParam(name = "content") String content,
			Model model) {
		
		if (content == null) {
			return "redirect:/vie-all-candidate";
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
		
		Integer totalPage = countCandidatePassEntryTest();
		model.addAttribute("totalPage", totalPage);
		showAllSkill(model);
		return "candidate/viewCandidateInformation";
	}
	
	@GetMapping(value = "/filter-candidate-age")
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String filterCandidateByAge(@RequestParam(name = "minAge", required = false) Integer minAge,
			@RequestParam(name = "maxAge", required = false) Integer maxAge, Model model) {
		
		if (minAge == null || maxAge == null) {
			return "redirect:/view-all-candidate";
		}
		
		List<CandidateDto> listCandidate = candidateServiceImpl.filterCandidateByAge(minAge, maxAge);
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidate(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		Integer totalPage = countCandidatePassEntryTest();
		model.addAttribute("totalPage", totalPage);
		showAllSkill(model);
		model.addAttribute("listCandidate", listCandidate);
		
		return "candidate/viewCandidateInformation";
	}
	
	@GetMapping(value = {"/filter-candidate-skill"})
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String filterCandidateBySkill(@RequestParam(name = "skillId", required = false) List<Integer> listId, Model model) {
		
		List<CandidateDto> listCandidate = new ArrayList<CandidateDto>();
		
		if (listId == null) {
			return "redirect:/view-all-candidate";
		}
		
		listCandidate = candidateServiceImpl.filterCandidateBySkill(listId);
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidate(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		Integer totalPage = countCandidatePassEntryTest();
		model.addAttribute("totalPage", totalPage);
		showAllSkill(model);
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
		
		showAllSkill(model);
		Integer totalPage = countCandidatePassEntryTest();
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("candidate", candidateDto);
		return "candidate/viewInfoDetailCandidate";
	}
	
}
