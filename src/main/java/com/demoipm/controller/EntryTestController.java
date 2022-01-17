package com.demoipm.controller;

import com.demoipm.dto.EntryTestRequest;
import com.demoipm.dto.QuestionEntryTestRequest;
import com.demoipm.service.EntryTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EntryTestController {

    @Autowired
    private EntryTestService entryTestService;

    @GetMapping("/entrytest/create")
    public String showEntryTestForm(@RequestParam("skillName")String skillName, Model model){
        model.addAttribute("entryTestRequest",new QuestionEntryTestRequest());
        return "entrytest/entrytest";
    }

    @PostMapping("/entrytest/create")
    public String createQuestionForm(@ModelAttribute("entryTestRequest") EntryTestRequest entryTestRequest, BindingResult result, Model model){
        model.addAttribute("entryTestRequest",entryTestService.create(entryTestRequest));
        System.out.println("question là : " + entryTestRequest);
        return "entrytest/entrytest";
    }

}
