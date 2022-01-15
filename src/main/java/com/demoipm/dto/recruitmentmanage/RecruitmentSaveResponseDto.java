package com.demoipm.dto.recruitmentmanage;

import com.demoipm.dto.general.ResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecruitmentSaveResponseDto extends ResponseDto implements Serializable {

    private static final long serialVersionUID = 5985417351824573333L;

    private List<FieldError> fieldErrors;

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public RecruitmentSaveResponseDto setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
        return this;
    }
}
