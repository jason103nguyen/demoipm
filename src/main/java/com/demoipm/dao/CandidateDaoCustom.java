package com.demoipm.dao;

import java.time.LocalDate;
import java.util.List;

import com.demoipm.entities.Candidate;

public interface CandidateDaoCustom {

	List<Candidate> search(String content);
	
	List<Candidate> filterCandidateByAge(LocalDate fromYear, LocalDate toYear);
	
	List<Candidate> getCandidateBySkillAndPassEntryTest(List<Integer> listId);
}
