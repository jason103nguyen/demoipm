package com.demoipm.dao;

import org.springframework.data.repository.CrudRepository;

import com.demoipm.entities.EntryTest;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryTestDao extends CrudRepository<EntryTest, Integer>{

}
