package com.demoipm.controller;

import com.demoipm.dto.InterviewRequest;
import com.demoipm.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @GetMapping("/interview/create")
    public String showForm(Model model){
        model.addAttribute("InterviewRequest",new InterviewRequest());
        return "interview/interview";
    }

    @PostMapping("/interview/create")
    public String createForm(@ModelAttribute("InterviewRequest") InterviewRequest interviewRequest, BindingResult result,Model model){
        model.addAttribute("InterviewRequest",interviewRequest);
        System.out.println(interviewRequest);
        return "interview/setup_success";
    }
}
