package com.demoipm.dao;

import java.time.LocalDate;
import java.util.List;

import com.demoipm.dto.candidatefilter.CandidateFilter;
import com.demoipm.entities.Candidate;
import com.demoipm.entities.Interview;

public interface CandidateDaoCustom {

	/**
	 * Filter candidate
	 * @param candidateFilter
	 * @return
	 */
	List<Candidate> filter(CandidateFilter candidateFilter);
}
