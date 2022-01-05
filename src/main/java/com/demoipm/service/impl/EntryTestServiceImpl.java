package com.demoipm.service.impl;

import javax.transaction.Transactional;

import com.demoipm.dto.EntryTestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.EntryTestDao;
import com.demoipm.entities.EntryTest;
import com.demoipm.service.EntryTestService;

@Service
@Transactional
public class EntryTestServiceImpl implements EntryTestService {

	@Autowired
	private EntryTestDao entryTestDao;
	
	@Override
	public EntryTest create(EntryTestRequest entryTestRequest) {
		EntryTest entryTest = parseEntryTestRequestToEntities(entryTestRequest);
		return entryTestDao.save(entryTest);
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
		entryTest.setPoint(entryTestRequest.getPoint());
		entryTest.setQuestionEntryTest(entryTestRequest.getQuestionEntryTest());
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
