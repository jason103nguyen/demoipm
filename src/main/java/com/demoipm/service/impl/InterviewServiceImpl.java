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
	public void create(InterviewRequest interviewRequest) {

		if(interviewDao.findById(interviewRequest.getId()).isPresent()){
			log.error(String.format("User name: [%s] is existing"));
		}
		try {
			interviewDao.save(parseInterviewRequestToEntities(interviewRequest));
		} catch (Exception ex){
			log.error("Create interview missing error: " + ex.getMessage());
		}

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
				.nameInterviewer(interviewRequest.getNameInterview())
				.build();
	}

/*
	@Override
	public InterviewDto readById(int id) throws Exception {

		Optional<Interview> interview = interviewDao.findById(id);
		InterviewDto interviewDto = null;
		if (interview.isEmpty()) {
			throw new Exception("The id doesn't exists");
		} else {
			interviewDto = new InterviewDto(interview.get());
		}
		return interviewDto;
	}

	@Override
	public List<InterviewDto> readAll() throws Exception {

		List<Interview> listInterview = (List<Interview>) interviewDao.findAll();
		List<InterviewDto> listInterviewDto = new ArrayList<InterviewDto>();
		
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
*/

	@Override
	public void deleteById(int id) {

		if (interviewDao.existsById(id)) {
			interviewDao.deleteById(id);
		}
	}

}
