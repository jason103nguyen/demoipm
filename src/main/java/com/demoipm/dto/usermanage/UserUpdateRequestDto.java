package com.demoipm.dto.usermanage;

import com.demoipm.consts.MessageConst;
import com.demoipm.consts.ValidateConst;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserUpdateRequestDto {

    @NotBlank(message = MessageConst.NAME_CANNOT_BE_BLANK)
    @Length(max = 50, message = MessageConst.NAME_EXCEED_LENGTH)
    private String fullName;

    @Pattern(regexp = ValidateConst.PHONE_REGEX, message = MessageConst.INVALID_PHONE)
    private String phone;

    @Pattern(regexp = ValidateConst.EMAIL_REGEX, message = MessageConst.INVALID_EMAIL)
    private String email;

    private String username;

    private List<String> roles;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
