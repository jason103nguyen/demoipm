package com.demoipm.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.potentialcandidate.PotentialCandidatePageDto;
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
	 * Filter Search and Sort and Pagination PotentialCandidate
	 * 
	 * @param candidateDto
	 * @return List All PotentialCandidate
	 */
	PotentialCandidatePageDto FilterPotentialCandidateIsDelete(String keySearch, int pageSize, int pageNo,
			String filed, String direction);

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

}
