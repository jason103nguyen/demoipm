package com.demoipm.dto.recruitmentmanage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobSelectionDto implements Serializable {

    private static final long serialVersionUID = 1003359096694510717L;
    private Integer id;
    private String job;

    public Integer getId() {
        return id;
    }

    public JobSelectionDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getJob() {
        return job;
    }

    public JobSelectionDto setJob(String job) {
        this.job = job;
        return this;
    }
}
