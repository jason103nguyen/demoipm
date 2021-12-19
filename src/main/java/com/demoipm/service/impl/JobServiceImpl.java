package com.demoipm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.JobDao;
import com.demoipm.dto.JobDto;
import com.demoipm.entities.Job;
import com.demoipm.service.JobService;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private JobDao jobDao;
	
	@Override
	public void create(JobDto jobDto) {

		Job job = new Job(jobDto);
		jobDao.save(job);
	}

	@Override
	public JobDto readById(int id) throws Exception {

		Optional<Job> job = jobDao.findById(id);
		JobDto jobDto = null;
		if (job.isEmpty()) {
			throw new Exception("The id doesn't exists");
		} else {
			jobDto = new JobDto(job.get());
		}
		return jobDto;
	}

	@Override
	public List<JobDto> readAll() throws Exception {

		List<Job> listJob = (List<Job>) jobDao.findAll();
		List<JobDto> listJobDto = new ArrayList<JobDto>();
		
		if (listJob.isEmpty()) {
			
			throw new Exception("This list is empty");
		} else {
			
			for (Job job : listJob) {
				JobDto jobDto = new JobDto(job);
				listJobDto.add(jobDto);
			}
		}
		
		return listJobDto;
	}

	@Override
	public void update(JobDto jobDto) {

		Job job = new Job(jobDto);
		jobDao.save(job);
	}

	@Override
	public void deleteById(int id) {

		if (jobDao.existsById(id)) {
			jobDao.deleteById(id);
		}
	}

}
