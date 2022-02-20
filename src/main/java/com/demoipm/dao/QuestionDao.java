package com.demoipm.dao;

import com.demoipm.entities.EntryTest;
import com.demoipm.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    @Query("SELECT q FROM Question q WHERE q.skill.name like :skill")
    List<Question> getRandomQuestionBySkill(@Param("skill") String skill);

    List<Question> findQuestionById(Integer id);
}