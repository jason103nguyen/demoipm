package com.demoipm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.demoipm.dto.JobSkillDto;

@Entity
@Table(name = "job_skill")
public class JobSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_skill_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;
	
	public JobSkill() {}
	
	public JobSkill(JobSkillDto jobSkillDto) {
		super();
		this.id = jobSkillDto.getId();
		
		Skill skill = new Skill(jobSkillDto.getSkillDto());
		this.skill = skill;
		
		Job job = new Job(jobSkillDto.getJobDto());
		this.job = job;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
}
