package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.RecruitmentDto;
import com.demoipm.dto.SkillDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentDetailDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentListPageResponseDto;

public interface RecruitmentService {

	void create(RecruitmentDto recruitmentDto);
	
	RecruitmentDto readById(int id) throws Exception;
	
	List<RecruitmentDto> readAll() throws Exception ;
	
	void update(RecruitmentDto recruitmentDto);
	
	void deleteById(int id);
	
	public List<SkillDto> readAllSkillByRecruitment(int id) throws Exception;

	/**
	 * Read recruitment list by page number and entries number
	 * @param pageNo
	 * @param entriesNo
	 * @return
	 */
    RecruitmentListPageResponseDto readByCondition(Integer pageNo, Integer entriesNo);

	/**
	 * Get recruitment detail by id
	 * @param id
	 * @return
	 */
	RecruitmentDetailDto getRecruimentDetailById(Integer id);
}
