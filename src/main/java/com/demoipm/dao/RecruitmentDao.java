package com.demoipm.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demoipm.entities.Recruitment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecruitmentDao extends JpaRepository<Recruitment, Integer> {

    @Query("SELECT r FROM Recruitment r WHERE r.job.name LIKE :search OR r.career.name LIKE :search")
    Page<Recruitment> readByCondition(@Param("search") String search, Pageable pageable);
}
