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
	public List<Candidate> filterCandidateByContent(String content, Integer page) {
		
		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT c FROM Candidate c " +
				"JOIN c.listEntryTest et " +
				"WHERE et.point >= :point AND " +
				"c.fullName LIKE '%' || :content || '%'", Candidate.class)
				.setParameter("point", EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter("content", content)
				.setMaxResults(PaginationInfo.MAX_RESULT)
				.setFirstResult(page * PaginationInfo.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
		
	}
	
	@Override
	public Integer countCandidateByContent(String content) {
		
		Long totalRow = entityManager.createQuery(
				"SELECT COUNT(c) FROM Candidate c " +
				"JOIN c.listEntryTest et " +
				"WHERE et.point >= :point AND " +
				"c.fullName LIKE '%' || :content || '%'", Long.class)
				.setParameter("point", EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter("content", content)
				.getSingleResult();
				
		return totalRow.intValue();
		
	}

	@Override
	public List<Candidate> filterCandidateByAge(LocalDate fromYear, LocalDate toYear, Integer page) {

		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT c FROM Candidate c " 
				+ " JOIN c.listEntryTest et " 
				+ " WHERE et.point >= :point " 
				+ " AND c.birthDay BETWEEN :fromYear AND :toYear", Candidate.class)
				.setParameter("point", EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.setMaxResults(PaginationInfo.MAX_RESULT)
				.setFirstResult(page * PaginationInfo.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}

	@Override
	public Integer countCandidateByAge(LocalDate fromYear, LocalDate toYear) {

		Long totalRow = entityManager.createQuery(
				"SELECT COUNT(c) FROM Candidate c " 
				+ " JOIN c.listEntryTest et " 
				+ " WHERE et.point >= :point " 
				+ " AND c.birthDay BETWEEN :fromYear AND :toYear", Long.class)
				.setParameter("point", EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.getSingleResult();
				
		return totalRow.intValue();
	}

	@Override
	public List<Candidate> filterCandidateBySkillAndPassEntryTest(List<Integer> listId, Integer page) {
		
		List<Candidate> listCandidate = entityManager.createQuery(
			"SELECT DISTINCT c FROM Candidate c"
			+ " JOIN c.listEntryTest et " 
			+ " JOIN c.listSkillCandidate sc " 
			+ " WHERE et.point >= ?1 " 
			+ " AND sc.skill.id IN (?2)", Candidate.class)
		.setParameter(1, EntryTestInfo.POINT_PASS_ENTRY_TEST)
		.setParameter(2, listId)
		.setMaxResults(PaginationInfo.MAX_RESULT)
		.setFirstResult(page * PaginationInfo.MAX_RESULT)
		.getResultList();
		
		return listCandidate;
	}

	@Override
	public Integer countCandidateBySkillAndPassEntryTest(List<Integer> listId) {
		
		Long totalRow = entityManager.createQuery(
			"SELECT COUNT(DISTINCT c) FROM Candidate c"
			+ " JOIN c.listEntryTest et " 
			+ " JOIN c.listSkillCandidate sc " 
			+ " WHERE et.point >= ?1 " 
			+ " AND sc.skill.id IN (?2)", Long.class)
		.setParameter(1, EntryTestInfo.POINT_PASS_ENTRY_TEST)
		.setParameter(2, listId)
		.getSingleResult();
		
		return totalRow.intValue();
	}

	@Override
	public List<Candidate> filterCandidateByAgeAndSkillAndPassEntryTest(
			LocalDate fromYear, LocalDate toYear, List<Integer> listId, Integer page) {
		
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
				.setMaxResults(PaginationInfo.MAX_RESULT)
				.setFirstResult(page * PaginationInfo.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}

	@Override
	public Integer countCandidateByAgeAndSkillAndPassEntryTest(
			LocalDate fromYear, LocalDate toYear, List<Integer> listId) {
		
		Long totalRow = entityManager.createQuery(
				"SELECT COUNT(DISTINCT c) FROM Candidate c " 
				+ " JOIN c.listEntryTest et " 
				+ " JOIN c.listSkillCandidate sc "
				+ " WHERE et.point >= ?1 " 
				+ " AND sc.skill.id IN (?2) " 
				+ " AND c.birthDay BETWEEN ?3 AND ?4", Long.class)
				.setParameter(1, EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter(2, listId)
				.setParameter(3, fromYear)
				.setParameter(4, toYear)
				.getSingleResult();
				
		return totalRow.intValue();
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
	public List<Candidate> filterCandidateByContentAndSkillAndPassEntryTest(String content, List<Integer> listId,
		Integer page) {
		
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
				.setMaxResults(PaginationInfo.MAX_RESULT)
				.setFirstResult(page * PaginationInfo.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}

	@Override
	public Integer countCandidateByContentAndSkillAndPassEntryTest(String content, List<Integer> listId) {
		
		Long totalRow = entityManager.createQuery(
				"SELECT COUNT (DISTINCT c) FROM Candidate c "
				+ " JOIN c.listEntryTest et " 
				+ " JOIN c.listSkillCandidate sc "
				+ " WHERE et.point >= ?1 " 
				+ " AND sc.skill.id IN (?2) " 
				+ " AND c.fullName LIKE '%' || :content || '%'", Long.class)
				.setParameter(1, EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter(2, listId)
				.setParameter("content", content)
				.getSingleResult();
				
		return totalRow.intValue();
	}

	@Override
	public List<Candidate> filterCandidateByContentAndAgeAndPassEntryTest(String content, LocalDate fromYear, 
		LocalDate toYear, Integer page) {

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
				.setMaxResults(PaginationInfo.MAX_RESULT)
				.setFirstResult(page * PaginationInfo.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}

	@Override
	public Integer countCandidateByContentAndAgeAndPassEntryTest(String content, LocalDate fromYear, LocalDate toYear) {

		Long listCandidate = entityManager.createQuery(
				"SELECT COUNT (DISTINCT c) FROM Candidate c "
				+ " JOIN c.listEntryTest et "
				+ " WHERE et.point >= :point AND "
				+ " c.birthDay BETWEEN :fromYear AND :toYear AND "
				+ " c.fullName LIKE '%' || :content || '%'", Long.class)
				.setParameter("point", EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.setParameter("content", content)
				.getSingleResult();
				
		return listCandidate.intValue();
	}

	@Override
	public List<Candidate> filterCandidateByContentAndAgeAndSkillAndPassEntryTest(String content, LocalDate fromYear,
	LocalDate toYear, List<Integer> listId, Integer page) {

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
				.setMaxResults(PaginationInfo.MAX_RESULT)
				.setFirstResult(page * PaginationInfo.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}

	@Override
	public Integer countCandidateByContentAndAgeAndSkillAndPassEntryTest(String content, LocalDate fromYear,
	LocalDate toYear, List<Integer> listId) {

		Long totalRow = entityManager.createQuery(
				"SELECT COUNT(DISTINCT c) FROM Candidate c "
				+ " JOIN c.listEntryTest et JOIN c.listSkillCandidate sc "
				+ " WHERE "
				+ " et.point >= :point AND "
				+ " c.birthDay BETWEEN :fromYear AND :toYear AND "
				+ " c.fullName LIKE '%' || :content || '%' AND "
				+ " sc.Skill.id IN (:listId)", Long.class)
				.setParameter("point", EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.setParameter("listId", listId)
				.getSingleResult();
				
		return totalRow.intValue();
	}

	@Override
	public List<Candidate> filterCandidatePassEntryTest(Integer page) {

		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT DISTINCT c FROM Candidate c "
				+ " JOIN c.listEntryTest et "
				+ " WHERE "
				+ " et.point >= :point", Candidate.class)
				.setParameter("point", EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.setMaxResults(PaginationInfo.MAX_RESULT)
				.setFirstResult(page * PaginationInfo.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}

	@Override
	public Integer countCandidatePassEntryTest() {

		Long totalRow = entityManager.createQuery(
				"SELECT COUNT(DISTINCT c) FROM Candidate c "
				+ " JOIN c.listEntryTest et "
				+ " WHERE "
				+ " et.point >= :point", Long.class)
				.setParameter("point", EntryTestInfo.POINT_PASS_ENTRY_TEST)
				.getSingleResult();
				
		return totalRow.intValue();
	}

}
