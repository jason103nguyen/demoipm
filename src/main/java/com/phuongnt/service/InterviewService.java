package com.phuongnt.service;

import java.util.List;

import com.phuongnt.dto.InterviewDto;

public interface InterviewService {

	void create(InterviewDto interviewDto);
	
	InterviewDto readById(int id) throws Exception;
	
	List<InterviewDto> readAll() throws Exception ;
	
	void update(InterviewDto interviewDto);
	
	void deleteById(int id);
}
