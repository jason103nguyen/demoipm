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
	
	public List<InterviewDto> getListInterviewByCandidateId(int id);
	
	public List<CandidateDto> filterCandidatePassEntryTest(Integer page);
	
	public List<CandidateDto> filterCandidateByContent(String content, Integer page);

	public List<CandidateDto> filterCandidateByAge(int minAge, int maxAge, Integer page);
	
	public List<CandidateDto> filterCandidateBySkill(List<Integer> listId, Integer page);
	
	public List<CandidateDto> filterCandidateByAgeAndSkill(Integer minAge, Integer maxAge, List<Integer> listId, Integer page);
	
	public List<CandidateDto> filterCandidateByContentAndSkill(String content, List<Integer> listId, Integer page);

	public List<CandidateDto> filterCandidateByContentAndAge(String content, Integer minAge, Integer maxAge, Integer page);
	
	public List<CandidateDto> filterCandidateByContentAndAgeAndSkill(String content, Integer minAge, Integer maxAge,
			List<Integer> listId, Integer page);

	public Integer countPageCandidatePassEntryTest();
	
	public Integer countPageCandidateByContent(String content);

	public Integer countPageCandidateByAge(int minAge, int maxAge);
	
	public Integer countPageCandidateBySkill(List<Integer> listId);
	
	public Integer countPageCandidateByAgeAndSkill(Integer minAge, Integer maxAge, List<Integer> listId);
	
	public Integer countPageCandidateByContentAndSkill(String content, List<Integer> listId);

	public Integer countPageCandidateByContentAndAge(String content, Integer minAge, Integer maxAge);
	
	public Integer countPageCandidateByContentAndAgeAndSkill(String content, Integer minAge, Integer maxAge,
			List<Integer> listId);
}
