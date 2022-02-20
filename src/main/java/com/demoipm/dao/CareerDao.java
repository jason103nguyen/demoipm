package com.demoipm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demoipm.entities.Career;

public interface CareerDao extends JpaRepository<Career, Integer>{

}
