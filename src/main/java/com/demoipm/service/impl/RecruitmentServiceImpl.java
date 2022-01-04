package com.demoipm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.RecruitmentDao;
import com.demoipm.dto.RecruitmentDto;
import com.demoipm.dto.SkillDto;
import com.demoipm.entities.Recruitment;
import com.demoipm.entities.RecruitmentSkill;
import com.demoipm.entities.Skill;
import com.demoipm.service.RecruitmentService;

@Service
@Transactional
public class RecruitmentServiceImpl implements RecruitmentService {

	@Autowired
	private RecruitmentDao recruitmentDao;
	
	@Override
	public void create(RecruitmentDto recruitmentDto) {

		Recruitment recruitment = new Recruitment(recruitmentDto);
		recruitmentDao.save(recruitment);
	}

	@Override
	public RecruitmentDto readById(int id) throws Exception {

		Optional<Recruitment> recruitment = recruitmentDao.findById(id);
		RecruitmentDto recruitmentDto = null;
		if (!recruitment.isPresent()) {
			throw new Exception("The id doesn't exists");
		} else {
			recruitmentDto = new RecruitmentDto(recruitment.get());
		}
		return recruitmentDto;
	}

	@Override
	public List<RecruitmentDto> readAll() throws Exception {

		List<Recruitment> listRecruitment = (List<Recruitment>) recruitmentDao.findAll();
		List<RecruitmentDto> listRecruitmentDto = new ArrayList<RecruitmentDto>();
		
		if (listRecruitment.isEmpty()) {
			
			throw new Exception("This list is empty");
		} else {
			
			for (Recruitment recruitment : listRecruitment) {
				RecruitmentDto recruitmentDto = new RecruitmentDto(recruitment);
				listRecruitmentDto.add(recruitmentDto);
			}
		}
		
		return listRecruitmentDto;
	}
	
	@Override
	public List<SkillDto> readAllSkillByRecruitment(int id) throws Exception {

		Optional<Recruitment> recruitment = recruitmentDao.findById(id);
		List<SkillDto> listSkill = null;
		if (!recruitment.isPresent()) {
			throw new Exception("The id doesn't exists");
		} else {
			List<RecruitmentSkill> listRecruitmentSkill = recruitment.get().getListRecruitmentSkill();
			listSkill = new ArrayList<SkillDto>();
			for(RecruitmentSkill recruitmentSkill : listRecruitmentSkill) {
				Skill skill = recruitmentSkill.getSkill();
				SkillDto skillDto = new SkillDto(skill);
				listSkill.add(skillDto);
			}
			
		}
		return listSkill;
	}

	@Override
	public void update(RecruitmentDto recruitmentDto) {

		Recruitment recruitment = new Recruitment(recruitmentDto);
		recruitmentDao.save(recruitment);
	}

	@Override
	public void deleteById(int id) {

		if (recruitmentDao.existsById(id)) {
			recruitmentDao.deleteById(id);
		}
	}

}
