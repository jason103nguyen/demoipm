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

import com.phuongnt.dto.SkillDto;

@Entity
@Table(name = "skill")
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "skill_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "skill")
	private List<JobSkill> listJobSkill = new ArrayList<JobSkill>();
	
	public Skill() {}

	public Skill(SkillDto skillDto) {
		super();
		this.id = skillDto.getId();
		this.name = skillDto.getName();
		this.description = skillDto.getDescription();
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

	public List<JobSkill> getListJobSkill() {
		return listJobSkill;
	}

	public void setListJobSkill(List<JobSkill> listJobSkill) {
		this.listJobSkill = listJobSkill;
	}

}
