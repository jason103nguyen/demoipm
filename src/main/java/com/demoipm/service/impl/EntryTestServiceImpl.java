package com.demoipm.service.impl;

import javax.transaction.Transactional;

import com.demoipm.dao.QuestionDao;
import com.demoipm.dto.EntryTestRequest;
import com.demoipm.dto.QuestionRequest;
import com.demoipm.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.EntryTestDao;
import com.demoipm.entities.EntryTest;
import com.demoipm.service.EntryTestService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EntryTestServiceImpl implements EntryTestService {

	@Autowired
	private EntryTestDao entryTestDao;

	@Autowired
	private QuestionDao questionDao;
	
	@Override
	public EntryTest create(EntryTestRequest entryTestRequest) {
		try {
			int min = 1;
			int max =10;
			double point_double = Math.random() * (max - min + 1) + min;
			int random_point = (int)(Math.random() * (max - min + 1) + min);
			String kq ;
			if(random_point >= 5){
				kq = "Đậu";
			} else {
				kq = "Rớt";
			}
			EntryTest entryTest = parseEntryTestRequestToEntities(entryTestRequest);
			List<Question> listQuestion = new ArrayList<>();
			for(Integer questionId : entryTestRequest.getQuestionIds()){
				Question question = new Question();
				question.setId(questionId);
				listQuestion.add(question);
			}
			entryTest.setQuestions(listQuestion);
			entryTest.setPoint(random_point);
			entryTest.setResult(kq);
			return entryTestDao.save(entryTest);

		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Question> getRandBySkillName(String skill,Integer numberofQuestion ){
		 List<Question> question = questionDao.getRandomQuestionBySkill(skill);
		 Collections.shuffle(question);
		 List<Question> randomQuestion = new ArrayList<>();
		 if(question.size() < numberofQuestion){
			 randomQuestion = question;
		 } else {
			 randomQuestion = question.subList(0,numberofQuestion);
		 }
		System.out.println(question);
		 return randomQuestion;
	}

	@Override
	public List<Question> getQuestionById(Integer id) {
		List<Question> listQuestion = questionDao.findQuestionById(id);
		return listQuestion;
	}

	@Override
	public EntryTest update(EntryTestRequest entryTestRequest) {
		EntryTest entryTest = parseEntryTestRequestToEntities(entryTestRequest);
		return entryTestDao.save(entryTest);
	}

	private EntryTest parseEntryTestRequestToEntities(EntryTestRequest entryTestRequest){
		EntryTest entryTest = new EntryTest();
		entryTest.setBeginTest(entryTestRequest.getBeginTest());
		entryTest.setFinishTest(entryTestRequest.getFinishTest());
		entryTest.setTimeEntryTest(entryTestRequest.getTimeEntryTest());
		entryTest.setNumberofQuestion(entryTestRequest.getNumberofQuestion());
		entryTest.setNameTest(entryTestRequest.getNameTest());
		return entryTest;
	}

	private List<Question> parseSkillInQuestionToEntity(List<QuestionRequest> questionRequest){
		return questionRequest.stream()
				.map(this::parseQuestionToEntity)
				.collect(Collectors.toList());
	}

	private Question parseQuestionToEntity(QuestionRequest questionRequest){
		Question question = new Question();
		return question;
	}

	@Override
	public void deleteById(int id) {

		if (entryTestDao.existsById(id)) {
			entryTestDao.deleteById(id);
		}
	}

}
