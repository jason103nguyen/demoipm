package com.phuongnt.dto;

import java.util.List;

import com.phuongnt.entities.Job;

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
