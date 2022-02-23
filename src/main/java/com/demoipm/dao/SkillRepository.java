package com.demoipm.dao;

import com.demoipm.entities.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

    List<Skill> findByNameStartingWith(String name) throws Exception;
    Page<Skill> findByNameStartingWith(String name, Pageable pageable) throws Exception;
}
