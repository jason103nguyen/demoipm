package com.demoipm.service;

import com.demoipm.dto.SkillDto;
import com.demoipm.dto.recruitmentmanage.SkillSelectionDto;
import com.demoipm.entities.Skill;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SkillService {

    void create(SkillDto skillDto);

    SkillDto readById(int id) throws Exception;

    List<SkillDto> readAll() throws Exception;

    void update(SkillDto skillDto);

    void deleteById(int id);

    Optional<Skill> findByName(String name);

    Page<Skill> paginationSkills(int page, int size);

    List<SkillDto> findByNameSkillDto(String name) throws Exception;

    Page<Skill> findByNameSkill(String name, int page, int size) throws Exception;

    /**
     * Get all skill
     *
     * @return
     */
    List<SkillSelectionDto> getAllSkill(Integer jobId);
}
