package com.phuongnt.dao;

import org.springframework.data.repository.CrudRepository;

import com.phuongnt.entities.Candidate;

public interface CandidateDao extends CrudRepository<Candidate, Integer>{

}
