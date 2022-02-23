package com.demoipm.service.impl;

import com.demoipm.dao.SkillDao;
import com.demoipm.dao.SkillRepository;
import com.demoipm.dto.SkillDto;
import com.demoipm.dto.recruitmentmanage.SkillSelectionDto;
import com.demoipm.entities.Skill;
import com.demoipm.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SkillServiceImpl.class);

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
		SkillDto skillDto;
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
		List<SkillDto> listSkillDto = new ArrayList<>();

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
	public Optional<Skill> findByName(String name) {
		return skillDao.findByName(name);
	}

	@Override
	public Page<Skill> paginationSkills(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return skillRepository.findAll(pageable);
	}

	@Override
	public List<SkillDto> findByNameSkillDto(String name) throws Exception {
		return skillRepository.findByNameStartingWith(name).stream().map(SkillDto::new).collect(Collectors.toList());
	}

	@Override
	public Page<Skill> findByNameSkill(String name, int page, int size) throws Exception {
		Pageable pageable = PageRequest.of(page, size);
		return skillRepository.findByNameStartingWith(name, pageable);
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
