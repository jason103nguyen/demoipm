package com.demoipm.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import com.demoipm.entities.Skill;

public interface SkillDao extends CrudRepository<Skill, Integer>{
    Optional<Skill> findByName(String name);
}
