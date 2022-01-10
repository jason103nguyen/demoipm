package com.demoipm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demoipm.dto.CandidateDto;
import com.demoipm.service.PotentialCandidateService;

@Controller
public class PotentialCandidatesController {

	@Autowired
	private PotentialCandidateService potentialCandidateService;

	@GetMapping("view-potential-candidates-info")
	@Secured(value = "ROLE_HR")
	public String viewPotentialCandidatesInfo(@RequestParam("id") int id, Model model) throws Exception {

		CandidateDto candidateDto = potentialCandidateService.getPotentialCandidateByID(id);

		model.addAttribute("candidateDto", candidateDto);

		return "potentialCandidates/potentialCandidatesInfo";

	}

	@Secured(value = "ROLE_HR")
	@GetMapping("view-potential-candidates-list")
	public String viewPotentialCandidatesList(Model model, @Param("keySearch") String keySearch) {

		if (keySearch == null) {
			List<CandidateDto> listCandidateDto = potentialCandidateService.getAllPotentialCandidate();
			model.addAttribute("listCandidateDto", listCandidateDto);
		} else {
			List<CandidateDto> listSearchCandidateDto = potentialCandidateService
					.searchPotentialCandidateIsDelete(keySearch);
			model.addAttribute("listCandidateDto", listSearchCandidateDto);
		}
		return "potentialCandidates/potentialCandidatesList";
	}

	@Secured(value = "ROLE_HR")
	@GetMapping("delete-potential-candidates/{id}")
	public String deletePotentialCandidate(@PathVariable("id") Integer id) {
		potentialCandidateService.deletePotentialCandidate(id);
		return "redirect:/view-potential-candidates-list";
	}

	@Secured(value = "ROLE_HR")
	@GetMapping("add-new-potential-candidates")
	public String getCreatePotentialCandidate(Model model) {

		CandidateDto candidateDto = new CandidateDto();
		model.addAttribute("candidateDto", candidateDto);
		return "potentialCandidates/create-PotentialCandidate";
	}

	@Secured(value = "ROLE_HR")
	@PostMapping("add-new-potential-candidates")
	public String postCreatePotentialCandidate(@ModelAttribute("candidateDto") @Valid CandidateDto candidateDto,
			BindingResult result) {	
		
		if (result.hasErrors()) {
			return "potentialCandidates/create-PotentialCandidate";
		}	
		potentialCandidateService.createPotentialCandidate(candidateDto);
		
		return "redirect:/view-potential-candidates-list";
	}
	
	@Secured(value = "ROLE_HR")
	@GetMapping("update-potential-candidates")
	public String getUpdatePotentialCandidate(@RequestParam("id") int id, Model model) {

		  CandidateDto candidateDto = potentialCandidateService.getPotentialCandidateByID(id);  
		  model.addAttribute("candidateDto", candidateDto);
		  
		return "potentialCandidates/update-PotentialCandidate";
	}
	
	@Secured(value = "ROLE_HR")
	@PostMapping("update-potential-candidates")
	public String postUpdatePotentialCandidate(@ModelAttribute("candidateDto") @Valid CandidateDto candidateDto, BindingResult result) {
	
		if (result.hasErrors()) {
			return "potentialCandidates/update-PotentialCandidate";
		}
		potentialCandidateService.updatePotentialCandidate(candidateDto);
		 
		return "redirect:/view-potential-candidates-list";
	}
}
