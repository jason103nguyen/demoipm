package com.phuongnt.service;

import java.util.List;

import com.phuongnt.dto.JobDto;

public interface JobService {

	void create(JobDto jobDto);
	
	JobDto readById(int id) throws Exception;
	
	List<JobDto> readAll() throws Exception ;
	
	void update(JobDto jobDto);
	
	void deleteById(int id);
}
