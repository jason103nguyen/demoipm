package com.demoipm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.JobSkillDao;
import com.demoipm.dto.JobSkillDto;
import com.demoipm.entities.JobSkill;
import com.demoipm.service.JobSkillService;

@Service
@Transactional
public class JobSkillServiceImpl implements JobSkillService {

	@Autowired
	private JobSkillDao jobSkillDao;
	
	@Override
	public void create(JobSkillDto jobSkillDto) {

		JobSkill jobSkill = new JobSkill(jobSkillDto);
		jobSkillDao.save(jobSkill);
	}

	@Override
	public JobSkillDto readById(int id) throws Exception {

		Optional<JobSkill> jobSkill = jobSkillDao.findById(id);
		JobSkillDto jobSkillDto = null;
		if (jobSkill.isEmpty()) {
			throw new Exception("The id doesn't exists");
		} else {
			jobSkillDto = new JobSkillDto(jobSkill.get());
		}
		return jobSkillDto;
	}

	@Override
	public List<JobSkillDto> readAll() throws Exception {

		List<JobSkill> listJobSkill = (List<JobSkill>) jobSkillDao.findAll();
		List<JobSkillDto> listJobSkillDto = new ArrayList<JobSkillDto>();
		
		if (listJobSkill.isEmpty()) {
			
			throw new Exception("This list is empty");
		} else {
			
			for (JobSkill jobSkill : listJobSkill) {
				JobSkillDto jobSkillDto = new JobSkillDto(jobSkill);
				listJobSkillDto.add(jobSkillDto);
			}
		}
		
		return listJobSkillDto;
	}

	@Override
	public void update(JobSkillDto jobSkillDto) {

		JobSkill jobSkill = new JobSkill(jobSkillDto);
		jobSkillDao.save(jobSkill);
	}

	@Override
	public void deleteById(int id) {

		if (jobSkillDao.existsById(id)) {
			jobSkillDao.deleteById(id);
		}
	}

}
