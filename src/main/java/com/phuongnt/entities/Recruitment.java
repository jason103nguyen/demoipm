package com.phuongnt.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.phuongnt.dto.RecruitmentDto;

@Entity
@Table(name = "recruitment")
public class Recruitment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recruitment_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "career_id")
	private Career career;
	
	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;
	
	@OneToMany(mappedBy = "recruitment")
	private List<RecruitmentSkill> listRecruitmentSkill = new ArrayList<RecruitmentSkill>();
	
	@Column(name = "number")
	private int number;
	
	@Column(name = "min_salary")
	private double minSalary;
	
	@Column(name = "max_salary")
	private double maxSalary;
	
	@Column(name = "start_recruitment")
	private Date startRecruitment;
	
	@Column(name = "end_recruitment")
	private Date endRecruitment;
	
	public Recruitment() {}
	
	public Recruitment(RecruitmentDto recruitmentDto) {
		super();
		this.id = recruitmentDto.getId();
		
		Career career = new Career(recruitmentDto.getCareer());
		this.career = career;
		
		this.number = recruitmentDto.getNumber();
		this.minSalary = recruitmentDto.getMinSalary();
		this.maxSalary = recruitmentDto.getMaxSalary();
		this.startRecruitment = recruitmentDto.getStartRecruitment();
		this.endRecruitment = recruitmentDto.getEndRecruitment();
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<RecruitmentSkill> getListRecruitmentSkill() {
		return listRecruitmentSkill;
	}

	public void setListRecruitmentSkill(List<RecruitmentSkill> listRecruitmentSkill) {
		this.listRecruitmentSkill = listRecruitmentSkill;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
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
