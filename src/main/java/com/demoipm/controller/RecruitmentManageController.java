package com.demoipm.controller;

import com.demoipm.consts.URLConst;
import com.demoipm.consts.ViewConst;
import com.demoipm.dto.recruitmentmanage.RecruitmentDetailDTO;
import com.demoipm.dto.recruitmentmanage.RecruitmentListPageResponseDto;
import com.demoipm.service.RecruitmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecruitmentManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecruitmentManageController.class);

    @Autowired
    private RecruitmentService recruitmentService;

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
    @RequestMapping(URLConst.API_GET_RECRUITMENT_DETAIL_URL)
    public ResponseEntity<RecruitmentDetailDTO> getUserList(@RequestParam(value = "id") Integer id) {
        LOGGER.info("Start get recruitment detail with id {}", id);
        RecruitmentDetailDTO responseDto = recruitmentService.getRecruimentDetailById(id);
        LOGGER.info("End get recruitment detail with id {}", id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
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
