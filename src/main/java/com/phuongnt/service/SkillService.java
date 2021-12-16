package com.phuongnt.service;

import java.util.List;

import com.phuongnt.dto.SkillDto;

public interface SkillService {

	void create(SkillDto skillDto);
	
	SkillDto readById(int id) throws Exception;
	
	List<SkillDto> readAll() throws Exception ;
	
	void update(SkillDto skillDto);
	
	void deleteById(int id);
}
