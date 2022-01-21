package com.demoipm.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.potentialcandidate.pageDTO;
import com.demoipm.entities.Candidate;

public interface PotentialCandidateService {
	
	/**
	 * Get Potential Candidate By ID
	 * 
	 * @param id
	 * @return Potential Candidate
	 */
	CandidateDto getPotentialCandidateByID(int id);

	/**
	 * Create Potential Candidate
	 * 
	 * @param candidateDto
	 * @return 
	 */
	void createPotentialCandidate(CandidateDto candidateDto);

	/**
	 * Get all PotentialCandidate
	 * 
	 * 
	 * @return List All PotentialCandidates
	 */
	List<CandidateDto> getAllPotentialCandidate();

	/**
	 * Search Potential Candidate By Key Search
	 * 
	 * 
	 * @param keySearch
	 * @return List Potential Candidate
	 */
	pageDTO searchPotentialCandidateIsDelete(String keySearch, int pageSize, int pageNo, String filed);

	/**
	 * Delete Potential Candidate Info DB
	 * 
	 * @param id
	 */
	void deletePotentialCandidate(int id);

	/**
	 * Update PotentialCandidate Info To DB
	 * 
	 * 
	 * @param candidateDto
	 */
	void updatePotentialCandidate(CandidateDto candidateDto);

	List<CandidateDto> findPotentialCandidateWithSorting(String field);

	//Page<Candidate> findPagination(int pageNo, int pageSize);


}
