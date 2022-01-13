package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.SkillCandidateDto;

public interface SkillCandidateService {

	void create(SkillCandidateDto skillCandidateDto);
	
	SkillCandidateDto readById(int id) throws Exception;
	
	List<SkillCandidateDto> readAll() throws Exception ;
	
	void update(SkillCandidateDto skillCandidateDto);
	
	void deleteById(int id);
}
