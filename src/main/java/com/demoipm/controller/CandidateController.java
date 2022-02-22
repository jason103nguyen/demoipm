package com.demoipm.controller;

import com.demoipm.consts.PaginationInfoConst;
import com.demoipm.consts.RoleConst;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CandidateController {


    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateController.class);

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
     *
     * @param content
     * @param minAge
     * @param maxAge
     * @param page
     * @param model
     * @return
     */
    @GetMapping(value = {URLConst.VIEW_CANDIDATE_URL})
    @Secured(value = {RoleConst.ROLE_HR, RoleConst.ROLE_INTERVIEWER})
    public String viewCandidateInformation(Model model,
                                           @RequestParam(name = "content", required = false) String content,
                                           @RequestParam(name = "maxAge", required = false) Integer maxAge,
                                           @RequestParam(name = "minAge", required = false) Integer minAge,
                                           @RequestParam(name = "listSkills", required = false) List<Integer> listSkills,
                                           @RequestParam(name = "page", required = false) Integer page) {

        LOGGER.info("Start view candidate information");
        List<CandidateDto> listCandidate;
        CandidateFilter candidateFilter = new CandidateFilter();

        // Set default value for page
        if (page == null) {
            page = 0;
        }

        candidateFilter.setContent(content);
        candidateFilter.setMaxAge(maxAge);
        candidateFilter.setMinAge(minAge);
        candidateFilter.setListSkills(listSkills);

        listCandidate = candidateServiceImpl.filter(candidateFilter, page);

        // Count page
        Integer totalRow = candidateServiceImpl.countRow(candidateFilter);
        Integer totalPage = (int) Math.round((double) totalRow / (double) PaginationInfoConst.MAX_RESULT);

        for (CandidateDto candidateDto : listCandidate) {
            List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidateId(candidateDto.getId());
            candidateDto.setListInterview(listInterviewDto);
        }

        model.addAttribute("totalPage", totalPage);
        model.addAttribute("candidateFilter", candidateFilter);

        // Get all skill
        List<SkillDto> listSkillDto = new ArrayList<>();
        try {
            listSkillDto = skillServiceImpl.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("listSkill", listSkillDto);
        model.addAttribute("listCandidate", listCandidate);

        LOGGER.info("End view candidate information");
        return ViewConst.MANAGE_CANDIDATE_PAGE;
    }

    /**
     * Method will be show information of candidate
     *
     * @param id
     * @param model
     * @return
     */

    @GetMapping(value = "/view-candidate-information/{id}")
    @Secured(value = {RoleConst.ROLE_HR, RoleConst.ROLE_INTERVIEWER})
    public String viewCandidateInfoById(@PathVariable(name = "id") int id, Model model) {

        LOGGER.info("Start view candidate information by id is {}", id);
        CandidateDto candidateDto = null;
        try {
            candidateDto = candidateServiceImpl.readById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<InterviewDto> listInterviewDto = candidateServiceImpl.getListInterviewByCandidateId(candidateDto.getId());
        candidateDto.setListInterview(listInterviewDto);

        model.addAttribute("candidate", candidateDto);

        LOGGER.info("End view candidate information by id is {}", id);
        return "candidate/viewInfoDetailCandidate";
    }

    /**
     * Method will show information interview of candidate
     *
     * @param idInterview
     * @param model
     * @return
     */

    @GetMapping(value = "/report-interview/{idInterview}")
    @Secured(value = {RoleConst.ROLE_INTERVIEWER})
    public String reportInterviewByIdInterview(@PathVariable(name = "idInterview") int idInterview, Model model) {

        LOGGER.info("Start report interview at id interview {}", idInterview);
        InterviewDto interviewDto = null;
        try {
            interviewDto = interviewServiceImpl.readById(idInterview);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username != null) {
            ResponseDto responseDto = new ResponseDto();
            UserUpdateRequestDto userUpdateRequestDto = userAppServiceImpl.readUserByUsername(username, responseDto);
            String nameInterviewer = userUpdateRequestDto.getFullName();
            interviewDto.setNameInterviewer(nameInterviewer);
        }

        model.addAttribute("interview", interviewDto);

        LOGGER.info("End report interview at id interview {}", idInterview);
        return "candidate/reportInterview";
    }

    /**
     * Method update result interview
     *
     * @param interviewResponse
     * @param model
     * @return
     */

    @PostMapping(value = "/report-interview")
    @Secured(value = {RoleConst.ROLE_INTERVIEWER})
    public String updateReportInterview(@ModelAttribute(name = "interview") InterviewDto interviewResponse, Model model) {

        LOGGER.info("Start update report interview");
        int idInterview = interviewResponse.getId();
        InterviewDto interviewDto = null;
        try {
            interviewDto = interviewServiceImpl.readById(idInterview);
        } catch (Exception e) {
            e.printStackTrace();
        }

        interviewDto.setNameInterviewer(interviewResponse.getNameInterviewer());
        interviewDto.setResult(interviewResponse.getResult());
        interviewDto.setNote(interviewResponse.getNote());

        interviewServiceImpl.update(interviewDto);

        LOGGER.info("End update report interview");
        return "redirect:/view-all-candidate";
    }

    /**
     * Add all skill on view
     *
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

    /**
     * Check age is empty
     *
     * @param minAge
     * @param maxAge
     * @return
     */

    private boolean ageIsEmpty(Integer minAge, Integer maxAge) {

        if (minAge == null || maxAge == null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Check content is empty
     *
     * @param content
     * @return
     */

    private boolean contentIsEmpty(String content) {

        if (content == null) {
            return true;
        } else if (content.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check list contain skills selected is empty
     *
     * @param listId
     * @return
     */

    private boolean listIdIsEmpty(List<Integer> listId) {

        if (listId == null) {
            return true;
        } else if (listId.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
