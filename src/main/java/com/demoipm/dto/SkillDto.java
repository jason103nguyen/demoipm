package com.demoipm.dto;

import java.util.ArrayList;
import java.util.List;

import com.demoipm.entities.Skill;
import com.demoipm.entities.SkillCandidate;

public class SkillDto {

	private int id;
	
	private String name;
	
	private String description;
	
	private List<JobSkillDto> listJobSkillDto;
	
	private List<SkillCandidateDto> listSkillCandidate = new ArrayList<SkillCandidateDto>();
	
	public SkillDto() {}

	public SkillDto(Skill skill) {
		super();
		this.id = skill.getId();
		this.name = skill.getName();
		this.description = skill.getDescription();
	}

	public List<SkillCandidateDto> getListSkillCandidate() {
		return listSkillCandidate;
	}

	public void setListSkillCandidate(List<SkillCandidateDto> listSkillCandidate) {
		this.listSkillCandidate = listSkillCandidate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<JobSkillDto> getListJobSkillDto() {
		return listJobSkillDto;
	}


	public void setListJobSkillDto(List<JobSkillDto> listJobSkillDto) {
		this.listJobSkillDto = listJobSkillDto;
	}

}
