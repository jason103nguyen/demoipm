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

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PotentialCandidateImpl implements PotentialCandidateService{

	@Autowired
	private PotentialCandidateDao potentialCandidateDao;
	
	private ObjectMapper mapper;
	
	/**
	 * Create Potential Candidate
	 * (
	 * @param candidateDto
	 */
	@Override
	public void createPotentialCandidate(CandidateDto candidateDto) {
		Candidate candidate = mapper.convertValue(candidateDto, Candidate.class);
		potentialCandidateDao.save(candidate);
	}
	
	/**
	 * Get Potential Candidate By ID
	 * 
	 * @param id
	 * @return Potential Candidate
	 */
	@Override
	public CandidateDto getPotentialCandidateByID(int id) {
		
		CandidateDto candidateDto = null;
		Optional<Candidate> candidate = potentialCandidateDao.findById(id);
		if(candidate.isPresent()) {
			candidateDto = new CandidateDto(candidate.get());
		}
		return candidateDto;
	}
	
	/**
	 * Get all PotentialCandidate
	 * 
	 * 
	 * @return List All PotentialCandidate
	 */
	
	@Override
	public List<CandidateDto> getAllPotentialCandidate(){
		
		List<CandidateDto> listCandidateDto = new ArrayList<>();
		try {	
			List<Candidate> listCandidate = potentialCandidateDao.findAll();
			
			for(Candidate candidate : listCandidate) {
				CandidateDto candidateDto = new CandidateDto(candidate);
				listCandidateDto.add(candidateDto);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCandidateDto;
	}
}
