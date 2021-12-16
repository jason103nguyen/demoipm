package com.phuongnt.dto;

import java.util.List;

import com.phuongnt.entities.Job;
import com.phuongnt.entities.JobSkill;

public class JobDto {

	private int id;
	
	private String name;
	
	private List<JobSkillDto> listJobSkillDto;
	
	private CareerDto career;
	
	public JobDto() {}
	
	public JobDto(Job job) {
		super();
		this.id = job.getId();
		this.name = job.getName();
		
		List<JobSkill> listJobSkill = job.getListJobSkill();
		for (JobSkill jobSkill : listJobSkill) {
			JobSkillDto jobSkillDto = new JobSkillDto(jobSkill);
			this.listJobSkillDto.add(jobSkillDto);
		}
		
		CareerDto careerDto = new CareerDto(job.getCareer());
		this.career = careerDto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<JobSkillDto> getListJobSkillDto() {
		return listJobSkillDto;
	}

	public void setListJobSkillDto(List<JobSkillDto> listJobSkillDto) {
		this.listJobSkillDto = listJobSkillDto;
	}

	public CareerDto getCareer() {
		return career;
	}

	public void setCareer(CareerDto career) {
		this.career = career;
	}
	
}
