package com.phuongnt.dto;

import com.phuongnt.entities.JobSkill;

public class JobSkillDto {

	private int id;
	
	private SkillDto skillDto;
	
	private JobDto jobDto;
	
	public JobSkillDto() {}

	public JobSkillDto(JobSkill jobSkill) {
		super();
		this.id = jobSkill.getId();
		
		SkillDto skillDto = new SkillDto(jobSkill.getSkill());
		this.skillDto = skillDto;
		
		JobDto jobDto = new JobDto(jobSkill.getJob());
		this.jobDto = jobDto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SkillDto getSkillDto() {
		return skillDto;
	}

	public void setSkillDto(SkillDto skillDto) {
		this.skillDto = skillDto;
	}

	public JobDto getJobDto() {
		return jobDto;
	}

	public void setJobDto(JobDto jobDto) {
		this.jobDto = jobDto;
	}

}
