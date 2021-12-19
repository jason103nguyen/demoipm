package com.demoipm.dao;

import org.springframework.data.repository.CrudRepository;

import com.demoipm.entities.Candidate;

public interface CandidateDao extends CrudRepository<Candidate, Integer>{

}
