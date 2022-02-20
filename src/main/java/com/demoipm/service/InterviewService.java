package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.InterviewDto;
import com.demoipm.dto.InterviewRequest;
import com.demoipm.entities.Interview;

public interface InterviewService {

	Interview create(InterviewRequest interviewRequest);
	
	void deleteById(int id);
}
