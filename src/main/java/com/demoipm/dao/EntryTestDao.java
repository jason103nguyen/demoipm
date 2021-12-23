package com.demoipm.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demoipm.Constant.EntryTestInfo;
import com.demoipm.entities.EntryTest;

public interface EntryTestDao extends CrudRepository<EntryTest, Integer>{

	@Query("SELECT e FROM EntryTest e WHERE e.point >= " + EntryTestInfo.POINT_PASS_ENTRY_TEST)
	List<EntryTest> readPassEntryTest();

	@Query("SELECT e FROM EntryTest e WHERE e.point >= " 
			+ EntryTestInfo.POINT_PASS_ENTRY_TEST + " AND e.candidate.birthDay BETWEEN :fromYear AND :toYear")
	List<EntryTest> readPassEntryTestfromYearToYearBirthDay(LocalDate fromYear, LocalDate toYear);

}
