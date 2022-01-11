package com.demoipm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demoipm.consts.URLConst;
import com.demoipm.consts.ViewConst;
import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewDto;
import com.demoipm.dto.SkillDto;
import com.demoipm.dto.candidatefilter.CandidateFilter;
import com.demoipm.dto.general.ResponseDto;
import com.demoipm.dto.usermanage.UserUpdateRequestDto;
import com.demoipm.service.CandidateService;
import com.demoipm.service.InterviewService;
import com.demoipm.service.SkillService;
import com.demoipm.service.UserAppService;

@Controller
public class CandidateController {

	@Autowired
	private CandidateService candidateServiceImpl;
	
	@Autowired
	private SkillService skillServiceImpl;
	
	@Autowired
	private InterviewService interviewServiceImpl;
	
	@Autowired
	private UserAppService userAppServiceImpl;

	/**
	 * Method will be show information of all candidate.
	 * @param content
	 * @param minAge
	 * @param maxAge
	 * @param listId
	 * @param page
	 * @param model
	 * @return
	 */
	@GetMapping(value = {URLConst.VIEW_CANDIDATE_URL})
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String viewCandidateInformation(Model model) {

		List<CandidateDto> listCandidate = new ArrayList<CandidateDto>();
		CandidateFilter candidateFilter = new CandidateFilter();
		
		listCandidate = candidateServiceImpl.filter(candidateFilter);
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidateId(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		model.addAttribute("candidateFilter", candidateFilter);
		showAllSkill(model);
		model.addAttribute("listCandidate", listCandidate);
		
		return ViewConst.MANAGE_CANDIDATE_PAGE;
	}
	
	@PostMapping(value = {URLConst.VIEW_CANDIDATE_URL})
	@Secured(value = {"ROLE_HR", "ROLE_INTERVIEWER"})
	public String viewCandidateInformation(
		@ModelAttribute(name = "candidateFilter") CandidateFilter candidateFilter, Model model) {
	
		List<CandidateDto> listCandidate = new ArrayList<CandidateDto>();
		
		listCandidate = candidateServiceImpl.filter(candidateFilter);
		
		for (CandidateDto candidateDto : listCandidate) {
			List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidateId(candidateDto.getId());
			candidateDto.setListInterview(listInterviewDto);
		}
		
		model.addAttribute("candidateFilter", candidateFilter);
		showAllSkill(model);
		model.addAttribute("listCandidate", listCandidate);
		
		return ViewConst.MANAGE_CANDIDATE_PAGE;
	}
	
	/**
	 * Method will be show information of candidate 
	 * @param id
	 * @param model
	 * @return
	 */
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
	
	/**
	 * Method will show information interview of candidate
	 * @param idInterview
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/report-interview/{idInterview}")
	@Secured(value = {"ROLE_INTERVIEWER"})
	public String reportInterviewByIdInterview(@PathVariable(name = "idInterview") int idInterview, Model model) {
		
		InterviewDto interviewDto = null;
		try {
			interviewDto = interviewServiceImpl.readById(idInterview);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		
		if(username != null) {
			ResponseDto responseDto = new ResponseDto();
			UserUpdateRequestDto userUpdateRequestDto = userAppServiceImpl.readUserByUsername(username, responseDto);
			String nameInterviewer = userUpdateRequestDto.getFullName();
			interviewDto.setNameInterviewer(nameInterviewer);
		}
		
		model.addAttribute("interview", interviewDto);
		return "candidate/reportInterview";
	}
	
	/**
	 * Method update result interview
	 * @param interviewResponse
	 * @param model
	 * @return
	 */
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
	
	/**
	 * Add all skill on view
	 * @param model
	 */
	private void showAllSkill(Model model) {
		
		List<SkillDto> listSkillDto = new ArrayList<SkillDto>();
		try {
			listSkillDto = skillServiceImpl.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("listSkill", listSkillDto);
	}
	
}
