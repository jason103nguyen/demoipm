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

import com.demoipm.consts.EntryTestInfoConst;
import com.demoipm.consts.PaginationInfoConst;
import com.demoipm.dao.CandidateDaoCustom;
import com.demoipm.dto.candidatefilter.CandidateFilter;
import com.demoipm.entities.Candidate;
import com.demoipm.entities.Interview;

public class CandidateDaoCustomImpl implements CandidateDaoCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Filter candidate
	 */
	public List<Candidate> filter(CandidateFilter candidateFilter) {
		
		List<Candidate> listCandidate = new ArrayList<Candidate>();
		
		String fromQuery = "SELECT DISTINCT c FROM Candidate c";
		String joinQuery = " JOIN c.listEntryTest et JOIN c.listSkillCandidate sc JOIN c.listInterview li";
		String conditionquery = " WHERE et.point >= " + EntryTestInfoConst.POINT_PASS_ENTRY_TEST + " ";
		
		if (!contentIsEmpty(candidateFilter.getContent())) {
			conditionquery += " AND c.fullName LIKE '%' || '" + candidateFilter.getContent() + "' || '%'";
		}
		
		if (!ageIsEmpty(candidateFilter.getMinAge(), candidateFilter.getMaxAge())) {
			
			LocalDate currentDate = LocalDate.now();
			int fromYear = currentDate.getYear() - candidateFilter.getMaxAge();
			
			int toYear = currentDate.getYear() - candidateFilter.getMinAge();
			
			conditionquery += " AND YEAR(c.birthDay) BETWEEN " + fromYear + " AND " + toYear;
		}
		
		if(!listIdIsEmpty(candidateFilter.getListSkills())) {
			
			String listIdSkillStr = candidateFilter.getListSkills().toString();
			
			// Remove first and last character
			listIdSkillStr = listIdSkillStr.substring(1, listIdSkillStr.length() - 1);

			conditionquery += " AND sc.skill.id IN (" + listIdSkillStr + ") ";
		}
		
		String query = fromQuery + joinQuery + conditionquery;
		
		listCandidate = entityManager.createQuery(query, Candidate.class)
				.getResultList();
		
		return listCandidate;
	}
	
	/**
	 * Filter candidate by content
	 */
	@Override
	public List<Candidate> filterCandidateByContent(String content, Integer page) {
		
		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT c FROM Candidate c " +
				"JOIN c.listEntryTest et " +
				"WHERE et.point >= :point AND " +
				"c.fullName LIKE '%' || :content || '%'", Candidate.class)
				.setParameter("point", EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter("content", content)
				.setMaxResults(PaginationInfoConst.MAX_RESULT)
				.setFirstResult(page * PaginationInfoConst.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
		
	}
	
	/**
	 * Count candidate filter by content
	 */
	@Override
	public Integer countCandidateByContent(String content) {
		
		Long totalRow = entityManager.createQuery(
				"SELECT COUNT(c) FROM Candidate c " +
				"JOIN c.listEntryTest et " +
				"WHERE et.point >= :point AND " +
				"c.fullName LIKE '%' || :content || '%'", Long.class)
				.setParameter("point", EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter("content", content)
				.getSingleResult();
				
		return totalRow.intValue();
		
	}

	/**
	 * Filter candidate by age
	 */
	@Override
	public List<Candidate> filterCandidateByAge(LocalDate fromYear, LocalDate toYear, Integer page) {

		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT c FROM Candidate c " 
				+ " JOIN c.listEntryTest et " 
				+ " WHERE et.point >= :point " 
				+ " AND c.birthDay BETWEEN :fromYear AND :toYear", Candidate.class)
				.setParameter("point", EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.setMaxResults(PaginationInfoConst.MAX_RESULT)
				.setFirstResult(page * PaginationInfoConst.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}
	
	/**
	 * Filter candidate by age
	 */
	@Override
	public List<Candidate> filterCandidateByAge(Integer fromYear, Integer toYear, Integer page) {

		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT c FROM Candidate c " 
				+ " JOIN c.listEntryTest et " 
				+ " WHERE et.point >= :point " 
				+ " AND YEAR(c.birthDay) BETWEEN :fromYear AND :toYear", Candidate.class)
				.setParameter("point", EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.setMaxResults(PaginationInfoConst.MAX_RESULT)
				.setFirstResult(page * PaginationInfoConst.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}

	/**
	 * Count candidate by age
	 */
	@Override
	public Integer countCandidateByAge(LocalDate fromYear, LocalDate toYear) {

		Long totalRow = entityManager.createQuery(
				"SELECT COUNT(c) FROM Candidate c " 
				+ " JOIN c.listEntryTest et " 
				+ " WHERE et.point >= :point " 
				+ " AND c.birthDay BETWEEN :fromYear AND :toYear", Long.class)
				.setParameter("point", EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.getSingleResult();
				
		return totalRow.intValue();
	}

	/**
	 * Filter candidate by skill and pass entry test
	 */
	@Override
	public List<Candidate> filterCandidateBySkillAndPassEntryTest(List<Integer> listId, Integer page) {
		
		List<Candidate> listCandidate = entityManager.createQuery(
			"SELECT DISTINCT c FROM Candidate c"
			+ " JOIN c.listEntryTest et " 
			+ " JOIN c.listSkillCandidate sc " 
			+ " WHERE et.point >= ?1 " 
			+ " AND sc.skill.id IN (?2)", Candidate.class)
		.setParameter(1, EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
		.setParameter(2, listId)
		.setMaxResults(PaginationInfoConst.MAX_RESULT)
		.setFirstResult(page * PaginationInfoConst.MAX_RESULT)
		.getResultList();
		
		return listCandidate;
	}

	/**
	 * Count candidate by skill and pass entry test
	 */
	@Override
	public Integer countCandidateBySkillAndPassEntryTest(List<Integer> listId) {
		
		Long totalRow = entityManager.createQuery(
			"SELECT COUNT(DISTINCT c) FROM Candidate c"
			+ " JOIN c.listEntryTest et " 
			+ " JOIN c.listSkillCandidate sc " 
			+ " WHERE et.point >= ?1 " 
			+ " AND sc.skill.id IN (?2)", Long.class)
		.setParameter(1, EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
		.setParameter(2, listId)
		.getSingleResult();
		
		return totalRow.intValue();
	}

	/**
	 * Filter candidate by age, skill, and pass entry test
	 */
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
				.setParameter(1, EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter(2, listId)
				.setParameter(3, fromYear)
				.setParameter(4, toYear)
				.setMaxResults(PaginationInfoConst.MAX_RESULT)
				.setFirstResult(page * PaginationInfoConst.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}

	/**
	 * Count candidate by age, skill and pass entry test
	 */
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
				.setParameter(1, EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter(2, listId)
				.setParameter(3, fromYear)
				.setParameter(4, toYear)
				.getSingleResult();
				
		return totalRow.intValue();
	}

	/**
	 * Get list interview by candidate id
	 */
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

	/**
	 * Filter candidate by content, skill, and pass entry test
	 */
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
				.setParameter(1, EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter(2, listId)
				.setParameter("content", content)
				.setMaxResults(PaginationInfoConst.MAX_RESULT)
				.setFirstResult(page * PaginationInfoConst.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}

	/**
	 * Count candidate by content, skill and pass entry test
	 */
	@Override
	public Integer countCandidateByContentAndSkillAndPassEntryTest(String content, List<Integer> listId) {
		
		Long totalRow = entityManager.createQuery(
				"SELECT COUNT (DISTINCT c) FROM Candidate c "
				+ " JOIN c.listEntryTest et " 
				+ " JOIN c.listSkillCandidate sc "
				+ " WHERE et.point >= ?1 " 
				+ " AND sc.skill.id IN (?2) " 
				+ " AND c.fullName LIKE '%' || :content || '%'", Long.class)
				.setParameter(1, EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter(2, listId)
				.setParameter("content", content)
				.getSingleResult();
				
		return totalRow.intValue();
	}

	/**
	 * Filter candidate by content, age and pass entry test
	 */
	@Override
	public List<Candidate> filterCandidateByContentAndAgeAndPassEntryTest(String content, LocalDate fromYear, 
		LocalDate toYear, Integer page) {

		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT c FROM Candidate c "
				+ " JOIN c.listEntryTest et "
				+ " WHERE et.point >= :point AND "
				+ " c.birthDay BETWEEN :fromYear AND :toYear AND "
				+ " c.fullName LIKE '%' || :content || '%'", Candidate.class)
				.setParameter("point", EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.setParameter("content", content)
				.setMaxResults(PaginationInfoConst.MAX_RESULT)
				.setFirstResult(page * PaginationInfoConst.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}

	/**
	 * Count candidate by content, age, and pass entry test
	 */
	@Override
	public Integer countCandidateByContentAndAgeAndPassEntryTest(String content, LocalDate fromYear, LocalDate toYear) {

		Long listCandidate = entityManager.createQuery(
				"SELECT COUNT (DISTINCT c) FROM Candidate c "
				+ " JOIN c.listEntryTest et "
				+ " WHERE et.point >= :point AND "
				+ " c.birthDay BETWEEN :fromYear AND :toYear AND "
				+ " c.fullName LIKE '%' || :content || '%'", Long.class)
				.setParameter("point", EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.setParameter("content", content)
				.getSingleResult();
				
		return listCandidate.intValue();
	}

	/**
	 * Filter candidate by content, age, skill and pass entry test
	 */
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
				.setParameter("point", EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.setParameter("listId", listId)
				.setMaxResults(PaginationInfoConst.MAX_RESULT)
				.setFirstResult(page * PaginationInfoConst.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}

	/**
	 * Count candidate by content, age, skill and pass entry test
	 */
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
				.setParameter("point", EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setParameter("fromYear", fromYear)
				.setParameter("toYear", toYear)
				.setParameter("listId", listId)
				.getSingleResult();
				
		return totalRow.intValue();
	}

	/**
	 * Filter candidate pass entry test
	 */
	@Override
	public List<Candidate> filterCandidatePassEntryTest(Integer page) {

		List<Candidate> listCandidate = entityManager.createQuery(
				"SELECT DISTINCT c FROM Candidate c "
				+ " JOIN c.listEntryTest et "
				+ " WHERE "
				+ " et.point >= :point", Candidate.class)
				.setParameter("point", EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.setMaxResults(PaginationInfoConst.MAX_RESULT)
				.setFirstResult(page * PaginationInfoConst.MAX_RESULT)
				.getResultList();
				
		return listCandidate;
	}

	/**
	 * Count candidate pass entry test
	 */
	@Override
	public Integer countCandidatePassEntryTest() {

		Long totalRow = entityManager.createQuery(
				"SELECT COUNT(DISTINCT c) FROM Candidate c "
				+ " JOIN c.listEntryTest et "
				+ " WHERE "
				+ " et.point >= :point", Long.class)
				.setParameter("point", EntryTestInfoConst.POINT_PASS_ENTRY_TEST)
				.getSingleResult();
				
		return totalRow.intValue();
	}
	
	/**
	 * Check age is empty
	 * @param minAge
	 * @param maxAge
	 * @return
	 */
	private boolean ageIsEmpty(Integer minAge, Integer maxAge) {
		
		if (minAge == null || maxAge == null) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Check content is empty
	 * @param content
	 * @return
	 */
	private boolean contentIsEmpty(String content) {
		
		if( content == null) {
			return true;
		} else if (content.isEmpty() || content.isBlank()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Check list contain skills selected is empty
	 * @param listId
	 * @return
	 */
	private boolean listIdIsEmpty(List<Integer> listId) {
		
		if(listId == null) {
			return true;
		} else if (listId.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
