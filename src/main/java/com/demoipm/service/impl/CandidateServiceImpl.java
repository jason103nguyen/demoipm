package com.demoipm.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.Constant.PaginationInfo;
import com.demoipm.dao.CandidateDao;
import com.demoipm.dao.InterviewDao;
import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewDto;
import com.demoipm.entities.Candidate;
import com.demoipm.entities.Interview;
import com.demoipm.service.CandidateService;

@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private InterviewDao interviewDao;
	
	/**
	 * Create a candidate
	 */
	@Override
	public void create(CandidateDto candidateDto) {

		Candidate candidate = new Candidate(candidateDto);
		candidateDao.save(candidate);
	}

	/**
	 * Get a candidate by id
	 */
	@Override
	public CandidateDto readById(int id) throws Exception {

		Optional<Candidate> candidate = candidateDao.findById(id);
		CandidateDto candidateDto = null;
		if (!candidate.isPresent()) {
			throw new Exception("The id doesn't exists");
		} else {
			candidateDto = new CandidateDto(candidate.get());
		}
		return candidateDto;
	}

	/**
	 * Get all candidate
	 */
	@Override
	public List<CandidateDto> readAll() throws Exception {

		List<Candidate> listCandidate = (List<Candidate>) candidateDao.findAll();
		List<CandidateDto> listCandidateDto = new ArrayList<CandidateDto>();
		
		if (listCandidate.isEmpty()) {
			
			throw new Exception("This list is empty");
		} else {
			
			for (Candidate candidate : listCandidate) {
				CandidateDto candidateDto = new CandidateDto(candidate);
				listCandidateDto.add(candidateDto);
			}
		}
		
		return listCandidateDto;
	}

	/**
	 * Update a candidate
	 */
	@Override
	public void update(CandidateDto candidateDto) {

		Candidate candidate = new Candidate(candidateDto);
		candidateDao.save(candidate);
	}

	/**
	 * Delete a candidate by id
	 */
	@Override
	public void deleteById(int id) {

		if (candidateDao.existsById(id)) {
			candidateDao.deleteById(id);
		}
	}

	/**
	 * Filter candidate pass entry test
	 */
	@Override
	public List<CandidateDto> filterCandidatePassEntryTest(Integer page) {

		List<Candidate> listCandidate = candidateDao.filterCandidatePassEntryTest(page);
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		
		return listCandidateDto;
	}

	/**
	 * Count number of page of candidate pass entry test
	 */
	@Override
	public Integer countPageCandidatePassEntryTest() {

		return calculatorPage(candidateDao.countCandidatePassEntryTest());
	}

	/**
	 * Calculator total page base on total row
	 * @param totalRow
	 * @return
	 */
	private Integer calculatorPage(Integer totalRow) {

		Integer totalPage = Math.round(totalRow/PaginationInfo.MAX_RESULT);
		return totalPage;
	}

	/**
	 * Get list interview by candidate id
	 */
	@Override
	public List<InterviewDto> getListInterviewByCandidateId(int id) {

		List<Interview> listInterview = interviewDao.findByCandidateId(id);
		
		return convertToListInterviewDto(listInterview);
	}

	/**
	 * Filter candidate by content
	 */
	@Override
	public List<CandidateDto> filterCandidateByContent(String content, Integer page) {
		
		List<Candidate> listCandidate = candidateDao.filterCandidateByContent(content, page);
		List<CandidateDto> listCandidateDto = new ArrayList<CandidateDto>();
		
		for(Candidate candidate : listCandidate) {
			CandidateDto candidateDto = new CandidateDto(candidate);
			listCandidateDto.add(candidateDto);
		}
		return listCandidateDto;
	}

	/**
	 * Filter candidate by age
	 */
	@Override
	public List<CandidateDto> filterCandidateByAge(int minAge, int maxAge, Integer page) {
		
		LocalDate currentDate = LocalDate.now();
		int fromYearInt = currentDate.getYear() - maxAge;
		LocalDate fromYear = LocalDate.of(fromYearInt, 1, 1);
		
		int toYearInt = currentDate.getYear() - minAge;
		LocalDate toYear = LocalDate.of(toYearInt, 12, 31);
		
		List<Candidate> listCandidate = candidateDao.filterCandidateByAge(fromYear, toYear, page);
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		
		return listCandidateDto;
	}

	/**
	 * Filter candidate by skill
	 */
	@Override
	public List<CandidateDto> filterCandidateBySkill(List<Integer> listId , Integer page) {
		
		List<Candidate> listCandidate = candidateDao.filterCandidateBySkillAndPassEntryTest(listId, page);
		
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		return listCandidateDto;
	}

	/**
	 * Filter candidate by age and skill
	 */
	@Override
	public List<CandidateDto> filterCandidateByAgeAndSkill(Integer minAge, Integer maxAge, 
		List<Integer> listId, Integer page) {
		
		LocalDate currentDate = LocalDate.now();
		int fromYearInt = currentDate.getYear() - maxAge;
		LocalDate fromYear = LocalDate.of(fromYearInt, 1, 1);
		
		int toYearInt = currentDate.getYear() - minAge;
		LocalDate toYear = LocalDate.of(toYearInt, 12, 31);
		
		List<Candidate> listCandidate = candidateDao.filterCandidateByAgeAndSkillAndPassEntryTest(fromYear, toYear, listId, page);
		
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		return listCandidateDto;
	}

	/**
	 * Filter candidate by content and skill
	 */
	@Override
	public List<CandidateDto> filterCandidateByContentAndSkill(String content, List<Integer> listId, 
		Integer page) {
		
		List<Candidate> listCandidate = candidateDao.filterCandidateByContentAndSkillAndPassEntryTest(content, listId, page);
		
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		return listCandidateDto;
	}

	/**
	 * Filter candidate by content and age
	 */
	@Override
	public List<CandidateDto> filterCandidateByContentAndAge(String content, Integer minAge, Integer maxAge, 
		Integer page){

		LocalDate currentDate = LocalDate.now();
		int fromYearInt = currentDate.getYear() - maxAge;
		LocalDate fromYear = LocalDate.of(fromYearInt, 1, 1);
		
		int toYearInt = currentDate.getYear() - minAge;
		LocalDate toYear = LocalDate.of(toYearInt, 12, 31);

		List<Candidate> listCandidate = candidateDao.filterCandidateByContentAndAgeAndPassEntryTest(content, fromYear, toYear, page);
		
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		return listCandidateDto;
	}

	/**
	 * Filter candidate by content, age and skill
	 */
	@Override
	public List<CandidateDto> filterCandidateByContentAndAgeAndSkill(String content, Integer minAge, Integer maxAge,
			List<Integer> listId, Integer page) {

		LocalDate currentDate = LocalDate.now();
		int fromYearInt = currentDate.getYear() - maxAge;
		LocalDate fromYear = LocalDate.of(fromYearInt, 1, 1);
		
		int toYearInt = currentDate.getYear() - minAge;
		LocalDate toYear = LocalDate.of(toYearInt, 12, 31);

		List<Candidate> listCandidate = candidateDao.filterCandidateByContentAndAgeAndSkillAndPassEntryTest(content, fromYear, 
			toYear, listId, page);
		
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		return listCandidateDto;
	}

	/**
	 * Count page candidate by content
	 */
	@Override
	public Integer countPageCandidateByContent(String content) {

		return calculatorPage(candidateDao.countCandidateByContent(content));
	}

	/**
	 * Count page candidate by age
	 */
	@Override
	public Integer countPageCandidateByAge(int minAge, int maxAge) {

		LocalDate currentDate = LocalDate.now();
		int fromYearInt = currentDate.getYear() - maxAge;
		LocalDate fromYear = LocalDate.of(fromYearInt, 1, 1);
		
		int toYearInt = currentDate.getYear() - minAge;
		LocalDate toYear = LocalDate.of(toYearInt, 12, 31);

		return calculatorPage(candidateDao.countCandidateByAge(fromYear, toYear));
	}

	/**
	 * Count page candidate by skill
	 */
	@Override
	public Integer countPageCandidateBySkill(List<Integer> listId) {

		return calculatorPage(candidateDao.countCandidateBySkillAndPassEntryTest(listId));
	}

	/**
	 * Count page candidate by age and skill
	 */
	@Override
	public Integer countPageCandidateByAgeAndSkill(Integer minAge, Integer maxAge, List<Integer> listId) {

		LocalDate currentDate = LocalDate.now();
		int fromYearInt = currentDate.getYear() - maxAge;
		LocalDate fromYear = LocalDate.of(fromYearInt, 1, 1);
		
		int toYearInt = currentDate.getYear() - minAge;
		LocalDate toYear = LocalDate.of(toYearInt, 12, 31);

		return calculatorPage(candidateDao.countCandidateByAgeAndSkillAndPassEntryTest(fromYear, toYear, listId));
	}

	/**
	 * Count page candidate by content and skill
	 */
	@Override
	public Integer countPageCandidateByContentAndSkill(String content, List<Integer> listId) {

		return calculatorPage(candidateDao.countCandidateByContentAndSkillAndPassEntryTest(content, listId));
	}

	/**
	 * Count page candidate by content and age
	 */
	@Override
	public Integer countPageCandidateByContentAndAge(String content, Integer minAge, Integer maxAge) {

		LocalDate currentDate = LocalDate.now();
		int fromYearInt = currentDate.getYear() - maxAge;
		LocalDate fromYear = LocalDate.of(fromYearInt, 1, 1);
		
		int toYearInt = currentDate.getYear() - minAge;
		LocalDate toYear = LocalDate.of(toYearInt, 12, 31);

		return calculatorPage(candidateDao.countCandidateByContentAndAgeAndPassEntryTest(content, fromYear, toYear));
	}

	/**
	 * Count page candidate by content, age and skill
	 */
	@Override
	public Integer countPageCandidateByContentAndAgeAndSkill(String content, Integer minAge, Integer maxAge,
			List<Integer> listId) {

		LocalDate currentDate = LocalDate.now();
		int fromYearInt = currentDate.getYear() - maxAge;
		LocalDate fromYear = LocalDate.of(fromYearInt, 1, 1);
		
		int toYearInt = currentDate.getYear() - minAge;
		LocalDate toYear = LocalDate.of(toYearInt, 12, 31);

		return calculatorPage(candidateDao.countCandidateByContentAndAgeAndSkillAndPassEntryTest(content, fromYear, toYear, listId));
	}

	/**
	 * Convert list candidate entity to dto
	 * @param listCandidate
	 * @return
	 */
	private List<CandidateDto> convertToListDto(List<Candidate> listCandidate) {
		
		List<CandidateDto> listCandidateDto = new ArrayList<CandidateDto>();
		
		for(Candidate candidate : listCandidate) {
			CandidateDto candidateDto = new CandidateDto(candidate);
			listCandidateDto.add(candidateDto);
		}
		
		return listCandidateDto;
	}
	
	/**
	 * Convert to list interview entity to dto
	 * @param listInterview
	 * @return
	 */
	private List<InterviewDto> convertToListInterviewDto(List<Interview> listInterview) {
		
		List<InterviewDto> listInterviewDto = new ArrayList<InterviewDto>();
		
		for(Interview interview : listInterview) {
			InterviewDto interviewDto = new InterviewDto(interview);
			listInterviewDto.add(interviewDto);
		}
		
		return listInterviewDto;
	}

	/**
	 * Convert age to year born
	 * @param age
	 * @return
	 */
	private Integer convertAgeToYearBorn(Integer age){

		LocalDate currentDate = LocalDate.now();
		Integer yearBorn = currentDate.getYear() - age;
		return yearBorn;
	}

}
