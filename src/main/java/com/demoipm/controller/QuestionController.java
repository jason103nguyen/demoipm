package com.demoipm.controller;

import com.demoipm.dto.InterviewRequest;
import com.demoipm.dto.QuestionEntryTestRequest;
import com.demoipm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/question/create")
    public String showQuestionForm(Model model){
        model.addAttribute("questionRequest",new QuestionEntryTestRequest());
        return "entrytest/questionentrytest";
    }

    @PostMapping("/question/create")
    public String createQuestionForm(@ModelAttribute("questionRequest") QuestionEntryTestRequest questionEntryTestRequest, BindingResult result, Model model){
        model.addAttribute("questionRequest",questionService.create(questionEntryTestRequest));
        System.out.println(questionEntryTestRequest);
        return "entrytest/questionentrytest";
    }
}
