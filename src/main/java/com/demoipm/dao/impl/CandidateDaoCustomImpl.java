package com.demoipm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.demoipm.dao.CandidateDaoCustom;
import com.demoipm.entities.Candidate;

public class CandidateDaoCustomImpl implements CandidateDaoCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Candidate> search(String content) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Candidate> query = cb.createQuery(Candidate.class);
		Root<Candidate> candidate = query.from(Candidate.class);
		
		Path<String> fullNamePath = candidate.get("fullName");
		Path<String> statusPath = candidate.get("status");
		
		List<Predicate> predicate = new ArrayList<Predicate>();
		predicate.add(cb.like(fullNamePath, "%" + content + "%"));
		predicate.add(cb.like(statusPath, content));
		
		query.select(candidate).where(cb.or(predicate.toArray(new Predicate[predicate.size()])));
		return entityManager.createQuery(query).getResultList();
		
	}

}
