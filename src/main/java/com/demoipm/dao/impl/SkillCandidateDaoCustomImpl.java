package com.demoipm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.demoipm.Constant.EntryTestInfo;
import com.demoipm.dao.SkillCandidateDaoCustom;
import com.demoipm.entities.Candidate;

public class SkillCandidateDaoCustomImpl implements SkillCandidateDaoCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Candidate> getCandidateBySkillAndPassEntryTest(int[] listId) {
		
		return null;
	}

}
