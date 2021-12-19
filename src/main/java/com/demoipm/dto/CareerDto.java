package com.demoipm.dto;

import java.util.ArrayList;
import java.util.List;

import com.demoipm.entities.Career;

public class CareerDto {

	private int id;
	
	private String name;
	
	private String description;
	
	private List<JobDto> listJobDto = new ArrayList<JobDto>();
	
	private List<RecruitmentDto> listRecruitmentDto = new ArrayList<RecruitmentDto>();
	
	public CareerDto() {}
	
	public CareerDto(Career career) {
		super();
		this.id = career.getId();
		this.name = career.getName();
		this.description = career.getDescription();
	}

	public List<RecruitmentDto> getListRecruitmentDto() {
		return listRecruitmentDto;
	}

	public void setListRecruitmentDto(List<RecruitmentDto> listRecruitmentDto) {
		this.listRecruitmentDto = listRecruitmentDto;
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

	public List<JobDto> getListJobDto() {
		return listJobDto;
	}

	public void setListJobDto(List<JobDto> listJob) {
		this.listJobDto = listJob;
	}
	
}
