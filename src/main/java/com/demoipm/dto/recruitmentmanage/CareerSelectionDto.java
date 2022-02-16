package com.demoipm.dto.recruitmentmanage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CareerSelectionDto implements Serializable {

    private static final long serialVersionUID = -5783682178398889189L;
    private Integer id;
    private String career;

    public Integer getId() {
        return id;
    }

    public CareerSelectionDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCareer() {
        return career;
    }

    public CareerSelectionDto setCareer(String career) {
        this.career = career;
        return this;
    }
}
