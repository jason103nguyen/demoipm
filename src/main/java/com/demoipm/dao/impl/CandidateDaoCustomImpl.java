package com.demoipm.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.demoipm.Constant.EntryTestInfo;
import com.demoipm.Constant.PaginationInfo;
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
		
		List<Predicate> predicateContent = new ArrayList<Predicate>();
		predicateContent.add(cb.like(fullNamePath, "%" + content + "%"));
		predicateContent.add(cb.like(statusPath, content));
		
		Predicate predicateOr = cb.or(predicateContent.toArray(new Predicate[predicateContent.size()]));

		query.select(candidate).where(predicateOr);
		return entityManager.createQuery(query).getResultList();
		
	}

	@Override
	public List<Candidate> filterCandidateByAge(LocalDate fromYear, LocalDate toYear) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Candidate> query = cb.createQuery(Candidate.class);
		Root<Candidate> candidate = query.from(Candidate.class);
		
		Predicate betweenYearBirthDay = cb.between(candidate.get("birthDay"), fromYear, toYear);

		query.select(candidate).where(betweenYearBirthDay);
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<Candidate> getCandidateBySkillAndPassEntryTest(List<Integer> listId) {
		
		List<Candidate> listCandidate = entityManager.createQuery("SELECT c FROM Candidate c "
		+ "JOIN c.listEntryTest et JOIN c.listSkillCandidate sc WHERE et.point >= ?1 AND sc.skill.id IN (?2)", Candidate.class)
		.setParameter(1, EntryTestInfo.POINT_PASS_ENTRY_TEST)
		.setParameter(2, listId)
		.getResultList();
		
		return listCandidate;
	}

	@Override
	public List<Candidate> readCandidatePassEntryTest(Integer page) {

		List<Candidate> listCandidate = entityManager.createQuery("SELECT c FROM Candidate c "
		+ "JOIN c.listEntryTest et WHERE et.point >= ?1", Candidate.class)
		.setParameter(1, EntryTestInfo.POINT_PASS_ENTRY_TEST)
		.setMaxResults(PaginationInfo.MAX_RESULT)
		.setFirstResult(page * PaginationInfo.MAX_RESULT)
		.getResultList();
		
		return listCandidate;
	}

	@Override
	public Integer countCandidatePassEntryTest() {

		Long totalRow = entityManager.createQuery("SELECT COUNT(c.id) FROM Candidate c "
		+ "JOIN c.listEntryTest et WHERE et.point >= ?1", Long.class)
			.setParameter(1, EntryTestInfo.POINT_PASS_ENTRY_TEST)
			.getSingleResult();

		return totalRow.intValue();
	}

}
