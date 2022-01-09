package com.demoipm.service.impl;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.demoipm.consts.MessageConst;
import com.demoipm.dao.RecruitmentSkillDao;
import com.demoipm.dto.recruitmentmanage.RecruitmentCreateRequestDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentDetailDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentListPageResponseDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentResponseDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentSaveResponseDto;
import com.demoipm.entities.Career;
import com.demoipm.entities.Job;
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

	@Autowired
	private RecruitmentSkillDao recruitmentSkillDao;

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

	@Override
	public RecruitmentDetailDto getRecruimentDetailById(Integer id) {
		LOGGER.info("Start getRecruimentDetailById with id {}", id);
		RecruitmentDetailDto responseDTO = new RecruitmentDetailDto();
		try {
			Recruitment recruitment = recruitmentDao.getById(id);
			if (recruitment != null) {
				responseDTO.setCareer(recruitment.getCareer().getName());
				responseDTO.setJob(recruitment.getJob().getName());
				responseDTO.setQuantity(recruitment.getNumber());
				responseDTO.setMinSalary(recruitment.getMinSalary());
				responseDTO.setMaxSalary(recruitment.getMaxSalary());
				responseDTO.setStartDate(recruitment.getStartRecruitment());
				responseDTO.setEndDate(recruitment.getEndRecruitment());
				List<String> skills = recruitment.getListRecruitmentSkill()
						.stream()
						.map(RecruitmentSkill::getSkill)
						.map(Skill::getName)
						.collect(Collectors.toList());
				responseDTO.setSkills(skills);
			} else {
				LOGGER.error("Recruitment id not existed");
				responseDTO.setError(true);
				responseDTO.setMessage(MessageConst.RESOURCE_NOT_FOUND);
			}
		} catch (Throwable t) {
			LOGGER.error("Has error when getRecruimentDetailById", t);
			responseDTO.setError(true);
			responseDTO.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
		}
		return responseDTO;
	}

	@Override
	public RecruitmentSaveResponseDto createRecruitment(RecruitmentCreateRequestDto requestDto) {
		LOGGER.info("Start saveRecruitment with requestDto {}", requestDto);
		RecruitmentSaveResponseDto responseDTO = new RecruitmentSaveResponseDto();
		try {
			Recruitment recruitment = new Recruitment();
			// Mapping request dto to entity
			recruitment.setNumber(requestDto.getQuantity());
			recruitment.setMinSalary(requestDto.getMinSalary());
			recruitment.setMaxSalary(requestDto.getMaxSalary());
			recruitment.setStartRecruitment(Date.from(requestDto.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			recruitment.setEndRecruitment(Date.from(requestDto.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

			// Mapping career, job relationship
			Career career = new Career();
			career.setId(requestDto.getCareerId());
			recruitment.setCareer(career);

			Job job = new Job();
			job.setId(requestDto.getJobId());
			recruitment.setJob(job);

			recruitmentDao.save(recruitment);

			// Mapping skill relationship
			List<RecruitmentSkill> recruitmentSkills = requestDto.getSkillIds().stream().map(skillId -> {
				RecruitmentSkill recruitmentSkill = new RecruitmentSkill();
				Skill skill = new Skill();
				skill.setId(skillId);
				recruitmentSkill.setSkill(skill);
				recruitmentSkill.setRecruitment(recruitment);
				return recruitmentSkill;
			}).collect(Collectors.toList());
			recruitmentSkillDao.saveAll(recruitmentSkills);

		} catch (Throwable t) {
			LOGGER.error("Has error when getRecruimentDetailById", t);
			responseDTO.setError(true);
			responseDTO.setMessage(MessageConst.INTERNAL_SERVER_ERROR);
		}
		return responseDTO;
	}

}
