package com.demoipm.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demoipm.entities.Candidate;
import com.demoipm.entities.Interview;

public interface CandidateDao extends CrudRepository<Candidate, Integer>, CandidateDaoCustom {

}