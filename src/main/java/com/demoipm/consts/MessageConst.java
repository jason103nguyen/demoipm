package com.demoipm.consts;

public class MessageConst {
    public static final String RESOURCE_NOT_FOUND = "Resource not found";
    public static final String INTERNAL_SERVER_ERROR = "Internal server error";

    /**
     * Create/update new user validation message
     */
    public static final String NAME_CANNOT_BE_BLANK = "Full name cannot be blank";
    public static final String NAME_EXCEED_LENGTH = "Full name max length exceed";
    public static final String INVALID_PHONE = "Invalid phone number";
    public static final String INVALID_EMAIL = "Invalid email address";
    public static final String USERNAME_CANNOT_BE_BLANK = "Username cannot be blank";
    public static final String USERNAME_EXCEED_LENGTH = "Username max length exceed";
    public static final String PASSWORD_LENGTH_INVALID = "Password length invalid";

    /**
     * General user manage message
     */
    public static final String USERNAME_NOT_EXISTED = "Username not existed";
    public static final String USERNAME_REQUIRED = "Username is required";

    /**
     * Create/update recruitment validation message
     */
    public static final String RECRUITMENT_CANNOT_BE_NULL = "Recruitment can not be empty";
    public static final String CAREER_CANNOT_BE_NULL = "Career can not be empty";
    public static final String JOB_CANNOT_BE_NULL = "Job can not be empty";
    public static final String QUANTITY_CANNOT_BE_NULL = "Quantity cannot be empty";
    public static final String QUANTITY_MIN_NOT_MEET = "Quantity must above 1";
    public static final String SALARY_CANNOT_BE_NULL = "Salary cannot be empty";
    public static final String MIN_SALARY_NOT_MEET = "Salary must above 100";
    public static final String SALARY_PAIR_INVALID = "Salary min must above max";
    public static final String RECRUITMENT_DATE_CANNOT_BE_NULL = "Recruitment date cannot be empty";
    public static final String RECRUITMENT_DATE_PRESENT_OR_FUTURE = "Recruitment date must in present or future";
    public static final String RECRUITMENT_DATE_PAIR_INVALID = "Recruitment start date must before end date";
}
