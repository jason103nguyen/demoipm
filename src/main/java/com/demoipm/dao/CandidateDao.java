package com.demoipm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demoipm.entities.Candidate;

public interface CandidateDao extends CrudRepository<Candidate, Integer>, CandidateDaoCustom {

}