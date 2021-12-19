package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.JobDto;

public interface JobService {

	void create(JobDto jobDto);
	
	JobDto readById(int id) throws Exception;
	
	List<JobDto> readAll() throws Exception ;
	
	void update(JobDto jobDto);
	
	void deleteById(int id);
}
