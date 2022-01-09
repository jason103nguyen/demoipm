package com.demoipm.dao;

import com.demoipm.entities.QuestionEntryTest;
import com.demoipm.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionDao extends JpaRepository<QuestionEntryTest, Integer> {

    Skill findBySkill(String skill);

}