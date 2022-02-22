package com.demoipm.service;

import com.demoipm.dto.InterviewDto;
import com.demoipm.dto.InterviewRequest;
import com.demoipm.entities.Interview;

import java.util.List;

public interface InterviewService {

	Interview create(InterviewRequest interviewRequest);

	InterviewDto readById(int id) throws Exception;

	List<InterviewDto> readAll() throws Exception ;

	void update(InterviewDto interviewDto);
	
	void deleteById(int id);
}
