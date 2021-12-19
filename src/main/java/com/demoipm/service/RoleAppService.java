package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.RoleAppDto;

public interface RoleAppService {

	void create(RoleAppDto roleAppDto);
	
	RoleAppDto readById(int id) throws Exception;
	
	List<RoleAppDto> readAll() throws Exception ;
	
	void update(RoleAppDto roleAppDto);
	
	void deleteById(int id);
}
