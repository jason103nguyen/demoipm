package com.demoipm.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.PotentialCandidateDao;
import com.demoipm.dto.CandidateDto;
import com.demoipm.entities.Candidate;
import com.demoipm.service.PotentialCandidateService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class PotentialCandidateImpl implements PotentialCandidateService{

	@Autowired
	PotentialCandidateDao potentialCandidateDao;
	
	private ObjectMapper mapper;
	
	@Override
	public void createPotentialCandidate(CandidateDto candidateDto) {
		Candidate candidate = mapper.convertValue(candidateDto, Candidate.class);
		potentialCandidateDao.save(candidate);
	}
	
	
	@Override
	public CandidateDto getPotentialCandidateByID(int id) {
		
		CandidateDto candidateDto = null;
		Optional<Candidate> candidate = potentialCandidateDao.findById(id);
		if(candidate.isPresent()) {
			candidateDto = new CandidateDto(candidate.get());
		}
		return candidateDto;
	}
}
