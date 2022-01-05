package com.demoipm.service;

import com.demoipm.dto.InterviewRequest;
import com.demoipm.dto.QuestionEntryTestRequest;
import com.demoipm.entities.Interview;
import com.demoipm.entities.QuestionEntryTest;

public interface QuestionService {

	QuestionEntryTest create(QuestionEntryTestRequest questionEntryTestRequest);

	QuestionEntryTest update(QuestionEntryTestRequest questionEntryTestRequest);
	
	void deleteById(int id);
}
