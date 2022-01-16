package com.demoipm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoipm.entities.Recruitment;

public interface RecruitmentDao extends JpaRepository<Recruitment, Integer> {

}
