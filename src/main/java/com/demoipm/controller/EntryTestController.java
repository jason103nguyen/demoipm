package com.demoipm.controller;

import com.demoipm.service.EntryTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class EntryTestController {

    @Autowired
    private EntryTestService entryTestService;


}
