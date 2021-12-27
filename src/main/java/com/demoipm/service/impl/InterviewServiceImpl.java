package com.demoipm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.demoipm.dto.InterviewRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.InterviewDao;
import com.demoipm.dto.InterviewDto;
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
		return Interview.builder()
				.location(interviewRequest.getLocation())
/*
				.candidate(interviewRequest.getCandidate())
*/
				.timeInterview(interviewRequest.getTimeInterview())
				.date(interviewRequest.getDate())
/*
				.nameInterviewer(interviewDao.findByCandidateId(interviewRequest.getCandidate().getId()).get().getNameInterviewer())
*/
				.contactForm(interviewRequest.getContactForm())
				.nameInterviewer(interviewRequest.getNameInterview())
				.build();
	}

	@Override
	public void deleteById(int id) {

		if (interviewDao.existsById(id)) {
			interviewDao.deleteById(id);
		}
	}

}
