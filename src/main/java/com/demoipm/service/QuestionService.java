package com.demoipm.service;

import com.demoipm.dto.QuestionEntryTestRequest;
import com.demoipm.entities.Question;

public interface QuestionService {

	Question create(QuestionEntryTestRequest questionEntryTestRequest);

	Question update(QuestionEntryTestRequest questionEntryTestRequest);

	void deleteById(int id);
}
