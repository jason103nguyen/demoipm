package com.demoipm.service;

import java.util.List;
import java.util.Optional;

import com.demoipm.dto.SkillDto;
import com.demoipm.entities.Skill;

public interface SkillService {

	void create(SkillDto skillDto);
	
	SkillDto readById(int id) throws Exception;
	
	List<SkillDto> readAll() throws Exception ;
	
	void update(SkillDto skillDto);
	
	void deleteById(int id);

	Optional<Skill> findByName(String name);
}
