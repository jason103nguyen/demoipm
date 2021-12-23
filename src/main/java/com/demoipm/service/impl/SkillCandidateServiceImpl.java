package com.demoipm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.SkillCandidateDao;
import com.demoipm.dto.SkillCandidateDto;
import com.demoipm.entities.SkillCandidate;
import com.demoipm.service.SkillCandidateService;

@Service
@Transactional
public class SkillCandidateServiceImpl implements SkillCandidateService {

	@Autowired
	private SkillCandidateDao skillCandidateDao;
	
	@Override
	public void create(SkillCandidateDto skillCandidateDto) {

		SkillCandidate skillCandidate = new SkillCandidate(skillCandidateDto);
		skillCandidateDao.save(skillCandidate);
	}

	@Override
	public SkillCandidateDto readById(int id) throws Exception {

		Optional<SkillCandidate> skillCandidate = skillCandidateDao.findById(id);
		SkillCandidateDto skillCandidateDto = null;
		if (skillCandidate.isEmpty()) {
			throw new Exception("The id doesn't exists");
		} else {
			skillCandidateDto = new SkillCandidateDto(skillCandidate.get());
		}
		return skillCandidateDto;
	}

	@Override
	public List<SkillCandidateDto> readAll() throws Exception {

		List<SkillCandidate> listSkillCandidate = (List<SkillCandidate>) skillCandidateDao.findAll();
		List<SkillCandidateDto> listSkillCandidateDto = new ArrayList<SkillCandidateDto>();
		
		if (listSkillCandidate.isEmpty()) {
			
			throw new Exception("This list is empty");
		} else {
			
			for (SkillCandidate skillCandidate : listSkillCandidate) {
				SkillCandidateDto skillCandidateDto = new SkillCandidateDto(skillCandidate);
				listSkillCandidateDto.add(skillCandidateDto);
			}
		}
		
		return listSkillCandidateDto;
	}

	@Override
	public void update(SkillCandidateDto skillCandidateDto) {

		SkillCandidate skillCandidate = new SkillCandidate(skillCandidateDto);
		skillCandidateDao.save(skillCandidate);
	}

	@Override
	public void deleteById(int id) {

		if (skillCandidateDao.existsById(id)) {
			skillCandidateDao.deleteById(id);
		}
	}

}
