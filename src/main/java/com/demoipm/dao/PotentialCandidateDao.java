package com.demoipm.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demoipm.entities.Candidate;

public interface PotentialCandidateDao extends JpaRepository<Candidate, Integer>{
	
	@Query("SELECT pc FROM Candidate pc WHERE CONCAT (pc.fullName, pc.email, pc.phone, pc.status) LIKE %:keySearch%")
	public List<Candidate> ListSearchPotentialCandidate(@Param("keySearch") String keySearch);
	
	@Query("SELECT c FROM Candidate c WHERE c.isDelete = :isDelete")
	public List<Candidate> findPotentialCandidateIsDelete(Boolean isDelete);
	
	@Query("SELECT c FROM Candidate c WHERE c.id = :id AND c.isDelete = :isDelete")
	Optional<Candidate> findByPotentialCandidateIdAndIsDelete(Integer id, Boolean isDelete);
	
	@Query("SELECT c FROM Candidate c WHERE EXISTS (SELECT c FROM Candidate c WHERE c.id = :id AND c.isDelete = :isDelete)")
	public boolean existsByPotentialCandidateIdAndIsDelete(Integer id, Boolean isDelete);

}
