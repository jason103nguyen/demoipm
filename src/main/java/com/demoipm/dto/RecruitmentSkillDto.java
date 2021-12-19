package com.demoipm.dto;

import com.demoipm.entities.RecruitmentSkill;

public class RecruitmentSkillDto {

	private int id;
	
	private RecruitmentDto recruitmentDto;
	
	private SkillDto skillDto;
	
	public RecruitmentSkillDto() {}

	public RecruitmentSkillDto(RecruitmentSkill recruitmentSkill) {
		super();
		
		RecruitmentDto recruitmentDto = new RecruitmentDto(recruitmentSkill.getRecruitment());
		this.recruitmentDto = recruitmentDto;
		
		SkillDto skillDto = new SkillDto(recruitmentSkill.getSkill());
		this.skillDto = skillDto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RecruitmentDto getRecruitmentDto() {
		return recruitmentDto;
	}

	public void setRecruitmentDto(RecruitmentDto recruitmentDto) {
		this.recruitmentDto = recruitmentDto;
	}

	public SkillDto getSkillDto() {
		return skillDto;
	}

	public void setSkillDto(SkillDto skillDto) {
		this.skillDto = skillDto;
	}

}
