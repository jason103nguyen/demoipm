package com.demoipm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoipm.entities.Candidate;

public interface PotentialCandidateDao extends JpaRepository<Candidate, Integer>{

}
