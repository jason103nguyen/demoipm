package com.demoipm.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.PotentialCandidateDao;
import com.demoipm.dto.CandidateDto;
import com.demoipm.entities.Candidate;
import com.demoipm.service.PotentialCandidateService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PotentialCandidateImpl implements PotentialCandidateService{

	@Autowired
	private PotentialCandidateDao potentialCandidateDao;
		
	/**
	 * Create Potential Candidate
	 * 
	 * @param candidateDto
	 */
	@Override
	public void createPotentialCandidate(CandidateDto candidateDto) {
		
		try {
			Candidate candidate = new Candidate(candidateDto);
			candidate.setCreatedDate(new Date());
			potentialCandidateDao.save(candidate);	
		} catch (Exception e) {
			e.printStackTrace();
		}	
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
		Optional<Candidate> candidate = potentialCandidateDao.findByPotentialCandidateIdAndIsDelete(id, false);

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
			List<Candidate> listCandidate = potentialCandidateDao.findPotentialCandidateIsDelete(false);
			
			for(Candidate candidate : listCandidate) {
				CandidateDto candidateDto = new CandidateDto(candidate);
				listCandidateDto.add(candidateDto);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCandidateDto;
	}
	
	/**
	 * Search Potential Candidate By Key Search
	 * 
	 * 
	 * @param keySearch
	 * @return List Potential Candidate
	 */
	
	@Override
	public List<CandidateDto> searchPotentialCandidateIsDelete(String keySearch){
		
		List<CandidateDto> listCandidateDto = new ArrayList<CandidateDto>();
		
		try {
			List<Candidate> listCandidate = potentialCandidateDao.ListSearchPotentialCandidateIsDelete(keySearch, false);
			for(Candidate candidate : listCandidate) {
				CandidateDto candidateDto = new CandidateDto(candidate);
				listCandidateDto.add(candidateDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listCandidateDto;	
	}

	/**
	 * Update PotentialCandidate Info To DB
	 * 
	 * 
	 * @param candidateDto
	 */
	
	@Override
	public void updatePotentialCandidate(CandidateDto candidateDto) {
					
		Candidate candidate = potentialCandidateDao.findByPotentialCandidateId(candidateDto.getId(), false);
		
		try {
			  if(candidate != null) {
				  candidate = new Candidate(candidateDto);  
				  candidate.setUpdatedDate(new Date());
				  potentialCandidateDao.save(candidate);	  
			  }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete Potential Candidate Info DB
	 * 
	 * @param id
	 */
	@Override
	public void deletePotentialCandidate(int id) {
		
		try {
			Optional<Candidate> candidate = potentialCandidateDao.findByPotentialCandidateIdAndIsDelete(id, false);
			if(candidate.isPresent()) {
				candidate.get().setIsDelete(true);
				potentialCandidateDao.save(candidate.get());
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
