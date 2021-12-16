package com.phuongnt.service;

import java.util.List;

import com.phuongnt.dto.CareerDto;

public interface CareerService {

	void create(CareerDto careerDto);
	
	CareerDto readById(int id) throws Exception;
	
	List<CareerDto> readAll() throws Exception ;
	
	void update(CareerDto careerDto);
	
	void deleteById(int id);
}
