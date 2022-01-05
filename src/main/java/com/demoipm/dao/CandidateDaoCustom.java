package com.demoipm.dao;

import java.time.LocalDate;
import java.util.List;

import com.demoipm.entities.Candidate;
import com.demoipm.entities.Interview;

public interface CandidateDaoCustom {

	/**
	 * Get list interview by candidate id
	 * @param id
	 * @return
	 */
	public List<Interview> findListInterviewByCandidateId(int id);
	
	/**
	 * Filter candidate by content
	 * @param content
	 * @param page
	 * @return
	 */
	public List<Candidate> filterCandidateByContent(String content, Integer page);
	
	/**
	 * Filter candidate by age
	 * @param fromYear
	 * @param toYear
	 * @param page
	 * @return
	 */
	public List<Candidate> filterCandidateByAge(LocalDate fromYear, LocalDate toYear, Integer page);
	
	/**
	 * Filter candidate by skill and pass entry test
	 * @param listId
	 * @param page
	 * @return
	 */
	public List<Candidate> filterCandidateBySkillAndPassEntryTest(List<Integer> listId, Integer page);
	
	/**
	 * Filter candidate by age, skill and pass entry test
	 * @param fromYear
	 * @param toYear
	 * @param listId
	 * @param page
	 * @return
	 */
	public List<Candidate> filterCandidateByAgeAndSkillAndPassEntryTest(
			LocalDate fromYear, LocalDate toYear, List<Integer> listId, Integer page);
	
	/**
	 * Filter candidate by content, skill, and pass entry test
	 * @param content
	 * @param listId
	 * @param page
	 * @return
	 */
	public List<Candidate> filterCandidateByContentAndSkillAndPassEntryTest(String content, 
		List<Integer> listId, Integer page);
	
	/**
	 * Filter candidate by content, age and pass entry test
	 * @param content
	 * @param fromYear
	 * @param toYear
	 * @param page
	 * @return
	 */
	public List<Candidate> filterCandidateByContentAndAgeAndPassEntryTest(String content, 
		LocalDate fromYear, LocalDate toYear, Integer page);
	
	/**
	 * Filter candidate by content, age, skill and pass entry test
	 * @param content
	 * @param fromYear
	 * @param toYear
	 * @param listId
	 * @param page
	 * @return
	 */
	public List<Candidate> filterCandidateByContentAndAgeAndSkillAndPassEntryTest(String content, LocalDate fromYear,
		LocalDate toYear, List<Integer> listId, Integer page);
	
	/**
	 * Filter candidate pass entry test
	 * @param page
	 * @return
	 */
	public List<Candidate> filterCandidatePassEntryTest(Integer page);

	/**
	 * Count candidate by content
	 * @param content
	 * @return
	 */
	public Integer countCandidateByContent(String content);
	
	/**
	 * Count candidate by age
	 * @param fromYear
	 * @param toYear
	 * @return
	 */
	public Integer countCandidateByAge(LocalDate fromYear, LocalDate toYear);
	
	/**
	 * Count candidate by skill and pass entry test
	 * @param listId
	 * @return
	 */
	public Integer countCandidateBySkillAndPassEntryTest(List<Integer> listId);
	
	/**
	 * Count candidate by age, skill and pass entry test
	 * @param fromYear
	 * @param toYear
	 * @param listId
	 * @return
	 */
	public Integer countCandidateByAgeAndSkillAndPassEntryTest(
			LocalDate fromYear, LocalDate toYear, List<Integer> listId);
	
	/**
	 * Count candidate by content, skill and pass entry test
	 * @param content
	 * @param listId
	 * @return
	 */
	public Integer countCandidateByContentAndSkillAndPassEntryTest(String content, List<Integer> listId);
	
	/**
	 * Count candidate by content, age and pass entry test
	 * @param content
	 * @param fromYear
	 * @param toYear
	 * @return
	 */
	public Integer countCandidateByContentAndAgeAndPassEntryTest(String content, LocalDate fromYear, 
		LocalDate toYear);
	
	/**
	 * Count candidate by content, age, skill and pass entry test
	 * @param content
	 * @param fromYear
	 * @param toYear
	 * @param listId
	 * @return
	 */
	public Integer countCandidateByContentAndAgeAndSkillAndPassEntryTest(String content, LocalDate fromYear,
	LocalDate toYear, List<Integer> listId);
	
	/**
	 * Count candidate pass entry test
	 * @return
	 */
	public Integer countCandidatePassEntryTest();
	
	/**
	 * Filter candidate by age
	 * @param fromYear
	 * @param toYear
	 * @param page
	 * @return
	 */
	public List<Candidate> filterCandidateByAge(Integer fromYear, Integer toYear, Integer page);

}
