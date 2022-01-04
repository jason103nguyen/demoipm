package com.demoipm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.demoipm.consts.MessageConst;
import com.demoipm.dto.recruitmentmanage.RecruitmentListPageResponseDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demoipm.dao.RecruitmentDao;
import com.demoipm.dto.RecruitmentDto;
import com.demoipm.dto.SkillDto;
import com.demoipm.entities.Recruitment;
import com.demoipm.entities.RecruitmentSkill;
import com.demoipm.entities.Skill;
import com.demoipm.service.RecruitmentService;

@Service
@Transactional
public class RecruitmentServiceImpl implements RecruitmentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecruitmentService.class);

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
		if (!recruitment.isPresent()) {
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
	public List<SkillDto> readAllSkillByRecruitment(int id) throws Exception {

		Optional<Recruitment> recruitment = recruitmentDao.findById(id);
		List<SkillDto> listSkill = null;
		if (!recruitment.isPresent()) {
			throw new Exception("The id doesn't exists");
		} else {
			List<RecruitmentSkill> listRecruitmentSkill = recruitment.get().getListRecruitmentSkill();
			listSkill = new ArrayList<SkillDto>();
			for(RecruitmentSkill recruitmentSkill : listRecruitmentSkill) {
				Skill skill = recruitmentSkill.getSkill();
				SkillDto skillDto = new SkillDto(skill);
				listSkill.add(skillDto);
			}
			
		}
		return listSkill;
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

	@Override
	public RecruitmentListPageResponseDto readByCondition(Integer pageNo, Integer entriesNo) {
		LOGGER.info("Start readByCondition with pageNo {}, entriesNo {}", pageNo, entriesNo);
		RecruitmentListPageResponseDto responseDto = new RecruitmentListPageResponseDto();
		try {
			// Get data from database with pagination and search word
			Pageable pageable = PageRequest.of(pageNo - 1, entriesNo, Sort.Direction.DESC, "createdDate");
			Page<Recruitment> recruitmentPage = recruitmentDao.findAll(pageable);

			// Prepare response dto
			responseDto.setCurrentPage(pageNo);
			responseDto.setEntriesNo(entriesNo);
			responseDto.setTotalPage(recruitmentPage.getTotalPages());
			responseDto.setTotalEntries(recruitmentPage.getTotalElements());
			List<RecruitmentResponseDto> recruitmentList = recruitmentPage.getContent().stream().map(recruitment -> {
				RecruitmentResponseDto recruitmentResponseDto = new RecruitmentResponseDto();
				recruitmentResponseDto.setId(recruitment.getId());
				recruitmentResponseDto.setCareer(recruitment.getCareer().getName());
				recruitmentResponseDto.setJob(recruitment.getJob().getName());
				recruitmentResponseDto.setQuantity(recruitment.getNumber());
				return recruitmentResponseDto;
			}).collect(Collectors.toList());
			responseDto.setRecruitmentList(recruitmentList);
		} catch (Throwable t) {
			LOGGER.error("Has error when readByCondition", t);
			responseDto.setError(true);
			responseDto.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
		} finally {
			LOGGER.info("End readByCondition with pageNo {}, entriesNo {}", pageNo, entriesNo);
			return responseDto;
		}
	}

}
