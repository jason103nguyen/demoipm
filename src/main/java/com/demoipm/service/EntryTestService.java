package com.demoipm.service;

import com.demoipm.dto.EntryTestRequest;
import com.demoipm.dto.QuestionRequest;
import com.demoipm.entities.EntryTest;
import com.demoipm.entities.Question;

import java.util.List;

public interface EntryTestService {

	EntryTest create(EntryTestRequest entryTestRequest);

	EntryTest update(EntryTestRequest entryTestRequest);

	List<Question> getRandBySkillName(String skill,Integer numberofQuestion);

	List<Question> getQuestionById(Integer id);
	
	void deleteById(int id);
}
