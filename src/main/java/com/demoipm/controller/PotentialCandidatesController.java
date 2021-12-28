package com.demoipm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.demoipm.dto.CandidateDto;
import com.demoipm.entities.Candidate;
import com.demoipm.service.CandidateService;
import com.demoipm.service.PotentialCandidateService;

@Controller
public class PotentialCandidatesController {
	
	@Autowired
	private PotentialCandidateService potentialCandidateService;

	@GetMapping("view-potential-candidates-info/{id}")
	@Secured(value = "ROLE_HR")
	public String viewPotentialCandidatesInfo(@PathVariable("id") int id, Model model) throws Exception {
		
		CandidateDto candidateDto = potentialCandidateService.getPotentialCandidateByID(id);
		
		model.addAttribute("candidateDto", candidateDto);
		
		return "potentialCandidates/potentialCandidatesInfo";
		
	}

}
