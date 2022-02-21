package com.demoipm.service.impl;

import com.demoipm.dao.InterviewDao;
import com.demoipm.dto.InterviewDto;
import com.demoipm.dto.InterviewRequest;
import com.demoipm.entities.Interview;
import com.demoipm.service.InterviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@Override
	public InterviewDto readById(int id) throws Exception {

		Optional<Interview> interview = interviewDao.findById(id);
		InterviewDto interviewDto;
		if (interview.isEmpty()) {
			throw new Exception("The id doesn't exists");
		} else {
			interviewDto = new InterviewDto(interview.get());
		}
		return interviewDto;
	}

	@Override
	public List<InterviewDto> readAll() throws Exception {

		List<Interview> listInterview = interviewDao.findAll();
		List<InterviewDto> listInterviewDto = new ArrayList<>();

		if (listInterview.isEmpty()) {
			throw new Exception("This list is empty");
		} else {
			for (Interview interview : listInterview) {
				InterviewDto interviewDto = new InterviewDto(interview);
				listInterviewDto.add(interviewDto);
			}
		}

		return listInterviewDto;
	}

	@Override
	public void update(InterviewDto interviewDto) {

		Interview interview = new Interview(interviewDto);
		interviewDao.save(interview);
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
