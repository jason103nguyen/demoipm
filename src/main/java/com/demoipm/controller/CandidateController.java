package com.demoipm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
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

	@GetMapping(value = {"/view-all-candidate"})
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String viewCandidateInformation(
		@RequestParam(name = "content", required = false) String content,
		@RequestParam(name = "minAge", required = false) Integer minAge,
		@RequestParam(name = "maxAge", required = false) Integer maxAge, 
		@RequestParam(name = "skillId", required = false) List<Integer> listId,
		@RequestParam(name = "page", required = false) Integer page,
		Model model) {

		if (page == null) {
			page = 0;
		}
	
		List<CandidateDto> listCandidate = new ArrayList<CandidateDto>();
		Integer totalPage = 0;
		
		if (ageIsEmpty(minAge, maxAge) && listId == null && contentIsEmpty(content)) {

			listCandidate = candidateServiceImpl.filterCandidatePassEntryTest(page);
			totalPage = candidateServiceImpl.countPageCandidatePassEntryTest();

		} else if ((minAge != null && maxAge != null) && listId == null && content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateByAge(minAge, maxAge, page);
			totalPage = candidateServiceImpl.countPageCandidateByAge(minAge, maxAge);

		} else if ((minAge == null || maxAge == null) && listId != null && content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateBySkill(listId, page);
			totalPage = candidateServiceImpl.countPageCandidateBySkill(listId);

		} else if ((minAge == null || maxAge == null) && listId == null && !content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateByContent(content, page);
			totalPage = candidateServiceImpl.countPageCandidateByContent(content);

		} else if ((minAge != null || maxAge != null) && listId != null && content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateByAgeAndSkill(minAge, maxAge, listId, page);
			totalPage = candidateServiceImpl.countPageCandidateByAgeAndSkill(minAge, maxAge, listId);

		} else if ((minAge == null || maxAge == null) && listId != null && !content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateByContentAndSkill(content, listId, page);
			totalPage = candidateServiceImpl.countPageCandidateByContentAndSkill(content, listId);

		} else if ((minAge != null || maxAge != null) && listId == null && !content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateByContentAndAge(content, minAge, maxAge, page);
			totalPage = candidateServiceImpl.countPageCandidateByContentAndAge(content, minAge, maxAge);

		} else if ((minAge != null || maxAge != null) && listId != null && !content.equals("")) {

			listCandidate = candidateServiceImpl.filterCandidateByContentAndAgeAndSkill(content, minAge, maxAge, listId, page);
			totalPage = candidateServiceImpl.countPageCandidateByContentAndAgeAndSkill(content, minAge, maxAge, listId);

		}
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidateId(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		
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
		Integer totalPage = candidateServiceImpl.countPageCandidatePassEntryTest();
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("candidate", candidateDto);
		return "candidate/viewInfoDetailCandidate";
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
	
	private boolean ageIsEmpty(Integer minAge, Integer maxAge) {
		
		if (minAge == null || maxAge == null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	private boolean contentIsEmpty(String content) {
		
		if( content == null) {
			return true;
		} else if (content.isEmpty() || content.isBlank()) {
			return true;
		} else {
			return false;
		}
	}
}