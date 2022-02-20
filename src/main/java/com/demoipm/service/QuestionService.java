package com.demoipm.service;

import com.demoipm.dto.QuestionRequest;
import com.demoipm.entities.Question;

public interface QuestionService {

	Question create(QuestionRequest questionRequest);

	Question update(QuestionRequest questionRequest);

	void deleteById(int id);
}
