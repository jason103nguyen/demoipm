package com.demoipm.service.impl;


import javax.transaction.Transactional;
import com.demoipm.dto.InterviewRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demoipm.dao.InterviewDao;
import com.demoipm.entities.Interview;
import com.demoipm.service.InterviewService;

@Service
@Slf4j
@Transactional
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewDao interviewDao;
	
	@Override
	public Interview create(InterviewRequest interviewRequest) {

			Interview interview = parseInterviewRequestToEntities(interviewRequest);
			return interviewDao.save(interview);

	}

	private Interview parseInterviewRequestToEntities(InterviewRequest interviewRequest){
		Interview interview = new Interview();
		interview.setLocation(interviewRequest.getLocation());
		interview.setTimeInterview(interviewRequest.getTimeInterview());
		interview.setNameInterviewer(interviewRequest.getNameInterviewer());
		interview.setDate(interviewRequest.getDate());
		interview.setContactForm(interviewRequest.getContactForm());
		return interview;
	}

	@Override
	public void deleteById(int id) {

		if (interviewDao.existsById(id)) {
			interviewDao.deleteById(id);
		}
	}

}
