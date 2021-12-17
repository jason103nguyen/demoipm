package com.phuongnt.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.phuongnt.dto.JobDto;
import com.phuongnt.dto.JobSkillDto;

import javax.persistence.OneToMany;

@Entity
@Table(name = "job")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "job")
	private List<JobSkill> listJobSkill = new ArrayList<JobSkill>();
	
	@ManyToOne
	@JoinColumn(name = "career_id")
	private Career career;
	
	public Job() {}

	public Job(JobDto jobDto) {
		super();
		this.id = jobDto.getId();
		this.name = jobDto.getName();
		Career career = new Career(jobDto.getCareer());
		this.career = career;
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

	public List<JobSkill> getListJobSkill() {
		return listJobSkill;
	}

	public void setListJobSkill(List<JobSkill> listJobSkill) {
		this.listJobSkill = listJobSkill;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}
	
}
