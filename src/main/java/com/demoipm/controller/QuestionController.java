package com.demoipm.controller;

import com.demoipm.dto.QuestionRequest;
import com.demoipm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Secured("ROLE_HR")
    @GetMapping("/question/create")
    public String showQuestionForm(Model model){
        model.addAttribute("questionRequest",new QuestionRequest());
        return "entrytest/questionentrytest";
    }

    @Secured("ROLE_HR")
    @PostMapping("/question/create")
    public String createQuestionForm(@ModelAttribute("questionRequest") QuestionRequest questionRequest, BindingResult result, Model model){
        model.addAttribute("questionRequest",questionService.create(questionRequest));
        System.out.println("question là : " + questionRequest);
        return "entrytest/questionentrytest";
    }
}
