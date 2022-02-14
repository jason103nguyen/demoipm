package com.demoipm.controller;

import com.demoipm.consts.MessageConst;
import com.demoipm.consts.URLConst;
import com.demoipm.consts.ViewConst;
import com.demoipm.dto.general.DatatableParamRequestDTO;
import com.demoipm.dto.general.DatatableResponseDTO;
import com.demoipm.dto.recruitmentmanage.CareerSelectionDto;
import com.demoipm.dto.recruitmentmanage.JobSelectionDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentCreateRequestDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentDetailDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentResponseDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentSaveResponseDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentUpdateRequestDto;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public String getUserList() {
        return ViewConst.MANAGE_RECRUITMENT_PAGE;
    }

    @RequestMapping(URLConst.API_GET_RECRUITMENT_BY_CONDITION_URL)
    public ResponseEntity<DatatableResponseDTO> getRecruitmentByCondition(@RequestBody DatatableParamRequestDTO request) {
        LOGGER.info("Start get recruitment by condition");
        DatatableResponseDTO<RecruitmentResponseDto> response = recruitmentService.readByCondition(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(URLConst.API_GET_RECRUITMENT_DETAIL_URL)
    public ResponseEntity<RecruitmentDetailDto> getRecruitmentDetailList(@RequestParam(value = "id") Integer id) {
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
    @RequestMapping(URLConst.UPDATE_RECRUITMENT_PAGE_URL)
    public String updateRecruitmentPage(@RequestParam(value = "id") Integer id,
                                        Model model) {
        LOGGER.info("Start show update recruitment page");
        RecruitmentUpdateRequestDto recruitment = recruitmentService.getRecruitmentUpdateInfo(id);
        model.addAttribute("recruitment", recruitment);
        return ViewConst.UPDATE_RECRUITMENT_PAGE;
    }

    @Secured("ROLE_HR")
    @PostMapping(URLConst.API_PROCESS_CREATE_RECRUITMENT_URL)
    public ResponseEntity<RecruitmentSaveResponseDto> processCreateRecruitment(@Valid @ModelAttribute("recruitment") RecruitmentCreateRequestDto requestDto, BindingResult result) {
        LOGGER.info("Start process create recruitment");
        RecruitmentSaveResponseDto responseDto = validateSaveRecruitmentRequest(requestDto, result);
        if (responseDto.hasError()) {
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        responseDto = recruitmentService.createRecruitment(requestDto);
        if (responseDto.hasError()) {
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @Secured("ROLE_HR")
    @PostMapping(URLConst.API_PROCESS_UPDATE_RECRUITMENT_URL)
    public ResponseEntity<RecruitmentSaveResponseDto> processUpdateRecruitment(@Valid @ModelAttribute("recruitment") RecruitmentUpdateRequestDto requestDto,
                                                                               BindingResult result) {
        LOGGER.info("Start process update recruitment");
        RecruitmentSaveResponseDto responseDto = validateSaveRecruitmentRequest(requestDto, result);
        if (responseDto.hasError()) {
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        responseDto = recruitmentService.updateRecruitment(requestDto);
        if (responseDto.hasError()) {
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(responseDto, HttpStatus.OK);
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

    @Secured("ROLE_HR")
    @DeleteMapping(URLConst.API_DELETE_RECRUITMENT_URL)
    public ResponseEntity deleteRecruitment(@RequestParam("id") Integer recruitmentId) {
        LOGGER.info("Start deleteRecruitment with id {}", recruitmentId);
        recruitmentService.deleteById(recruitmentId);
        LOGGER.info("End deleteRecruitment with id {}", recruitmentId);
        return new ResponseEntity(HttpStatus.OK);
    }

    private RecruitmentSaveResponseDto validateSaveRecruitmentRequest(RecruitmentCreateRequestDto requestDto, BindingResult result) {
        RecruitmentSaveResponseDto responseDto = new RecruitmentSaveResponseDto();
        if (result.hasErrors()) {
            responseDto.setError(true);
            responseDto.setFieldErrors(result.getFieldErrors());
            return responseDto;
        }
        List<FieldError> fieldErrors = new ArrayList<>();
        if (requestDto.getStartDate().isAfter(requestDto.getEndDate())) {
            FieldError startDateError = new FieldError("recruitment", "startDate", MessageConst.RECRUITMENT_DATE_PAIR_INVALID);
            fieldErrors.add(startDateError);
        }
        if (requestDto.getMinSalary() > requestDto.getMaxSalary()) {
            FieldError minSalaryError = new FieldError("recruitment", "minSalary", MessageConst.SALARY_PAIR_INVALID);
            fieldErrors.add(minSalaryError);
        }
        if (fieldErrors.size() > 0) {
            responseDto.setError(true);
            responseDto.setFieldErrors(fieldErrors);
        }
        return responseDto;
    }

}
