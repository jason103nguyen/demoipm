package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.SkillDto;
import com.demoipm.entities.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SkillService {

	void create(SkillDto skillDto);
	
	SkillDto readById(int id) throws Exception;
	
	List<SkillDto> readAll() throws Exception ;
	
	void update(SkillDto skillDto);
	
	void deleteById(int id);

	Page<Skill> paginationSkills(int page, int size);

	List<SkillDto> findByNameSkillDto(String name) throws Exception;

	Page<Skill> findByNameSkill(String name, int page, int size) throws Exception;
}
