package com.demoipm.controller;

import com.demoipm.dto.InterviewRequest;
import com.demoipm.service.EntryTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EntryTestController {

    @Autowired
    private EntryTestService entryTestService;


}
