package com.demoipm.controller;

import com.demoipm.consts.URLConst;
import com.demoipm.consts.ViewConst;
import com.demoipm.dto.recruitmentmanage.CareerSelectionDto;
import com.demoipm.dto.recruitmentmanage.JobSelectionDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentCreateRequestDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentDetailDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentListPageResponseDto;
import com.demoipm.dto.recruitmentmanage.SkillSelectionDto;
import com.demoipm.service.CareerService;
import com.demoipm.service.JobService;
import com.demoipm.service.RecruitmentService;
import com.demoipm.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RecruitmentManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruitmentManageController.class);

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private CareerService careerService;

    @Autowired
    private JobService jobService;

    @Autowired
    private SkillService skillService;

    @Secured("ROLE_HR")
    @RequestMapping(URLConst.MANAGE_RECRUITMENT_URL)
    public String getUserList(@RequestParam(value = "pageNo", required = false) Integer pageNo,
                              @RequestParam(value = "entriesNo", required = false) Integer entriesNo,
                              Model model) {
        LOGGER.info("Start get recruitment list with search word {}, page {}, entries no {}", pageNo, entriesNo);
        entriesNo = setDefaultEntriesNo(entriesNo);
        pageNo = setDefaultPageNo(pageNo);
        RecruitmentListPageResponseDto responseDto = recruitmentService.readByCondition(pageNo, entriesNo);
        model.addAttribute("response", responseDto);
        LOGGER.info("End get recruitment list with search word {}, page {}, entries no {}", pageNo, entriesNo);
        return ViewConst.MANAGE_RECRUITMENT_PAGE;
    }

    @Secured("ROLE_HR")
    @GetMapping(URLConst.API_GET_RECRUITMENT_DETAIL_URL)
    public ResponseEntity<RecruitmentDetailDto> getUserList(@RequestParam(value = "id") Integer id) {
        LOGGER.info("Start get recruitment detail with id {}", id);
        RecruitmentDetailDto responseDto = recruitmentService.getRecruimentDetailById(id);
        LOGGER.info("End get recruitment detail with id {}", id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Secured("ROLE_HR")
    @RequestMapping(URLConst.CREATE_RECRUITMENT_PAGE_URL)
    public String createRecruitmentPage(Model model) {
        LOGGER.info("Start show create recruitment page");
        RecruitmentCreateRequestDto recruitment = new RecruitmentCreateRequestDto();
        model.addAttribute("recruitment", recruitment);
        return ViewConst.CREATE_RECRUITMENT_PAGE;
    }

    @Secured("ROLE_HR")
    @PostMapping(URLConst.PROCESS_CREATE_RECRUITMENT_URL)
    public String processCreateRecruitment(@Valid @ModelAttribute("recruitment") RecruitmentCreateRequestDto requestDto, BindingResult result) {
        LOGGER.info("Start process create recruitment");
        if (result.hasErrors()) {
            return ViewConst.CREATE_RECRUITMENT_PAGE;
        }
        return URLConst.REDIRECT + URLConst.MANAGE_RECRUITMENT_URL;
    }

    @Secured("ROLE_HR")
    @GetMapping(URLConst.API_GET_CAREER_SELECTION_URL)
    public ResponseEntity<List<CareerSelectionDto>> getCareerSelection() {
        LOGGER.info("Start get list career selection");
        List<CareerSelectionDto> careerList = careerService.getAllCareer();
        LOGGER.info("End get list career selection");
        return new ResponseEntity<>(careerList, HttpStatus.OK);
    }

    @Secured("ROLE_HR")
    @GetMapping(URLConst.API_GET_JOB_SELECTION_URL)
    public ResponseEntity<List<JobSelectionDto>> getJobSelection(@RequestParam("careerId") Integer careerId) {
        LOGGER.info("Start get list job selection");
        List<JobSelectionDto> jobList = jobService.getAllJobOfCareer(careerId);
        LOGGER.info("End get list job selection");
        return new ResponseEntity<>(jobList, HttpStatus.OK);
    }

    @Secured("ROLE_HR")
    @GetMapping(URLConst.API_GET_SKILL_SELECTION_URL)
    public ResponseEntity<List<SkillSelectionDto>> getSkillSelection(@RequestParam("jobId") Integer jobId) {
        LOGGER.info("Start get list skill selection");
        List<SkillSelectionDto> skillList = skillService.getAllSkill(jobId);
        LOGGER.info("End get list skill selection");
        return new ResponseEntity<>(skillList, HttpStatus.OK);
    }

    private Integer setDefaultPageNo(Integer pageNo) {
        if (pageNo == null || pageNo <= 0) {
            return 1;
        }
        return pageNo;
    }

    private Integer setDefaultEntriesNo(Integer entriesNo) {
        if (entriesNo == null || entriesNo <= 0) {
            return 10;
        }
        return entriesNo;
    }

}
