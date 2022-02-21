package com.demoipm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.CandidateDao;
import com.demoipm.dao.InterviewDao;
import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewDto;
import com.demoipm.dto.candidatefilter.CandidateFilter;
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
	 * Count row
	 */
	@Override
	public Integer countRow(CandidateFilter candidateFilter) {

		return candidateDao.countRow(candidateFilter);
	}

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
	 * Get list interview by candidate id
	 */
	@Override
	public List<InterviewDto> getListInterviewByCandidateId(int id) {

		List<Interview> listInterview = interviewDao.findByCandidateId(id).orElse(new ArrayList<>());
		
		return convertToListInterviewDto(listInterview);
	}

	/**
	 * Filter candidate
	 */
	@Override
	public List<CandidateDto> filter(CandidateFilter candidateFilter, Integer page) {

		List<Candidate> listEntity = candidateDao.filter(candidateFilter, page);
		
		return convertToListDto(listEntity);
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
		
		List<InterviewDto> listInterviewDto = new ArrayList<>();
		
		for(Interview interview : listInterview) {
			InterviewDto interviewDto = new InterviewDto(interview);
			listInterviewDto.add(interviewDto);
		}
		
		return listInterviewDto;
	}

}
