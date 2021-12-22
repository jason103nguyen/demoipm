package com.demoipm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewDto;
import com.demoipm.service.CandidateService;

@Controller
public class CandidateController {

	@Autowired
	private CandidateService candidateServiceImpl;
	
	@GetMapping(value = {"/view-candidate-information"})
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String viewCandidateInformation(Model model) {
		
		List<CandidateDto> listCandidate = candidateServiceImpl.readCandidatePassEntryTest();
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidate(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		model.addAttribute("listCandidate", listCandidate);
		return "candidate/viewCandidateInformation";
	}
	
	@GetMapping(value = "/find-candidate")
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String findCandidate(@RequestParam(name = "content") String content, Model model) {
		
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
	
}
