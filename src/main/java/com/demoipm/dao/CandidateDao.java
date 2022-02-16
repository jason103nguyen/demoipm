package com.demoipm.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demoipm.dto.candidatefilter.CandidateFilter;
import com.demoipm.entities.Candidate;

public interface CandidateDao extends CrudRepository<Candidate, Integer>, CandidateDaoCustom {

}