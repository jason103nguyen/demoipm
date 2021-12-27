package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewDto;

public interface CandidateService {

	void create(CandidateDto candidateDto);
	
	CandidateDto readById(int id) throws Exception;
	
	List<CandidateDto> readAll() throws Exception ;
	
	void update(CandidateDto candidateDto);
	
	void deleteById(int id);
	
	List<CandidateDto> readCandidatePassEntryTest();

	List<InterviewDto> getListInterviewByCandidateId(int id);

	List<CandidateDto> searchCandidate(String content);

	List<CandidateDto> filterCandidateByAge(int minAge, int maxAge);

	List<CandidateDto> filterCandidateBySkill(List<Integer> listId);

    List<CandidateDto> readCandidatePassEntryTest(Integer page);

    Integer countCandidatePassEntryTest();

	List<CandidateDto> filterCandidateByAgeAndSkill(Integer minAge, Integer maxAge, List<Integer> listId);

    List<CandidateDto> filterCandidateByContentAndSkill(String content, List<Integer> listId);

    List<CandidateDto> filterCandidateByContentAndAge(String content, Integer minAge, Integer maxAge);

	List<CandidateDto> filterCandidateByContentAndAgeAndSkill(String content, Integer minAge, Integer maxAge,
			List<Integer> listId);
}
