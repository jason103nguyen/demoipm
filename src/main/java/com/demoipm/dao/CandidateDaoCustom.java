package com.demoipm.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.demoipm.entities.Candidate;
import com.demoipm.entities.Interview;

public interface CandidateDaoCustom {

	List<Candidate> search(String content);
	
	List<Candidate> filterCandidateByAge(LocalDate fromYear, LocalDate toYear);
	
	List<Candidate> getCandidateBySkillAndPassEntryTest(List<Integer> listId);

	List<Candidate> readCandidatePassEntryTest(Integer page);

	Integer countCandidatePassEntryTest();
	
	List<Candidate> filterCandidateByAgeAndSkillAndPassEntryTest(LocalDate fromYear, LocalDate toYear, List<Integer> listId);

	List<Interview> findListInterviewByCandidateId(int id);

	List<Candidate> filterCandidateByContentAndSkillAndPassEntryTest(String content, List<Integer> listId);

	List<Candidate> filterCandidateByContentAndAgeAndPassEntryTest(String content, LocalDate fromYear, LocalDate toYear);

	List<Candidate> filterCandidateByContentAndAgeAndSkillAndPassEntryTest(String content, LocalDate fromYear,
	LocalDate toYear, List<Integer> listId);

}
