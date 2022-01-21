package com.demoipm.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demoipm.dao.PotentialCandidateDao;
import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.potentialcandidate.pageDTO;
import com.demoipm.entities.Candidate;
import com.demoipm.service.PotentialCandidateService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PotentialCandidateServiceImpl implements PotentialCandidateService {

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

		if (candidate.isPresent()) {
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
	public List<CandidateDto> getAllPotentialCandidate() {

		List<CandidateDto> listCandidateDto = new ArrayList<>();
		try {
			List<Candidate> listCandidate = potentialCandidateDao.findPotentialCandidateIsDelete(false);

			for (Candidate candidate : listCandidate) {
				CandidateDto candidateDto = new CandidateDto(candidate);
				listCandidateDto.add(candidateDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCandidateDto;
	}

	@Override
	public List<CandidateDto> findPotentialCandidateWithSorting(String field) {

		List<CandidateDto> listCandidateDto = new ArrayList<>();

		try {

			if ("fullName".equals(field)) {

				List<Candidate> listCandidatesWithSorting = potentialCandidateDao
						.findAll(Sort.by(Sort.Direction.DESC, "fullName"));
				for (Candidate candidate : listCandidatesWithSorting) {
					CandidateDto candidateDto = new CandidateDto(candidate);
					listCandidateDto.add(candidateDto);
				}
			}
			if ("email".equals(field)) {

				List<Candidate> listCandidatesWithSorting = potentialCandidateDao
						.findAll(Sort.by(Sort.Direction.DESC, "email"));
				for (Candidate candidate : listCandidatesWithSorting) {
					CandidateDto candidateDto = new CandidateDto(candidate);
					listCandidateDto.add(candidateDto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listCandidateDto;
	}

	@Override
	public pageDTO searchPotentialCandidateIsDelete(String keySearch, int pageNo, int pageSize, String field) {
		
		pageDTO pageDTO = new pageDTO();

		List<CandidateDto> listCandidateDto = new ArrayList<CandidateDto>();

		try {
			
			Sort sort = Sort.by(Sort.Direction.ASC, field);
			
			if ("fullName".equals(field)) {
				
				sort = Sort.by(Sort.Direction.DESC, "fullName");
			}
			
			if ("email".equals(field)) {
				
				sort = Sort.by(Sort.Direction.DESC, "email");
			}

			Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

			Page<Candidate> listCandidate = potentialCandidateDao.listSearchPotentialCandidateIsDelete(keySearch, false, pageable);
			
			for (Candidate candidate : listCandidate.getContent()) {
				CandidateDto candidateDto = new CandidateDto(candidate);
				listCandidateDto.add(candidateDto);
			}
			
			pageDTO.setList(listCandidateDto);
			pageDTO.setTotalPage(listCandidate.getTotalPages());
			pageDTO.setCurrentPage(pageNo);
			pageDTO.setKeySearch(keySearch);
			pageDTO.setPageNo(pageNo);
			pageDTO.setField(field);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageDTO;
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
			if (candidate != null) {
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
			if (candidate.isPresent()) {
				candidate.get().setIsDelete(true);
				potentialCandidateDao.save(candidate.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
