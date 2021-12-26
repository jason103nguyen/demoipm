package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.InterviewDto;
import com.demoipm.dto.InterviewRequest;

public interface InterviewService {

	void create(InterviewRequest interviewRequest);
	
	/*InterviewDto readById(int id) throws Exception;
	
	List<InterviewDto> readAll() throws Exception ;
	
	void update(InterviewDto interviewDto);*/
	
	void deleteById(int id);
}
