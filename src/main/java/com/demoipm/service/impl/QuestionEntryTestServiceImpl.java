package com.demoipm.service.impl;

import com.demoipm.dao.QuestionDao;
import com.demoipm.dao.SkillDao;
import com.demoipm.dto.QuestionRequest;
import com.demoipm.entities.Question;
import com.demoipm.entities.Skill;
import com.demoipm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class QuestionEntryTestServiceImpl implements QuestionService{

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private SkillDao skillDao;

    @Override
    public Question create(QuestionRequest questionRequest) {
        Question questionEntryTest = parseEntryQuestionTestRequestToEntities(questionRequest);
        Skill skill = getSkillByName(questionRequest.getSkill());
        questionEntryTest.setSkill(skill);
        return questionDao.save(questionEntryTest);
    }

    @Override
    public Question update(QuestionRequest questionRequest) {
        Question questionEntryTest = parseEntryQuestionTestRequestToEntities(questionRequest);
        return questionDao.save(questionEntryTest);
    }

    private Skill getSkillByName(String name){
        Skill skill = skillDao.findByName(name).orElse(null);
        return skill;
    }


    private Question parseEntryQuestionTestRequestToEntities(QuestionRequest questionRequest){
        Question questionEntryTest = new Question();
        questionEntryTest.setAnswer1(questionRequest.getAnswer1());
        questionEntryTest.setAnswer2(questionRequest.getAnswer2());
        questionEntryTest.setAnswer3(questionRequest.getAnswer3());
        questionEntryTest.setAnswer4(questionRequest.getAnswer4());
        questionEntryTest.setContent(questionRequest.getContent());
        questionEntryTest.setOption1(questionRequest.getOption1());
        questionEntryTest.setOption2(questionRequest.getOption2());
        questionEntryTest.setOption3(questionRequest.getOption3());
        questionEntryTest.setOption4(questionRequest.getOption4());
        return questionEntryTest;
    }

    @Override
    public void deleteById(int id) {
        if (questionDao.existsById(id)) {
            questionDao.deleteById(id);
        }

    }
}
