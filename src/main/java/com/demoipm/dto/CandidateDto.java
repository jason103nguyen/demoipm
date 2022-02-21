package com.demoipm.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.demoipm.consts.MessageConst;
import com.demoipm.consts.ValidateConst;
import com.demoipm.entities.Candidate;

public class CandidateDto {

	private int id;
	
	@NotBlank(message = MessageConst.ACTIVITY_CANNOT_BE_BLANK)
	private String activity;
	
	@Min(message = MessageConst.INVALID_EXPERIENCEYEAR_MESSAGE, value = 0)
	@NotNull(message = MessageConst.EXPERIENCEYEAR_CANNOT_BE_BLANK)
	private Integer experienceYear;
	
	@NotBlank(message = MessageConst.SKILL_CANNOT_BE_BLANK) 
	private String skill;
	
	@NotBlank(message = MessageConst.NAME_CANNOT_BE_BLANK)
    @Length(max = 50, message = MessageConst.NAME_EXCEED_LENGTH)
	private String fullName;
	
	@NotBlank(message = MessageConst.CMND_CANNOT_BE_BLANK) 
	private String cmnd;
	
	private Date dateCmnd;
	
	@Pattern(regexp = ValidateConst.PHONE_REGEX, message = MessageConst.INVALID_PHONE)
	private String phone;
	
	@Pattern(regexp = ValidateConst.EMAIL_REGEX, message = MessageConst.INVALID_EMAIL)
	private String email;
	
	private String info;
	
	@NotBlank(message = MessageConst.STATUS_CANNOT_BE_BLANK)
	private String status;
	
	@NotBlank(message = MessageConst.GENDER_CANNOT_BE_BLANK)
	private String sex;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = MessageConst.INVALID_DATE_MESSAGE)
	@NotNull(message = MessageConst.BIRTHDAY_CANNOT_BE_BLANK)
	private LocalDate birthDay;
	
	private List<InterviewDto> listInterview = new ArrayList<InterviewDto>();
	
	private List<EntryTestDto> listEntryTest = new ArrayList<EntryTestDto>();
	
	private List<SkillCandidateDto> listSkillCandidate = new ArrayList<SkillCandidateDto>();
	
	public CandidateDto() {}
	
	public CandidateDto(Candidate candidate) {
		super();
		this.id = candidate.getId();
		this.fullName = candidate.getFullName();
		cmnd = candidate.getCmnd();
		this.dateCmnd = candidate.getDateCmnd();
		this.phone = candidate.getPhone();
		this.email = candidate.getEmail();
		this.info = candidate.getInfo();
		this.status = candidate.getStatus();
		this.sex = candidate.getSex();
		this.birthDay = candidate.getBirthDay();
		this.skill = candidate.getSkill();
		this.experienceYear = candidate.getExperienceYear();
		this.activity = candidate.getActivity();
	}

	public List<SkillCandidateDto> getListSkillCandidate() {
		return listSkillCandidate;
	}

	public void setListSkillCandidate(List<SkillCandidateDto> listSkillCandidate) {
		this.listSkillCandidate = listSkillCandidate;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Integer getExperienceYear() {
		return experienceYear;
	}

	public void setExperienceYear(Integer experienceYear) {
		this.experienceYear = experienceYear;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public List<InterviewDto> getListInterview() {
		return listInterview;
	}

	public void setListInterview(List<InterviewDto> listInterview) {
		this.listInterview = listInterview;
	}

	public List<EntryTestDto> getListEntryTest() {
		return listEntryTest;
	}

	public void setListEntryTest(List<EntryTestDto> listEntryTest) {
		this.listEntryTest = listEntryTest;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public Date getDateCmnd() {
		return dateCmnd;
	}

	public void setDateCmnd(Date dateCmnd) {
		this.dateCmnd = dateCmnd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

}
