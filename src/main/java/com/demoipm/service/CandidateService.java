package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewDto;
import com.demoipm.dto.candidatefilter.CandidateFilter;

public interface CandidateService {

	/**
	 * Create a candidate
	 * @param candidateDto
	 */
	void create(CandidateDto candidateDto);
	
	/**
	 * Get a candidate by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	CandidateDto readById(int id) throws Exception;
	
	/**
	 * Get all candidate
	 * @return
	 * @throws Exception
	 */
	List<CandidateDto> readAll() throws Exception ;
	
	/**
	 * Update a candidate
	 * @param candidateDto
	 */
	void update(CandidateDto candidateDto);
	
	/**
	 * Delete a candidate by id
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * Get list interview by candidate id
	 * @param id
	 * @return
	 */
	public List<InterviewDto> getListInterviewByCandidateId(int id);
	
	/**
	 * Filter candidate
	 * @param candidateFilter
	 * @return
	 */
	List<CandidateDto> filter(CandidateFilter candidateFilter);
}
