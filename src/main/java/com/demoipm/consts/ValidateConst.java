package com.demoipm.consts;

public class ValidateConst {
    public static final String PHONE_REGEX = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
    public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String CMND_REGEX = "(21|0|1|2|3|4|5|6|7|8|9)([0-9]{8,10})\\b";
    public static final String FULLNAME_REGEX = "^[a-zA-Z]{2,}(?: [a-zA-Z]+){0,10}$";
}
