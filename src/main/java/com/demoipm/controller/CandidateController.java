package com.demoipm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewDto;
import com.demoipm.dto.SkillDto;
import com.demoipm.service.CandidateService;
import com.demoipm.service.InterviewService;
import com.demoipm.service.SkillService;
import com.demoipm.service.impl.InterviewServiceImpl;

@Controller
public class CandidateController {

	@Autowired
	private CandidateService candidateServiceImpl;
	
	@Autowired
	private SkillService skillServiceImpl;
	
	@Autowired
	private InterviewService interviewServiceImpl;

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
		
		if (ageIsEmpty(minAge, maxAge) && listIdIsEmpty(listId) && contentIsEmpty(content)) {

			listCandidate = candidateServiceImpl.filterCandidatePassEntryTest(page);
			totalPage = candidateServiceImpl.countPageCandidatePassEntryTest();

		} else if (!ageIsEmpty(minAge, maxAge) && listIdIsEmpty(listId) && contentIsEmpty(content)) {

			listCandidate = candidateServiceImpl.filterCandidateByAge(minAge, maxAge, page);
			totalPage = candidateServiceImpl.countPageCandidateByAge(minAge, maxAge);

		} else if (ageIsEmpty(minAge, maxAge) && !listIdIsEmpty(listId) && contentIsEmpty(content)) {

			listCandidate = candidateServiceImpl.filterCandidateBySkill(listId, page);
			totalPage = candidateServiceImpl.countPageCandidateBySkill(listId);

		} else if (ageIsEmpty(minAge, maxAge) && listIdIsEmpty(listId) && !contentIsEmpty(content)) {

			listCandidate = candidateServiceImpl.filterCandidateByContent(content, page);
			totalPage = candidateServiceImpl.countPageCandidateByContent(content);

		} else if (!ageIsEmpty(minAge, maxAge) && !listIdIsEmpty(listId) && contentIsEmpty(content)) {

			listCandidate = candidateServiceImpl.filterCandidateByAgeAndSkill(minAge, maxAge, listId, page);
			totalPage = candidateServiceImpl.countPageCandidateByAgeAndSkill(minAge, maxAge, listId);

		} else if (ageIsEmpty(minAge, maxAge) && !listIdIsEmpty(listId) && !contentIsEmpty(content)) {

			listCandidate = candidateServiceImpl.filterCandidateByContentAndSkill(content, listId, page);
			totalPage = candidateServiceImpl.countPageCandidateByContentAndSkill(content, listId);

		} else if (!ageIsEmpty(minAge, maxAge) && listIdIsEmpty(listId) && !contentIsEmpty(content)) {

			listCandidate = candidateServiceImpl.filterCandidateByContentAndAge(content, minAge, maxAge, page);
			totalPage = candidateServiceImpl.countPageCandidateByContentAndAge(content, minAge, maxAge);

		} else if (!ageIsEmpty(minAge, maxAge) && !listIdIsEmpty(listId) && !contentIsEmpty(content)) {

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
		
		List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidateId(candidateDto.getId());
		candidateDto.setListInterview(listInterviewDto);
		
		model.addAttribute("candidate", candidateDto);
		return "candidate/viewInfoDetailCandidate";
	}
	
	@GetMapping(value = "/report-interview/{idInterview}")
	@Secured(value = {"ROLE_INTERVIEWER"})
	public String reportInterviewByIdInterview(@PathVariable(name = "idInterview") int idInterview, Model model) {
		
		InterviewDto interviewDto = null;
		try {
			interviewDto = interviewServiceImpl.readById(idInterview);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("interview", interviewDto);
		return "candidate/reportInterview";
	}
	
	@PostMapping(value = "/report-interview")
	@Secured(value = {"ROLE_INTERVIEWER"})
	public String updateReportInterview(@ModelAttribute(name = "interview") InterviewDto interviewResponse, Model model) {
		
		int idInterview = interviewResponse.getId();
		InterviewDto interviewDto = null;
		try {
			interviewDto = interviewServiceImpl.readById(idInterview);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		interviewDto.setNameInterviewer(interviewResponse.getNameInterviewer());
		interviewDto.setResult(interviewResponse.getResult());
		interviewDto.setNote(interviewResponse.getNote());
		
		interviewServiceImpl.update(interviewDto);
		
		return "redirect:/view-all-candidate";
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
	
	private boolean listIdIsEmpty(List<Integer> listId) {
		
		if(listId == null) {
			return true;
		} else if (listId.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
