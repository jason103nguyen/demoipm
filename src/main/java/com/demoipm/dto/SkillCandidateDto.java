package com.demoipm.dto;

import com.demoipm.entities.Candidate;
import com.demoipm.entities.Skill;
import com.demoipm.entities.SkillCandidate;

public class SkillCandidateDto {

	private int id;
	
	private SkillDto skill;
	
	private CandidateDto candidate;
	
	public SkillCandidateDto() {}

	public SkillCandidateDto(SkillCandidate skillCandidate) {
		super();
		this.id = skillCandidate.getId();
		
		SkillDto skilDto = new SkillDto(skillCandidate.getSkill());
		this.skill = skilDto;
		
		CandidateDto candidateDto = new CandidateDto(skillCandidate.getCandidate());
		this.candidate = candidateDto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SkillDto getSkill() {
		return skill;
	}

	public void setSkill(SkillDto skill) {
		this.skill = skill;
	}

	public CandidateDto getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateDto candidate) {
		this.candidate = candidate;
	}
	
}
