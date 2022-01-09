package com.demoipm.service;

import com.demoipm.dto.InterviewRequest;
import com.demoipm.dto.QuestionEntryTestRequest;
import com.demoipm.entities.Interview;
import com.demoipm.entities.QuestionEntryTest;
import com.demoipm.entities.Skill;

import java.util.Optional;

public interface QuestionService {

	QuestionEntryTest create(QuestionEntryTestRequest questionEntryTestRequest);

	QuestionEntryTest update(QuestionEntryTestRequest questionEntryTestRequest);

	Skill findBySkill(String skill);

	void deleteById(int id);
}
