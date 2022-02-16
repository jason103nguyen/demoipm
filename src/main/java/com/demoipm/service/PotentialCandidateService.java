package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.CandidateDto;

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
	List<CandidateDto> searchPotentialCandidate(String keySearch);

}
