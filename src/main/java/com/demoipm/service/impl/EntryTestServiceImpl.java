package com.demoipm.service.impl;

import javax.transaction.Transactional;

import com.demoipm.dao.QuestionDao;
import com.demoipm.dto.EntryTestRequest;
import com.demoipm.dto.QuestionEntryTestRequest;
import com.demoipm.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.EntryTestDao;
import com.demoipm.entities.EntryTest;
import com.demoipm.service.EntryTestService;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class EntryTestServiceImpl implements EntryTestService {

	@Autowired
	private EntryTestDao entryTestDao;

	@Autowired
	private QuestionDao questionDao;
	
	@Override
	public EntryTest create(EntryTestRequest entryTestRequest) {
		int min = 1;
		int max =10;
		double point_double = Math.random() * (max - min + 1) + min;
		int random_point = (int)(Math.random() * (max - min + 1) + min);
		EntryTest entryTest = parseEntryTestRequestToEntities(entryTestRequest);
		entryTest.setPoint(random_point);
		return entryTestDao.save(entryTest);
	}

	public List<Question> getRandBySkillName(String skillName){
		 List<Question> question = questionDao.getRandomQuestionBySkill(skillName);
		 Collections.shuffle(question);
		 List<Question> randomQuestion = question.subList(0,4);
		 return randomQuestion;
	}

	@Override
	public EntryTest update(EntryTestRequest entryTestRequest) {
		EntryTest entryTest = parseEntryTestRequestToEntities(entryTestRequest);
		return entryTestDao.save(entryTest);
	}

	private EntryTest parseEntryTestRequestToEntities(EntryTestRequest entryTestRequest){
		EntryTest entryTest = new EntryTest();
		entryTest.setTimeEntryTest(entryTestRequest.getTimeEntryTest());
		entryTest.setNumberofQuestion(entryTestRequest.getNumberofQuestion());
		entryTest.setNameTest(entryTestRequest.getNameTest());
		entryTest.setEntryTestQuestions(entryTestRequest.getEntryTestQuestions());
		entryTest.setListJobSkill(entryTestRequest.getListJobSkill());
		return entryTest;
	}

	@Override
	public void deleteById(int id) {

		if (entryTestDao.existsById(id)) {
			entryTestDao.deleteById(id);
		}
	}

}
