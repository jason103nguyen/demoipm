package com.demoipm.controller;

import com.demoipm.dto.EntryTestRequest;
import com.demoipm.dto.QuestionRequest;
import com.demoipm.entities.Question;
import com.demoipm.service.EntryTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class EntryTestController {

    @Autowired
    private EntryTestService entryTestService;

    @Secured("ROLE_HR")
    @GetMapping("/entrytest/create")
    public String showEntryTestForm(Model model){
        model.addAttribute("entryTestRequest",new QuestionRequest());
        return "entrytest/entrytest";
    }

    @Secured("ROLE_HR")
    @GetMapping("/entrytest/question")
    public String showEntryTestQuestion(@RequestParam("skill")String skill,@RequestParam("numberofQuestion") Integer numberofQuestion,Model model){
        List<Question> question = entryTestService.getRandBySkillName(skill,numberofQuestion);
        model.addAttribute("question",question);
        model.addAttribute("skill",skill);
        System.out.println("question: "+ question);
        return "entrytest/entrytest";
    }

    @Secured("ROLE_HR")
    @PostMapping("/entrytest/create")
    public String createEntryTestForm(@ModelAttribute("entryTestRequest") EntryTestRequest entryTestRequest, BindingResult result, Model model){
        model.addAttribute("entryTestRequest",entryTestService.create(entryTestRequest));
        System.out.println("entrytest là : " + entryTestRequest);
        return "entrytest/entrytest";
    }

}
