package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.JobDto;
import com.demoipm.dto.recruitmentmanage.JobSelectionDto;

public interface JobService {

	void create(JobDto jobDto);
	
	JobDto readById(int id) throws Exception;
	
	List<JobDto> readAll() throws Exception ;
	
	void update(JobDto jobDto);
	
	void deleteById(int id);

	/**
	 * Get all job info of career
	 * @return
	 */
    List<JobSelectionDto> getAllJobOfCareer(Integer careerId);
}
