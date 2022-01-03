package com.demoipm.dao;

import java.time.LocalDate;
import java.util.List;

import com.demoipm.entities.Candidate;
import com.demoipm.entities.Interview;

public interface CandidateDaoCustom {

	public List<Interview> findListInterviewByCandidateId(int id);
	
	public List<Candidate> filterCandidateByContent(String content, Integer page);
	
	public List<Candidate> filterCandidateByAge(LocalDate fromYear, LocalDate toYear, Integer page);
	
	public List<Candidate> filterCandidateBySkillAndPassEntryTest(List<Integer> listId, Integer page);
	
	public List<Candidate> filterCandidateByAgeAndSkillAndPassEntryTest(
			LocalDate fromYear, LocalDate toYear, List<Integer> listId, Integer page);
	
	public List<Candidate> filterCandidateByContentAndSkillAndPassEntryTest(String content, 
		List<Integer> listId, Integer page);
	
	public List<Candidate> filterCandidateByContentAndAgeAndPassEntryTest(String content, 
		LocalDate fromYear, LocalDate toYear, Integer page);
	
	public List<Candidate> filterCandidateByContentAndAgeAndSkillAndPassEntryTest(String content, LocalDate fromYear,
		LocalDate toYear, List<Integer> listId, Integer page);
	
	public List<Candidate> filterCandidatePassEntryTest(Integer page);

	public Integer countCandidateByContent(String content);
	
	public Integer countCandidateByAge(LocalDate fromYear, LocalDate toYear);
	
	public Integer countCandidateBySkillAndPassEntryTest(List<Integer> listId);
	
	public Integer countCandidateByAgeAndSkillAndPassEntryTest(
			LocalDate fromYear, LocalDate toYear, List<Integer> listId);
	
	public Integer countCandidateByContentAndSkillAndPassEntryTest(String content, List<Integer> listId);
	
	public Integer countCandidateByContentAndAgeAndPassEntryTest(String content, LocalDate fromYear, 
		LocalDate toYear);
	
	public Integer countCandidateByContentAndAgeAndSkillAndPassEntryTest(String content, LocalDate fromYear,
	LocalDate toYear, List<Integer> listId);
	
	public Integer countCandidatePassEntryTest();
	
	public List<Candidate> filterCandidateByAge(Integer fromYear, Integer toYear, Integer page);

}
