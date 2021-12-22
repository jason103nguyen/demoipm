package com.demoipm.dao;

import java.util.List;

import com.demoipm.entities.Candidate;

public interface CandidateDaoCustom {

	List<Candidate> search(String content);
}
