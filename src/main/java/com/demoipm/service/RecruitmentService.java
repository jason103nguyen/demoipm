package com.demoipm.service;

import java.util.List;

import com.demoipm.dto.RecruitmentDto;
import com.demoipm.dto.SkillDto;
import com.demoipm.dto.general.DatatableParamRequestDTO;
import com.demoipm.dto.general.DatatableResponseDTO;
import com.demoipm.dto.recruitmentmanage.RecruitmentCreateRequestDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentDetailDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentSaveResponseDto;
import com.demoipm.dto.recruitmentmanage.RecruitmentUpdateRequestDto;

public interface RecruitmentService {

	void create(RecruitmentDto recruitmentDto);
	
	RecruitmentDto readById(int id) throws Exception;
	
	List<RecruitmentDto> readAll() throws Exception ;
	
	void update(RecruitmentDto recruitmentDto);
	
	void deleteById(int id);
	
	public List<SkillDto> readAllSkillByRecruitment(int id) throws Exception;

	/**
	 * Read recruitment list by datatable param
	 * @param request
	 * @return
	 */
    DatatableResponseDTO readByCondition(DatatableParamRequestDTO request);

	/**
	 * Get recruitment detail by id
	 * @param id
	 * @return
	 */
	RecruitmentDetailDto getRecruimentDetailById(Integer id);

	/**
	 * Save recruitment to db
	 * @param requestDto
	 * @return
	 */
    RecruitmentSaveResponseDto createRecruitment(RecruitmentCreateRequestDto requestDto);

	/**
	 * Get recruitment update info by id
	 * @param id
	 * @return
	 */
	RecruitmentUpdateRequestDto getRecruitmentUpdateInfo(Integer id);

	/**
	 * Update recruitment by request
	 * @param requestDto
	 * @return
	 */
	RecruitmentSaveResponseDto updateRecruitment(RecruitmentUpdateRequestDto requestDto);
}
