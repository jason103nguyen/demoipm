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
	 * @return List All PotentialCandidate
	 */
	List<CandidateDto> getAllPotentialCandidate();

}
