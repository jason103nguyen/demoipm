package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.CareerDto;
import com.demoipm.dto.recruitmentmanage.CareerSelectionDto;

public interface CareerService {

	void create(CareerDto careerDto);
	
	CareerDto readById(int id) throws Exception;
	
	List<CareerDto> readAll() throws Exception ;
	
	void update(CareerDto careerDto);
	
	void deleteById(int id);

	/**
	 * Get all career info
	 * @return
	 */
	List<CareerSelectionDto> getAllCareer();
}
