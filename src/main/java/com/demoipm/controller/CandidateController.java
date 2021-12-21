package com.demoipm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		
		int number = 1;
		for (CandidateDto candidateDto : listCandidate) {
			System.out.println("TT: " + number);
			number++;
			
			System.out.println("Name: " + candidateDto.getFullName());
			
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidate(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
			for(InterviewDto interviewDto : listInterviewDto) {
				
				System.out.println("Time: " + interviewDto.getTimeInterview());
			}
		}
		model.addAttribute("listCandidate", listCandidate);
		return "candidate/viewCandidateInformation";
	}
	
}
