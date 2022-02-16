package com.demoipm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.SkillCandidateDto;
import com.demoipm.dto.SkillDto;

@Entity
@Table(name = "skill_candidate")
public class SkillCandidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "skill_candidate_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	public SkillCandidate() {}
	
	public SkillCandidate(SkillCandidateDto skillCandidate) {
		super();
		this.id = skillCandidate.getId();
		
		Skill skill = new Skill(skillCandidate.getSkill());
		this.skill = skill;
		
		Candidate candidate = new Candidate(skillCandidate.getCandidate());
		this.candidate = candidate;
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

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
}
