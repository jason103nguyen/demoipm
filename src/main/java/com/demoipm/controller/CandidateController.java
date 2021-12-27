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
		
		if (page == null) {
			page = 0;
		}

		List<CandidateDto> listCandidate = candidateServiceImpl.readCandidatePassEntryTest(page);
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidateId(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		showAllSkill(model);
		Integer totalPage = countCandidatePassEntryTest();
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("listCandidate", listCandidate);
		return "candidate/viewCandidateInformation";
	}

	@GetMapping(value = "/filter-candidate")
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String filterCandidate(
		@RequestParam(name = "content", required = false) String content,
		@RequestParam(name = "minAge", required = false) Integer minAge,
		@RequestParam(name = "maxAge", required = false) Integer maxAge, 
		@RequestParam(name = "skillId", required = false) List<Integer> listId,
		Model model) {
		
		List<CandidateDto> listCandidate = new ArrayList<CandidateDto>();
		
		if ((minAge == null || maxAge == null) && listId == null && content.equals("")) {

			return "redirect:/view-all-candidate";
		} else if ((minAge != null && maxAge != null) && listId == null && content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateByAge(minAge, maxAge);

		} else if ((minAge == null || maxAge == null) && listId != null && content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateBySkill(listId);

		} else if ((minAge == null || maxAge == null) && listId == null && !content.equals("")) {

			listCandidate = candidateServiceImpl.searchCandidate(content);

		} else if ((minAge != null || maxAge != null) && listId != null && content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateByAgeAndSkill(minAge, maxAge, listId);

		} else if ((minAge == null || maxAge == null) && listId != null && !content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateByContentAndSkill(content, listId);

		} else if ((minAge != null || maxAge != null) && listId == null && !content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateByContentAndAge(content, minAge, maxAge);

		} else if ((minAge != null || maxAge != null) && listId != null && !content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateByContentAndAgeAndSkill(content, minAge, maxAge, listId);

		}
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidateId(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		Integer totalPage = countCandidatePassEntryTest();
		model.addAttribute("totalPage", totalPage);
		showAllSkill(model);
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
