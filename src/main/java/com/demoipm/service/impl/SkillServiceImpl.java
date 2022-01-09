package com.demoipm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.demoipm.dto.recruitmentmanage.SkillSelectionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.SkillDao;
import com.demoipm.dto.SkillDto;
import com.demoipm.entities.Skill;
import com.demoipm.service.SkillService;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SkillService.class);

	@Autowired
	private SkillDao skillDao;
	
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
	public List<SkillSelectionDto> getAllSkill(Integer jobId) {
		LOGGER.info("Start get all skill");
		try {
			List<Skill> skillEntities = skillDao.getAllSkillOfJob(jobId);
			List<SkillSelectionDto> skillDtos = skillEntities.stream()
					.map(skill ->
							new SkillSelectionDto()
									.setId(skill.getId())
									.setSkill(skill.getName()))
					.collect(Collectors.toList());
			return skillDtos;
		} catch (Throwable t) {
			LOGGER.error("Has error when getAllSkill", t);
			return new ArrayList<>();
		}
	}

}
