package com.demoipm.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demoipm.entities.Candidate;
import com.demoipm.entities.SkillCandidate;

public interface SkillCandidateDaoCustom {

	List<Candidate> getCandidateBySkillAndPassEntryTest(int[] listId);
}
