package com.demoipm.controller;

import com.demoipm.consts.MessageConst;
import com.demoipm.consts.URLConst;
import com.demoipm.consts.ViewConst;
import com.demoipm.dao.SkillDao;
import com.demoipm.dto.SkillDto;
import com.demoipm.entities.Skill;
import com.demoipm.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demoipm.dto.UserAppDto;
import com.demoipm.service.UserAppService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SkillController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkillController.class);

    @Autowired
    private SkillService skillService;

    private int pgStart = 1;
    private int pgEnd = 5;
    private int pgNo = 0;
    private int pageSize = 5;
    private String searchKey = "";

    @ModelAttribute
    public void getAttribute(Model model) {
        model.addAttribute("pageNo", pgNo);
        model.addAttribute("pageStart", pgStart);
        model.addAttribute("pageEnd", pgEnd);
        model.addAttribute("searchKey", searchKey);
    }

    @Secured("ROLE_HR")
    @RequestMapping(URLConst.PROCESS_MANAGE_SKILL_URL)
    public String manageSkillPage() {
        return "redirect:".concat(URLConst.MANAGE_SKILL_URL + "?pageNo=" + pgNo + "&pageStart=" + pgStart + "&pageEnd=" + pgEnd);
    }

    @Secured("ROLE_HR")
    @RequestMapping(URLConst.MANAGE_SKILL_URL)
    public String getSkillListPage(@RequestParam("pageNo") int pageNo,
                                   @RequestParam("pageStart") int pageStart,
                                   @RequestParam("pageEnd") int pageEnd,
                                   Model model) throws Exception {
        LOGGER.info("Start get skill list");
        pgNo = pageNo;
        pgStart = pageStart;
        pgEnd = pageEnd;
        Page<Skill> skillPage = skillService.paginationSkills(pageNo, pageSize);
        pageSkills(skillPage, pageNo, pageStart, pageEnd, model);
        LOGGER.info("End get skill list");
        return ViewConst.MANAGE_SKILL_PAGE;
    }

    @Secured("ROLE_HR")
    @GetMapping(URLConst.SEARCH_SKILL_URL)
    public String findBySkillName(@RequestParam("searchKey") String name,
                                  @RequestParam("pageNo") int pageNo,
                                  @RequestParam("pageStart") int pageStart,
                                  @RequestParam("pageEnd") int pageEnd,
                                  Model model) throws Exception {
        LOGGER.info("Start search skill name {}");
        if (!"".equals(name.trim())) {
            model.addAttribute("searchKey", name);
            Page<Skill> skillPage = skillService.findByNameSkill(name, pageNo, pageSize);
            pageSkills(skillPage, pageNo, pageStart, pageEnd, model);
            return ViewConst.MANAGE_SKILL_PAGE;
        }
        searchKey = "";
        LOGGER.info("End search skill name {}");
        return "redirect:".concat(URLConst.MANAGE_SKILL_URL + "?pageNo=" + pgNo + "&pageStart=" + pgStart + "&pageEnd=" + pgEnd);
    }

    @Secured("ROLE_HR")
    @GetMapping(URLConst.EDIT_SKILL_PAGE_URL)
    public String getSkillDetailUpdatePage(@RequestParam(name = "id", required = false) Integer id, Model model) throws Exception {
        SkillDto skill = null;
        if (id != null) {
            skill = skillService.readById(id);
        } else {
            skill = new SkillDto();
        }
        model.addAttribute("skill", skill);
        return ViewConst.EDIT_SKILL_PAGE;
    }

    @Secured("ROLE_HR")
    @PostMapping(URLConst.EDIT_SKILL_PAGE_URL)
    public String updateSkillDetail(@ModelAttribute("skill") @Valid SkillDto skillDto,
                                    BindingResult result, Model model) throws Exception {
//        String message = "";
        String error = "";
        if (result.hasErrors()) {
            model.addAttribute("responseStatus", "Error");
            return ViewConst.EDIT_SKILL_PAGE;
        } else {
            if(skillService.findByName(skillDto.getName()) != null) {
                error = MessageConst.SKILL_DUPLICATE;
                model.addAttribute("responseError", error);
                return ViewConst.EDIT_SKILL_PAGE;
            } else {
                if (skillDto.getId() != 0) {
                    // Case Update existed skill
                    skillService.update(skillDto);
//                    message = MessageConst.UPDATE_SKILL_SUCCESS_MESSAGE;
                } else {
                    // Case Create new skill
                    skillService.create(skillDto);
//                    message = MessageConst.CREATE_SKILL_SUCCESS_MESSAGE;
                }
            }
//            model.addAttribute("responseStatus", message);
        }
        return ViewConst.MANAGE_SKILL_PAGE;
    }

    @Secured("ROLE_HR")
    @GetMapping(URLConst.DELETE_SKILL_URL)
    public String deleteSkillById(@RequestParam("id") Integer id, Model model) throws Exception {
        LOGGER.info("Start delete skill id {}");
        skillService.deleteById(id);
        LOGGER.info("End delete skill id {}");
        return "redirect:".concat(URLConst.MANAGE_SKILL_URL + "?pageNo=" + pgNo + "&pageStart=" + pgStart + "&pageEnd=" + pgEnd);
    }

    private void pageSkills(Page<Skill> skillPage, int pageNo, int pageStart, int pageEnd, Model model) {
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageStart", pageStart);
        model.addAttribute("pageEnd", skillPage.getTotalPages() <= 5 ? skillPage.getTotalPages() : pageEnd);
        model.addAttribute("skillList", skillPage.getContent());
        model.addAttribute("totalPages", skillPage.getTotalPages());
    }
}
