package com.demoipm.service;

import com.demoipm.dto.CandidateDto;

public interface PotentialCandidateService {

	CandidateDto getPotentialCandidateByID(int id);

	void createPotentialCandidate(CandidateDto candidateDto);

}
