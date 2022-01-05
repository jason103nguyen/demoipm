package com.demoipm.service;

import com.demoipm.dto.EntryTestRequest;
import com.demoipm.entities.EntryTest;

public interface EntryTestService {

	EntryTest create(EntryTestRequest entryTestRequest);

	EntryTest update(EntryTestRequest entryTestRequest);
	
	void deleteById(int id);
}
