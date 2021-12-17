package com.phuongnt.dto;

import java.util.List;

import com.phuongnt.entities.Skill;

public class SkillDto {

	private int id;
	
	private String name;
	
	private String description;
	
	private List<JobSkillDto> listJobSkillDto;
	
	public SkillDto() {}

	public SkillDto(Skill skill) {
		super();
		this.id = skill.getId();
		this.name = skill.getName();
		this.description = skill.getDescription();
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
