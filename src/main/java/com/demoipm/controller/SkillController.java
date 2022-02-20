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

    @Autowired
    private UserAppService userAppService;

    @GetMapping(value = "/user-info")
    public String userInfo() {

        return "userInfo";
    }

    @GetMapping(value = "/candidate-info")
    public String candidateInfo() {

        return "candidateInfo";
    }

    @GetMapping(value = "/403")
    public String error403() {

        return "403Page";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {

        model.addAttribute("userApp", new UserAppDto());
        return "register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute(name = "userApp") UserAppDto userApp) {

        System.out.println("ID: " + userApp.getId());
        userAppService.create(userApp);
        return "homepage";
    }

    @GetMapping(value = "/login")
    public String login() {

        return "login";
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillController.class);

    @Autowired
    private SkillService skillService;

    private int pgStart = 1;
    private int pgEnd = 5;
    private int pgNo = 0;
    private int pageSize = 5;

    @ModelAttribute
    public void getAttribute(Model model) {
        model.addAttribute("pageNo", pgNo);
        model.addAttribute("pageStart", pgStart);
        model.addAttribute("pageEnd", pgEnd);
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
        List<Skill> skillList = skillPage.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageStart", pageStart);
        model.addAttribute("pageEnd", pageEnd);
        model.addAttribute("skillList", skillList);
        model.addAttribute("totalPages", skillPage.getTotalPages());
        LOGGER.info("End get skill list");
        return ViewConst.MANAGE_SKILL_PAGE;
    }

    @Secured("ROLE_HR")
    @GetMapping(URLConst.SEARCH_SKILL_URL)
    public String findBySkillName(@RequestParam("searchKey") String name, Model model) throws Exception {
        LOGGER.info("Start search skill name {}");
        if (!"".equals(name.trim())) {
            List<SkillDto> skillList = skillService.findByNameSkillDto(name);
            if (skillList != null) {
                model.addAttribute("skillList", skillList);
                return ViewConst.MANAGE_SKILL_PAGE;
            }
        }
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
        String message = "";
        if (result.hasErrors()) {
            model.addAttribute("responseStatus", "Error");
            return ViewConst.EDIT_SKILL_PAGE;
        } else {
            if (skillDto.getId() != 0) {
                // Case Update existed skill
                skillService.update(skillDto);
                message = MessageConst.UPDATE_SKILL_SUCCESS_MESSAGE;

            } else {
                // Case Create new skill
                skillService.create(skillDto);
                message = MessageConst.CREATE_SKILL_SUCCESS_MESSAGE;
            }
            model.addAttribute("responseStatus", message);
        }
        return ViewConst.EDIT_SKILL_PAGE;
    }

    @Secured("ROLE_HR")
    @GetMapping(URLConst.DELETE_SKILL_URL)
    public String deleteSkillById(@RequestParam("id") Integer id, Model model) throws Exception {
        LOGGER.info("Start delete skill id {}");
        skillService.deleteById(id);
        LOGGER.info("End delete skill id {}");
        return "redirect:".concat(URLConst.MANAGE_SKILL_URL + "?pageNo=" + pgNo + "&pageStart=" + pgStart + "&pageEnd=" + pgEnd);
    }

}
