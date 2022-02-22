package com.demoipm.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demoipm.entities.Skill;
import org.springframework.data.repository.query.Param;

public interface SkillDao extends CrudRepository<Skill, Integer>{

    Optional<Skill> findByName(String name);

    @Query("SELECT s FROM Skill s JOIN s.listJobSkill js WHERE js.job.id = :jobId")
    List<Skill> getAllSkillOfJob(@Param("jobId") Integer jobId);
}
