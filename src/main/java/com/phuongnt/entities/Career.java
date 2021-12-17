package com.phuongnt.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.phuongnt.dto.CareerDto;

@Entity
@Table(name = "career")
public class Career {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "career_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "career")
	private List<Job> listJob = new ArrayList<Job>();
	
	@OneToMany(mappedBy = "career")
	private List<Recruitment> listRecruitment = new ArrayList<Recruitment>();
	
	public Career() {}
	
	public Career(CareerDto careerDto) {
		super();
		this.id = careerDto.getId();
		this.name = careerDto.getName();
		this.description = careerDto.getDescription();
	}
	
	public List<Recruitment> getListRecruitment() {
		return listRecruitment;
	}

	public void setListRecruitment(List<Recruitment> listRecruitment) {
		this.listRecruitment = listRecruitment;
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

	public List<Job> getListJob() {
		return listJob;
	}

	public void setListJob(List<Job> listJob) {
		this.listJob = listJob;
	}
	
}
