package com.demoipm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demoipm.entities.Interview;

public interface InterviewDao extends CrudRepository<Interview, Integer>{

	@Query("SELECT i FROM Interview i WHERE i.candidate.id = :id")
	List<Interview> findByCandidateId(@Param(value = "id") int id);

}
