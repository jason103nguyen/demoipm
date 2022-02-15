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
import com.demoipm.dto.potentialcandidate.PotentialCandidatePageDto;
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

	/**
	 * Filter Search and Sort and Pagination PotentialCandidate
	 * 
	 * 
	 * @param keySearch, pageNo, pageSize, sortBy
	 * @return List PotentialCandidate Filtered
	 */
	@Override
	public PotentialCandidatePageDto FilterPotentialCandidateIsDelete(String keySearch, int pageNo, int pageSize, String sortBy, String direction ) {
		
		PotentialCandidatePageDto pageDTO = new PotentialCandidatePageDto();

		List<CandidateDto> listCandidateDto = new ArrayList<CandidateDto>();

		try {
			
			Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
			
			
			if ("id".equals(sortBy) && "ASC".equals(direction)) {
				sort = Sort.by(Sort.Direction.ASC, "id");
			} else {
				if ("id".equals(sortBy) && "DESC".equals(direction)){		
					sort = Sort.by(Sort.Direction.DESC, "id");
				}
			}
			
			if ("fullName".equals(sortBy) && "ASC".equals(direction)) {
				sort = Sort.by(Sort.Direction.ASC, "fullName");
			} else {
				if ("fullName".equals(sortBy) && "DESC".equals(direction)){		
					sort = Sort.by(Sort.Direction.DESC, "fullName");
				}
			}

			if ("email".equals(sortBy) && "ASC".equals(direction)) {
				sort = Sort.by(Sort.Direction.ASC, "email");
			} else {
				if ("email".equals(sortBy) && "DESC".equals(direction)){		
					sort = Sort.by(Sort.Direction.DESC, "email");
				}
			}
			
			if("status".equals(sortBy) && "ASC".equals(direction)) {
				sort = Sort.by(Sort.Direction.ASC, "status");
			} else {
				if ("status".equals(sortBy) && "DESC".equals(direction)){		
					sort = Sort.by(Sort.Direction.DESC, "status");
				}
			}

			Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

			Page<Candidate> listCandidate = potentialCandidateDao.listSearchPotentialCandidateIsDelete(keySearch, false, pageable);
			
			for (Candidate candidate : listCandidate.getContent()) {
				CandidateDto candidateDto = new CandidateDto(candidate);
				listCandidateDto.add(candidateDto);
			}
			
			pageDTO.setListPotentialCandidate(listCandidateDto);
			pageDTO.setTotalPage(listCandidate.getTotalPages());
			pageDTO.setCurrentPage(pageNo);
			pageDTO.setKeySearch(keySearch);
			pageDTO.setPageNo(pageNo);
			pageDTO.setSortBy(sortBy);
			pageDTO.setDirection(direction);
			
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
