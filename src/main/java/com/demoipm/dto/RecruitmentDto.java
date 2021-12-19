package com.demoipm.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demoipm.entities.Recruitment;

public class RecruitmentDto {

	private int id;
	
	private CareerDto career;
	
	private int number;
	
	private double minSalary;
	
	private double maxSalary;
	
	private Date startRecruitment;
	
	private Date endRecruitment;
	
	private JobDto jobDto;
	
	private List<SkillDto> listSkill = new ArrayList<SkillDto>();
	
	public RecruitmentDto() {}
	
	public RecruitmentDto(Recruitment recruitment) {
		super();
		this.id = recruitment.getId();
		
		CareerDto careerDto = new CareerDto(recruitment.getCareer());
		this.career = careerDto;
		
		JobDto jobDto = new JobDto(recruitment.getJob());
		this.jobDto = jobDto;
		
		this.number = recruitment.getNumber();
		this.minSalary = recruitment.getMinSalary();
		this.maxSalary = recruitment.getMaxSalary();
		this.startRecruitment = recruitment.getStartRecruitment();
		this.endRecruitment = recruitment.getEndRecruitment();
	}
	
	public List<SkillDto> getListSkill() {
		return listSkill;
	}

	public void setListSkill(List<SkillDto> listSkill) {
		this.listSkill = listSkill;
	}

	public JobDto getJobDto() {
		return jobDto;
	}

	public void setJobDto(JobDto jobDto) {
		this.jobDto = jobDto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CareerDto getCareer() {
		return career;
	}

	public void setCareer(CareerDto career) {
		this.career = career;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}

	public double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public Date getStartRecruitment() {
		return startRecruitment;
	}

	public void setStartRecruitment(Date startRecruitment) {
		this.startRecruitment = startRecruitment;
	}

	public Date getEndRecruitment() {
		return endRecruitment;
	}

	public void setEndRecruitment(Date endRecruitment) {
		this.endRecruitment = endRecruitment;
	}
	
}
