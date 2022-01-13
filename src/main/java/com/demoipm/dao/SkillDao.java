package com.demoipm.dao;

import com.demoipm.dto.SkillDto;
import org.springframework.data.repository.CrudRepository;

import com.demoipm.entities.Skill;

import java.util.Optional;

public interface SkillDao extends CrudRepository<Skill, Integer>{

    Optional<Skill> findByName(String name);


}
