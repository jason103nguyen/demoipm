package com.demoipm.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.demoipm.entities.EntryTest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryTestDao extends CrudRepository<EntryTest, Integer>{



}
