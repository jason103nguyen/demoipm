package com.demoipm.dao;

import org.springframework.data.repository.CrudRepository;

import com.demoipm.entities.Job;

public interface JobDao extends CrudRepository<Job, Integer>{

}
