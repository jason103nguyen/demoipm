package com.phuongnt.dao;

import org.springframework.data.repository.CrudRepository;

import com.phuongnt.entities.Job;

public interface JobDao extends CrudRepository<Job, Integer>{

}
