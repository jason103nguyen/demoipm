package com.demoipm.dao.impl;

import com.demoipm.consts.EntryTestInfoConst;
import com.demoipm.consts.PaginationInfoConst;
import com.demoipm.dao.CandidateDaoCustom;
import com.demoipm.dto.candidatefilter.CandidateFilter;
import com.demoipm.entities.Candidate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

public class CandidateDaoCustomImpl implements CandidateDaoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Count row
     */
    @Override
    public Integer countRow(CandidateFilter candidateFilter) {

        String fromQuery = "SELECT COUNT(DISTINCT c) FROM Candidate c";
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

        if (!listIdIsEmpty(candidateFilter.getListSkills())) {

            String listIdSkillStr = candidateFilter.getListSkills().toString();

            // Remove first and last character
            listIdSkillStr = listIdSkillStr.substring(1, listIdSkillStr.length() - 1);

            conditionquery += " AND sc.skill.id IN (" + listIdSkillStr + ") ";
        }

        String query = fromQuery + joinQuery + conditionquery;

        Long totalRow = entityManager.createQuery(query, Long.class)
                .getSingleResult();

        return totalRow.intValue();
    }

    /**
     * Filter candidate
     */
    public List<Candidate> filter(CandidateFilter candidateFilter, Integer page) {

        List<Candidate> listCandidate;

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

        if (!listIdIsEmpty(candidateFilter.getListSkills())) {

            String listIdSkillStr = candidateFilter.getListSkills().toString();

            // Remove first and last character
            listIdSkillStr = listIdSkillStr.substring(1, listIdSkillStr.length() - 1);

            conditionquery += " AND sc.skill.id IN (" + listIdSkillStr + ") ";
        }

        String query = fromQuery + joinQuery + conditionquery;

        listCandidate = entityManager.createQuery(query, Candidate.class)
                .setFirstResult(page * PaginationInfoConst.MAX_RESULT)
                .setMaxResults(PaginationInfoConst.MAX_RESULT)
                .getResultList();

        return listCandidate;
    }

    /**
     * Check age is empty
     *
     * @param minAge
     * @param maxAge
     * @return
     */
    private boolean ageIsEmpty(Integer minAge, Integer maxAge) {

		return minAge == null || maxAge == null;

    }

    /**
     * Check content is empty
     *
     * @param content
     * @return
     */
    private boolean contentIsEmpty(String content) {

        if (content == null) {
            return true;
        } else  {
        	return content.isEmpty();
		}
    }

    /**
     * Check list contain skills selected is empty
     *
     * @param listId
     * @return
     */
    private boolean listIdIsEmpty(List<Integer> listId) {

        if (listId == null) {
            return true;
        } else {
            return listId.isEmpty();
        }
    }

}
