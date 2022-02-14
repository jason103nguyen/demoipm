package com.demoipm.dto.recruitmentmanage;

import com.demoipm.consts.MessageConst;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecruitmentCreateRequestDto implements Serializable {

    private static final long serialVersionUID = -1604724206696748681L;

    @NotNull(message = MessageConst.CAREER_CANNOT_BE_NULL)
    private Integer careerId;

    @NotNull(message = MessageConst.JOB_CANNOT_BE_NULL)
    private Integer jobId;

    @NotNull(message = MessageConst.QUANTITY_CANNOT_BE_NULL)
    @Min(value = 1, message = MessageConst.QUANTITY_MIN_NOT_MEET)
    private Integer quantity;

    @NotNull(message = MessageConst.SALARY_CANNOT_BE_NULL)
    @Min(value = 100, message = MessageConst.MIN_SALARY_NOT_MEET)
    private Double minSalary;

    @NotNull(message = MessageConst.SALARY_CANNOT_BE_NULL)
    @Min(value = 100, message = MessageConst.MIN_SALARY_NOT_MEET)
    private Double maxSalary;

    @NotNull(message = MessageConst.RECRUITMENT_DATE_CANNOT_BE_NULL)
    @FutureOrPresent(message = MessageConst.RECRUITMENT_DATE_PRESENT_OR_FUTURE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = MessageConst.RECRUITMENT_DATE_CANNOT_BE_NULL)
    @FutureOrPresent(message = MessageConst.RECRUITMENT_DATE_PRESENT_OR_FUTURE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private List<Integer> skillIds;

    public Integer getCareerId() {
        return careerId;
    }

    public void setCareerId(Integer careerId) {
        this.careerId = careerId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Integer> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Integer> skillIds) {
        this.skillIds = skillIds;
    }
}
