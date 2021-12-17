package com.phuongnt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.phuongnt.dto.RecruitmentSkillDto;

@Entity
@Table(name = "recruitment_skill")
public class RecruitmentSkill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recruitment_skill_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "recruitment_id")
	private Recruitment recruitment;
	
	@ManyToOne
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
	public RecruitmentSkill() {}
	
	public RecruitmentSkill(RecruitmentSkillDto recruitmentSkillDto) {
		super();
		
		Recruitment recruitment = new Recruitment(recruitmentSkillDto.getRecruitmentDto());
		this.recruitment = recruitment;
		
		Skill skill = new Skill(recruitmentSkillDto.getSkillDto());
		this.skill = skill;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Recruitment getRecruitment() {
		return recruitment;
	}

	public void setRecruitment(Recruitment recruitment) {
		this.recruitment = recruitment;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
}
