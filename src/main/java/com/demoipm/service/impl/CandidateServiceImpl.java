package com.demoipm.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.CandidateDao;
import com.demoipm.dao.EntryTestDao;
import com.demoipm.dao.InterviewDao;
import com.demoipm.dao.SkillCandidateDao;
import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewDto;
import com.demoipm.entities.Candidate;
import com.demoipm.entities.EntryTest;
import com.demoipm.entities.Interview;
import com.demoipm.service.CandidateService;

@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private EntryTestDao entryTestDao;
	
	@Autowired
	private InterviewDao interviewDao;
	
	@Autowired
	private SkillCandidateDao skillCandidateDao;
	
	@Override
	public void create(CandidateDto candidateDto) {

		Candidate candidate = new Candidate(candidateDto);
		candidateDao.save(candidate);
	}

	@Override
	public CandidateDto readById(int id) throws Exception {

		Optional<Candidate> candidate = candidateDao.findById(id);
		CandidateDto candidateDto = null;
		if (candidate.isEmpty()) {
			throw new Exception("The id doesn't exists");
		} else {
			candidateDto = new CandidateDto(candidate.get());
		}
		return candidateDto;
	}

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

	@Override
	public void update(CandidateDto candidateDto) {

		Candidate candidate = new Candidate(candidateDto);
		candidateDao.save(candidate);
	}

	@Override
	public void deleteById(int id) {

		if (candidateDao.existsById(id)) {
			candidateDao.deleteById(id);
		}
	}

	@Override
	public List<CandidateDto> readCandidatePassEntryTest() {

		List<EntryTest> listEntryTest = entryTestDao.readPassEntryTest();
		List<CandidateDto> listCandidateDto = new ArrayList<CandidateDto>();

		for (EntryTest entryTest : listEntryTest) {
			Candidate candidate = entryTest.getCandidate();
			CandidateDto candidateDto = new CandidateDto(candidate);
			listCandidateDto.add(candidateDto);
		}
		
		return listCandidateDto;
	}

	@Override
	public List<InterviewDto> getListInterviewByCandidateId(int id) {

		List<Interview> listInterview = interviewDao.findByCandidateId(id);
		
		return convertToListInterviewDto(listInterview);
	}

	@Override
	public List<CandidateDto> searchCandidate(String content) {
		
		List<Candidate> listCandidate = candidateDao.search(content);
		List<CandidateDto> listCandidateDto = new ArrayList<CandidateDto>();
		
		for(Candidate candidate : listCandidate) {
			CandidateDto candidateDto = new CandidateDto(candidate);
			listCandidateDto.add(candidateDto);
		}
		return listCandidateDto;
	}

	@Override
	public List<CandidateDto> filterCandidateByAge(int minAge, int maxAge) {
		
		LocalDate currentDate = LocalDate.now();
		int fromYearInt = currentDate.getYear() - maxAge;
		LocalDate fromYear = LocalDate.of(fromYearInt, 1, 1);
		
		int toYearInt = currentDate.getYear() - minAge;
		LocalDate toYear = LocalDate.of(toYearInt, 12, 31);
		
		List<EntryTest> listEntryTest = entryTestDao.readPassEntryTestfromYearToYearBirthDay(fromYear, toYear);
		List<CandidateDto> listCandidateDto = new ArrayList<CandidateDto>();

		for (EntryTest entryTest : listEntryTest) {
			Candidate candidate = entryTest.getCandidate();
			CandidateDto candidateDto = new CandidateDto(candidate);
			listCandidateDto.add(candidateDto);
		}
		
		return listCandidateDto;
	}

	@Override
	public List<CandidateDto> filterCandidateBySkill(List<Integer> listId) {
		
		List<Candidate> listCandidate = candidateDao.getCandidateBySkillAndPassEntryTest(listId);
		
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		return listCandidateDto;
	}
	
	private List<CandidateDto> convertToListDto(List<Candidate> listCandidate) {
		
		List<CandidateDto> listCandidateDto = new ArrayList<CandidateDto>();
		
		for(Candidate candidate : listCandidate) {
			CandidateDto candidateDto = new CandidateDto(candidate);
			listCandidateDto.add(candidateDto);
		}
		
		return listCandidateDto;
	}
	
	private List<InterviewDto> convertToListInterviewDto(List<Interview> listInterview) {
		
		List<InterviewDto> listInterviewDto = new ArrayList<InterviewDto>();
		
		for(Interview interview : listInterview) {
			InterviewDto interviewDto = new InterviewDto(interview);
			listInterviewDto.add(interviewDto);
		}
		
		return listInterviewDto;
	}

	@Override
	public List<CandidateDto> readCandidatePassEntryTest(Integer page) {

		List<Candidate> listCandidate = candidateDao.readCandidatePassEntryTest(page);
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		return listCandidateDto;
	}

	@Override
	public Integer countCandidatePassEntryTest() {

		Integer totalRow = candidateDao.countCandidatePassEntryTest();
		return totalRow;
	}

	@Override
	public List<CandidateDto> filterCandidateByAgeAndSkill(Integer minAge, Integer maxAge, List<Integer> listId) {
		
		LocalDate currentDate = LocalDate.now();
		int fromYearInt = currentDate.getYear() - maxAge;
		LocalDate fromYear = LocalDate.of(fromYearInt, 1, 1);
		
		int toYearInt = currentDate.getYear() - minAge;
		LocalDate toYear = LocalDate.of(toYearInt, 12, 31);
		
		List<Candidate> listCandidate = candidateDao.filterCandidateByAgeAndSkillAndPassEntryTest(fromYear, toYear, listId);
		
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		return listCandidateDto;
	}

	@Override
	public List<CandidateDto> filterCandidateByContentAndSkill(String content, List<Integer> listId) {
		
		List<Candidate> listCandidate = candidateDao.filterCandidateByContentAndSkillAndPassEntryTest(content, listId);
		
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		return listCandidateDto;
	}

	@Override
	public List<CandidateDto> filterCandidateByContentAndAge(String content, Integer minAge, Integer maxAge){

		LocalDate currentDate = LocalDate.now();
		int fromYearInt = currentDate.getYear() - maxAge;
		LocalDate fromYear = LocalDate.of(fromYearInt, 1, 1);
		
		int toYearInt = currentDate.getYear() - minAge;
		LocalDate toYear = LocalDate.of(toYearInt, 12, 31);

		List<Candidate> listCandidate = candidateDao.filterCandidateByContentAndAgeAndPassEntryTest(content, fromYear, toYear);
		
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		return listCandidateDto;
	}

	@Override
	public List<CandidateDto> filterCandidateByContentAndAgeAndSkill(String content, Integer minAge, Integer maxAge,
			List<Integer> listId) {

		LocalDate currentDate = LocalDate.now();
		int fromYearInt = currentDate.getYear() - maxAge;
		LocalDate fromYear = LocalDate.of(fromYearInt, 1, 1);
		
		int toYearInt = currentDate.getYear() - minAge;
		LocalDate toYear = LocalDate.of(toYearInt, 12, 31);

		List<Candidate> listCandidate = candidateDao.filterCandidateByContentAndAgeAndSkillAndPassEntryTest(content, fromYear, toYear, listId);
		
		List<CandidateDto> listCandidateDto = convertToListDto(listCandidate);
		return listCandidateDto;
	}

	private Integer convertAgeToYearBorn(Integer age){

		LocalDate currentDate = LocalDate.now();
		Integer yearBorn = currentDate.getYear() - age;
		return yearBorn;
	}

}
