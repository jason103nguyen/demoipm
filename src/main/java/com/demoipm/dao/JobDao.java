package com.demoipm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoipm.entities.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobDao extends JpaRepository<Job, Integer> {

    @Query("SELECT j FROM Job j JOIN j.career c WHERE c.id = :careerId")
    List<Job> getAllJobOfCareer(@Param("careerId") Integer careerId);
}
