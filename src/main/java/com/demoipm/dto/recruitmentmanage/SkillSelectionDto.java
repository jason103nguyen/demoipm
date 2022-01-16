package com.demoipm.dto.recruitmentmanage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkillSelectionDto implements Serializable {

    private static final long serialVersionUID = 2148369013043295104L;
    private Integer id;
    private String skill;

    public Integer getId() {
        return id;
    }

    public SkillSelectionDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSkill() {
        return skill;
    }

    public SkillSelectionDto setSkill(String skill) {
        this.skill = skill;
        return this;
    }
}
