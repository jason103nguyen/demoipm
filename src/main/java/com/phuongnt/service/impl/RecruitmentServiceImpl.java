package com.phuongnt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phuongnt.dao.RecruitmentDao;
import com.phuongnt.dto.RecruitmentDto;
import com.phuongnt.entities.Recruitment;
import com.phuongnt.service.RecruitmentService;

@Service
@Transactional
public class RecruitmentServiceImpl implements RecruitmentService {

	@Autowired
	private RecruitmentDao recruitmentDao;
	
	@Override
	public void create(RecruitmentDto recruitmentDto) {

		Recruitment recruitment = new Recruitment(recruitmentDto);
		recruitmentDao.save(recruitment);
	}

	@Override
	public RecruitmentDto readById(int id) throws Exception {

		Optional<Recruitment> recruitment = recruitmentDao.findById(id);
		RecruitmentDto recruitmentDto = null;
		if (recruitment.isEmpty()) {
			throw new Exception("The id doesn't exists");
		} else {
			recruitmentDto = new RecruitmentDto(recruitment.get());
		}
		return recruitmentDto;
	}

	@Override
	public List<RecruitmentDto> readAll() throws Exception {

		List<Recruitment> listRecruitment = (List<Recruitment>) recruitmentDao.findAll();
		List<RecruitmentDto> listRecruitmentDto = new ArrayList<RecruitmentDto>();
		
		if (listRecruitment.isEmpty()) {
			
			throw new Exception("This list is empty");
		} else {
			
			for (Recruitment recruitment : listRecruitment) {
				RecruitmentDto recruitmentDto = new RecruitmentDto(recruitment);
				listRecruitmentDto.add(recruitmentDto);
			}
		}
		
		return listRecruitmentDto;
	}

	@Override
	public void update(RecruitmentDto recruitmentDto) {

		Recruitment recruitment = new Recruitment(recruitmentDto);
		recruitmentDao.save(recruitment);
	}

	@Override
	public void deleteById(int id) {

		if (recruitmentDao.existsById(id)) {
			recruitmentDao.deleteById(id);
		}
	}

}
