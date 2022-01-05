package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewDto;

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
	 * Filter candidate pass entry test
	 * @param page
	 * @return
	 */
	public List<CandidateDto> filterCandidatePassEntryTest(Integer page);
	
	/**
	 * Filter candidate by content
	 * @param content
	 * @param page
	 * @return
	 */
	public List<CandidateDto> filterCandidateByContent(String content, Integer page);

	/**
	 * Filter candidate by age
	 * @param minAge
	 * @param maxAge
	 * @param page
	 * @return
	 */
	public List<CandidateDto> filterCandidateByAge(int minAge, int maxAge, Integer page);
	
	/**
	 * Filter candidate by skill
	 * @param listId
	 * @param page
	 * @return
	 */
	public List<CandidateDto> filterCandidateBySkill(List<Integer> listId, Integer page);
	
	/**
	 * Filter candidate by age and skill
	 * @param minAge
	 * @param maxAge
	 * @param listId
	 * @param page
	 * @return
	 */
	public List<CandidateDto> filterCandidateByAgeAndSkill(Integer minAge, Integer maxAge, List<Integer> listId, Integer page);
	
	/**
	 * Filter candidate by content and skill
	 * @param content
	 * @param listId
	 * @param page
	 * @return
	 */
	public List<CandidateDto> filterCandidateByContentAndSkill(String content, List<Integer> listId, Integer page);

	/**
	 * Filter candidate by content and age
	 * @param content
	 * @param minAge
	 * @param maxAge
	 * @param page
	 * @return
	 */
	public List<CandidateDto> filterCandidateByContentAndAge(String content, Integer minAge, Integer maxAge, Integer page);
	
	/**
	 * Filter candidate by content, age and skill
	 * @param content
	 * @param minAge
	 * @param maxAge
	 * @param listId
	 * @param page
	 * @return
	 */
	public List<CandidateDto> filterCandidateByContentAndAgeAndSkill(String content, Integer minAge, Integer maxAge,
			List<Integer> listId, Integer page);

	/**
	 * Count page of candidate pass entry test
	 * @return
	 */
	public Integer countPageCandidatePassEntryTest();
	
	/**
	 * Count page of candidate by content 
	 * @param content
	 * @return
	 */
	public Integer countPageCandidateByContent(String content);

	/**
	 * Count page of candidate by age
	 * @param minAge
	 * @param maxAge
	 * @return
	 */
	public Integer countPageCandidateByAge(int minAge, int maxAge);
	
	/**
	 * Count page of candidate by skill
	 * @param listId
	 * @return
	 */
	public Integer countPageCandidateBySkill(List<Integer> listId);
	
	/**
	 * Count page of candidate by age and skill
	 * @param minAge
	 * @param maxAge
	 * @param listId
	 * @return
	 */
	public Integer countPageCandidateByAgeAndSkill(Integer minAge, Integer maxAge, List<Integer> listId);
	
	/**
	 * Count page of candidate by content and skill
	 * @param content
	 * @param listId
	 * @return
	 */
	public Integer countPageCandidateByContentAndSkill(String content, List<Integer> listId);

	/**
	 * Count page candidate by content and age
	 * @param content
	 * @param minAge
	 * @param maxAge
	 * @return
	 */
	public Integer countPageCandidateByContentAndAge(String content, Integer minAge, Integer maxAge);
	
	/**
	 * Count page of candidate by content, age and skill
	 * @param content
	 * @param minAge
	 * @param maxAge
	 * @param listId
	 * @return
	 */
	public Integer countPageCandidateByContentAndAgeAndSkill(String content, Integer minAge, Integer maxAge,
			List<Integer> listId);
}
