package com.demoipm.service.impl;

import com.demoipm.dao.QuestionDao;
import com.demoipm.dto.EntryTestRequest;
import com.demoipm.dto.QuestionEntryTestRequest;
import com.demoipm.entities.EntryTest;
import com.demoipm.entities.QuestionEntryTest;
import com.demoipm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class QuestionEntryTestServiceImpl implements QuestionService{

    @Autowired
    private QuestionDao questionDao;


    @Override
    public QuestionEntryTest create(QuestionEntryTestRequest questionEntryTestRequest) {
        QuestionEntryTest questionEntryTest = parseEntryQuestionTestRequestToEntities(questionEntryTestRequest);
        return questionDao.save(questionEntryTest);
    }

    @Override
    public QuestionEntryTest update(QuestionEntryTestRequest questionEntryTestRequest) {
        QuestionEntryTest questionEntryTest = parseEntryQuestionTestRequestToEntities(questionEntryTestRequest);
        return questionDao.save(questionEntryTest);
    }

    private QuestionEntryTest parseEntryQuestionTestRequestToEntities(QuestionEntryTestRequest questionEntryTestRequest){
        QuestionEntryTest questionEntryTest = new QuestionEntryTest();
        questionEntryTest.setAnswer(questionEntryTestRequest.getAnswer());
        questionEntryTest.setContent(questionEntryTestRequest.getContent());
        questionEntryTest.setOption1(questionEntryTestRequest.getOption1());
        questionEntryTest.setOption2(questionEntryTestRequest.getOption2());
        questionEntryTest.setOption3(questionEntryTestRequest.getOption3());
        questionEntryTest.setOption4(questionEntryTestRequest.getOption4());
        questionEntryTest.setLevel(questionEntryTestRequest.getLevel());
        return questionEntryTest;
    }

    @Override
    public void deleteById(int id) {
        if (questionDao.existsById(id)) {
            questionDao.deleteById(id);
        }

    }
}
