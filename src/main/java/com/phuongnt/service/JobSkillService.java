package com.phuongnt.service;

import java.util.List;

import com.phuongnt.dto.JobSkillDto;

public interface JobSkillService {

	void create(JobSkillDto jobSkillDto);
	
	JobSkillDto readById(int id) throws Exception;
	
	List<JobSkillDto> readAll() throws Exception ;
	
	void update(JobSkillDto jobSkillDto);
	
	void deleteById(int id);
}
