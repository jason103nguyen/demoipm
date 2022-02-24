package com.demoipm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoipm.entities.Interview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewDao extends JpaRepository<Interview, Integer> {

    // @Query(value = "SELECT \n" +
    //         " t.*\n" +
    //         " FROM\n" +
    //         " interview t\n" +
    //         " INNER JOIN\n" +
    //         " candidate c ON c.id = t.candidate_id\n" +
    //         "WHERE\n"+
    //         "t.candidate_id = :candidateId\n",nativeQuery = true)
    @Query(value = "select i from Interview i where i.candidate.id = :candidateId")
    Optional<List<Interview>> findByCandidateId(@Param("candidateId") Integer candidateId);

}