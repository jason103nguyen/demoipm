package com.demoipm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoipm.entities.Skill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillDao extends JpaRepository<Skill, Integer>{

    @Query("SELECT s FROM Skill s JOIN s.listJobSkill js WHERE js.job.id = :jobId")
    List<Skill> getAllSkillOfJob(@Param("jobId") Integer jobId);
}
