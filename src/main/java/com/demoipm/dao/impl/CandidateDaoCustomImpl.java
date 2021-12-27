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
import com.demoipm.entities.Interview;

public class CandidateDaoCustomImpl implements CandidateDaoCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Candidate> search(String content) {
		
		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT c FROM Candidate c " +
				"JOIN c.listEntryTest et " +
				"WHERE et.point >= :point AND " +
				"c.fullName LIKE '%' || :content || '%'", Candidate.class)
				.setParameter("point", EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter("content", content)
				.getResultList();
				
		return listCandidate;
		
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
		
		List<Candidate> listCandidate = entityManager.createQuery(
			"SELECT DISTINCT c FROM Candidate c"
			+ " JOIN c.listEntryTest et " 
			+ " JOIN c.listSkillCandidate sc " 
			+ " WHERE et.point >= ?1 " 
			+ " AND sc.skill.id IN (?2)", Candidate.class)
		.setParameter(1, EntryTestInfo.POINT_PASS_ENTRY_TEST)
		.setParameter(2, listId)
		.getResultList();
		
		return listCandidate;
	}

	@Override
	public List<Candidate> readCandidatePassEntryTest(Integer page) {

		List<Candidate> listCandidate = entityManager.createQuery(
			"SELECT c FROM Candidate c "
			+ " JOIN c.listEntryTest et " 
			+ " WHERE et.point >= ?1", Candidate.class)
		.setParameter(1, EntryTestInfo.POINT_PASS_ENTRY_TEST)
		.setMaxResults(PaginationInfo.MAX_RESULT)
		.setFirstResult(page * PaginationInfo.MAX_RESULT)
		.getResultList();
		
		return listCandidate;
	}

	@Override
	public Integer countCandidatePassEntryTest() {

		Long totalRow = entityManager.createQuery(
			"SELECT COUNT(c.id) FROM Candidate c "
			+ "JOIN c.listEntryTest et " 
			+ " WHERE et.point >= ?1", Long.class)
			.setParameter(1, EntryTestInfo.POINT_PASS_ENTRY_TEST)
			.getSingleResult();

		return totalRow.intValue();
	}

	@Override
	public List<Candidate> filterCandidateByAgeAndSkillAndPassEntryTest(
			LocalDate fromYear, LocalDate toYear, List<Integer> listId) {
		
		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT DISTINCT c FROM Candidate c " 
				+ " JOIN c.listEntryTest et " 
				+ " JOIN c.listSkillCandidate sc "
				+ " WHERE et.point >= ?1 " 
				+ " AND sc.skill.id IN (?2) " 
				+ " AND c.birthDay BETWEEN ?3 AND ?4", Candidate.class)
				.setParameter(1, EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter(2, listId)
				.setParameter(3, fromYear)
				.setParameter(4, toYear)
				.getResultList();
				
		return listCandidate;
	}

	@Override
	public List<Interview> findListInterviewByCandidateId(int id) {

		List<Interview> listInterview = entityManager.createQuery(
				"SELECT Interview FROM Candidate c "
				+ " JOIN c.listInterview li "
				+ " WHERE c.id = ?1", Interview.class)
				.setParameter(1, id)
				.getResultList();
				
		return listInterview;
	}

	@Override
	public List<Candidate> filterCandidateByContentAndSkillAndPassEntryTest(String content, List<Integer> listId) {
		
		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT DISTINCT c FROM Candidate c "
				+ " JOIN c.listEntryTest et " 
				+ " JOIN c.listSkillCandidate sc "
				+ " WHERE et.point >= ?1 " 
				+ " AND sc.skill.id IN (?2) " 
				+ " AND c.fullName LIKE '%' || :content || '%'", Candidate.class)
				.setParameter(1, EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter(2, listId)
				.setParameter("content", content)
				.getResultList();
				
		return listCandidate;
	}

	@Override
	public List<Candidate> filterCandidateByContentAndAgeAndPassEntryTest(String content, LocalDate fromYear, LocalDate toYear) {

		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT c FROM Candidate c "
				+ " JOIN c.listEntryTest et "
				+ " WHERE et.point >= :point AND "
				+ " c.birthDay BETWEEN :fromYear AND :toYear AND "
				+ " c.fullName LIKE '%' || :content || '%'", Candidate.class)
				.setParameter("point", EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.setParameter("content", content)
				.getResultList();
				
		return listCandidate;
	}

	@Override
	public List<Candidate> filterCandidateByContentAndAgeAndSkillAndPassEntryTest(String content, LocalDate fromYear,
	LocalDate toYear, List<Integer> listId) {

		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT DISTINCT c FROM Candidate c "
				+ " JOIN c.listEntryTest et JOIN c.listSkillCandidate sc "
				+ " WHERE "
				+ " et.point >= :point AND "
				+ " c.birthDay BETWEEN :fromYear AND :toYear AND "
				+ " c.fullName LIKE '%' || :content || '%' AND "
				+ " sc.Skill.id IN (:listId)", Candidate.class)
				.setParameter("point", EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.setParameter("listId", listId)
				.getResultList();
				
		return listCandidate;
	}

}
