package com.demoipm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demoipm.Constant.EntryTestInfo;
import com.demoipm.entities.EntryTest;

public interface EntryTestDao extends CrudRepository<EntryTest, Integer>{

	@Query("SELECT e FROM EntryTest e WHERE e.point >= " + EntryTestInfo.POINT_PASS_ENTRY_TEST)
	List<EntryTest> readPassEntryTest();

}
