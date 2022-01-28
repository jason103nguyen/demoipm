package com.demoipm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.demoipm.dao.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demoipm.dao.SkillDao;
import com.demoipm.dto.SkillDto;
import com.demoipm.entities.Skill;
import com.demoipm.service.SkillService;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillDao skillDao;

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public void create(SkillDto skillDto) {

		Skill skill = new Skill(skillDto);
		skillDao.save(skill);
	}

	@Override
	public SkillDto readById(int id) throws Exception {

		Optional<Skill> skill = skillDao.findById(id);
		SkillDto skillDto = null;
		if (!skill.isPresent()) {
			throw new Exception("The id doesn't exists");
		} else {
			skillDto = new SkillDto(skill.get());
		}
		return skillDto;
	}

	@Override
	public List<SkillDto> readAll() throws Exception {

		List<Skill> listSkill = (List<Skill>) skillDao.findAll();
		List<SkillDto> listSkillDto = new ArrayList<SkillDto>();
		
		if (listSkill.isEmpty()) {
			
			throw new Exception("This list is empty");
		} else {
			
			for (Skill skill : listSkill) {
				SkillDto skillDto = new SkillDto(skill);
				listSkillDto.add(skillDto);
			}
		}
		
		return listSkillDto;
	}

	@Override
	public void update(SkillDto skillDto) {

		Skill skill = new Skill(skillDto);
		skillDao.save(skill);
	}

	@Override
	public void deleteById(int id) {

		if (skillDao.existsById(id)) {
			skillDao.deleteById(id);
		}
	}

	@Override
	public Page<Skill> paginationSkills(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return skillRepository.findAll(pageable);
	}

	@Override
	public List<SkillDto> findByNameSkillDto(String name) throws Exception {
		return skillRepository.findByNameStartingWith(name).stream().map(skill -> {
			SkillDto skillDto = new SkillDto(skill);
			return skillDto;
		}).collect(Collectors.toList());
	}

	@Override
	public Page<Skill> findByNameSkill(String name, int page, int size) throws Exception {
		Pageable pageable = PageRequest.of(page, size);
		return (Page<Skill>) skillRepository.findByNameStartingWith(name, pageable);
	}


}
