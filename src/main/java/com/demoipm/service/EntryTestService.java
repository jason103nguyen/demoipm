package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.EntryTestDto;

public interface EntryTestService {

	void create(EntryTestDto entryTestDto);
	
	EntryTestDto readById(int id) throws Exception;
	
	List<EntryTestDto> readAll() throws Exception ;
	
	void update(EntryTestDto entryTestDto);
	
	void deleteById(int id);
}
