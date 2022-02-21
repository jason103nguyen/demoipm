package com.demoipm.controller;



import javax.servlet.http.HttpSession;
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
import com.demoipm.dto.potentialcandidate.PotentialCandidatePageDto;

import com.demoipm.service.PotentialCandidateService;

@Controller
public class PotentialCandidatesController {

	@Autowired
	private PotentialCandidateService potentialCandidateService;

	@GetMapping("view-potential-candidates-info")
	@Secured(value = "ROLE_HR")
	public String viewPotentialCandidatesInfo(@RequestParam("id") int id, Model model, HttpSession session) throws Exception {

		CandidateDto candidateDto = potentialCandidateService.getPotentialCandidateByID(id);

		model.addAttribute("candidateDto", candidateDto);
		
		String keySearch = (String) session.getAttribute("keySearch");
		
		Integer pageNo = (Integer) session.getAttribute("pageNo");
		
		String sortBy = (String) session.getAttribute("sortBy");
		
		String direction = (String) session.getAttribute("direction");
		
		model.addAttribute("keySearch", keySearch);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("direction", direction);

		return "potentialCandidates/potentialCandidatesInfo";

	}

	@Secured(value = "ROLE_HR")
	@GetMapping("view-potential-candidates-list")
	public String filterPotentialCandidatesList(Model model, HttpSession session,

			@RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,

			@RequestParam(name = "pageSize", required = false, defaultValue = "3") Integer pageSize,

			@RequestParam(name = "keySearch", required = false, defaultValue = "") String keySearch,

			@RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy ,
			
			@RequestParam(name = "direction", required = false, defaultValue = "ASC") String direction) {

		PotentialCandidatePageDto listFilterCandidateDto = potentialCandidateService
				.FilterPotentialCandidateIsDelete(keySearch, pageNo, pageSize, sortBy, direction);

		model.addAttribute("listCandidateDto", listFilterCandidateDto.getListPotentialCandidate());

		model.addAttribute("totalPage", listFilterCandidateDto.getTotalPage());

		model.addAttribute("currentPage", listFilterCandidateDto.getCurrentPage());

		model.addAttribute("keySearch", listFilterCandidateDto.getKeySearch());
		
		session.setAttribute("keySearch", keySearch);

		model.addAttribute("pageNo", listFilterCandidateDto.getPageNo());
		
		session.setAttribute("pageNo", pageNo);

		model.addAttribute("sortBy", listFilterCandidateDto.getSortBy());
		
		session.setAttribute("sortBy", sortBy);
		
		model.addAttribute("direction", listFilterCandidateDto.getDirection());
		
		session.setAttribute("direction", direction);

		return "potentialCandidates/potentialCandidatesList";
	}

	@Secured(value = "ROLE_HR")
	@GetMapping("delete-potential-candidates/{id}")
	public String deletePotentialCandidate(@PathVariable("id") Integer id, Model model, HttpSession session) {
		potentialCandidateService.deletePotentialCandidate(id);
		
		String keySearch = (String) session.getAttribute("keySearch");
		
		Integer pageNo = (Integer) session.getAttribute("pageNo");
		
		String sortBy = (String) session.getAttribute("sortBy");
		
		String direction = (String) session.getAttribute("direction");

		return "redirect:/view-potential-candidates-list?keySearch="+keySearch+"&pageNo="+pageNo+"&sortBy="+sortBy+"&direction="+direction;
	}

	@Secured(value = "ROLE_HR")
	@GetMapping("add-new-potential-candidates")
	public String getCreatePotentialCandidate(Model model, HttpSession session) {

		CandidateDto candidateDto = new CandidateDto();
		model.addAttribute("candidateDto", candidateDto);
		
		String keySearch = (String) session.getAttribute("keySearch");
		
		Integer pageNo = (Integer) session.getAttribute("pageNo");
		
		String sortBy = (String) session.getAttribute("sortBy");
		
		String direction = (String) session.getAttribute("direction");
		
		model.addAttribute("keySearch", keySearch);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("direction", direction);
		
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
	public String getUpdatePotentialCandidate(@RequestParam("id") int id, Model model, HttpSession session) {

		CandidateDto candidateDto = potentialCandidateService.getPotentialCandidateByID(id);
		model.addAttribute("candidateDto", candidateDto);
		
		String keySearch = (String) session.getAttribute("keySearch");
		
		Integer pageNo = (Integer) session.getAttribute("pageNo");
		
		String sortBy = (String) session.getAttribute("sortBy");
		
		String direction = (String) session.getAttribute("direction");
		
		model.addAttribute("keySearch", keySearch);	
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("direction", direction);

		return "potentialCandidates/update-PotentialCandidate";
	}

	@Secured(value = "ROLE_HR")
	@PostMapping("update-potential-candidates")
	public String postUpdatePotentialCandidate(@ModelAttribute("candidateDto") @Valid CandidateDto candidateDto,
			BindingResult result, HttpSession session) {

		if (result.hasErrors()) {
			return "potentialCandidates/update-PotentialCandidate";
		}
		potentialCandidateService.updatePotentialCandidate(candidateDto);

		String keySearch = (String) session.getAttribute("keySearch");
		
		Integer pageNo = (Integer) session.getAttribute("pageNo");
		
		String sortBy = (String) session.getAttribute("sortBy");
		
		String direction = (String) session.getAttribute("direction");

		return "redirect:/view-potential-candidates-list?keySearch="+keySearch+"&pageNo="+pageNo+"&sortBy="+sortBy+"&direction="+direction;
	}

}
