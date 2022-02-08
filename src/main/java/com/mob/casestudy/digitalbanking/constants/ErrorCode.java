package com.mob.casestudy.digitalbanking.constants;

public class ErrorCode {
    private ErrorCode(){
    }
    public static final String INVALID_CUSTOMER_ERROR_CODE="OTP-INI-FIE-001";
    public static final String INVALID_TEMPLATE_ID_ERROR_CODE="OTP-INI-FIE-002";
    public static final String REGISTRATION="REGISTRATION";
    public static final String LOGIN="LOGIN";
    public static final String FIELD_MISSING_ERROR_CODE="OTP-INI-FIE-003";
    public static final String CUSTOMER_FIELD_MISSING_ERROR_CODE ="CUS-CREATE-FIE-001";
    public static final String CUSTOMER_UPDATE_FIELD_MISSING_ERROR_CODE ="CUS-UPDATE-FIE-001";
    public static final String CUSTOMER_INVALID_PHONE_NUMBER_ERROR_CODE="CUS-CREATE-FIE-002";
    public static final String CUSTOMER_UPDATE_INVALID_PHONE_NUMBER_ERROR_CODE="CUS-UPDATE-FIE-002";
    public static final String CUSTOMER_UPDATE_INVALID_EMAIL_ERROR_CODE="CUS-UPDATE-FIE-003";
    public static final String CUSTOMER_UPDATE_INVALID_PREFERRED_LANGUAGE_ERROR_CODE="CUS-UPDATE-FIE-004";
    public static final String CUSTOMER_UPDATE_NOT_PRESENT_ERROR_CODE="CUS-UPDATE-NFD-001";
    public static final String CUSTOMER_INVALID_EMAIL_ERROR_CODE="CUS-CREATE-FIE-003";
    public static final String CUSTOMER_INVALID_LANGUAGE_ERROR_CODE="CUS-CREATE-FIE-004";
    public static final String CUSTOMER_INVALID_USERNAME_ERROR_CODE="CUS-CREATE-FIE-005";
    public static final String CUSTOMER_DUPLICATE_USERNAME_ERROR_CODE="CUS-CREATE-FIE-006";
    public static final String SECURITY_QUESTION_EMPTY_ERROR_CODE="SQE-GET-FIE-001";
    public static final String CUSTOMER_SECURITY_QUESTION_EMPTY_ERROR_CODE="CSQ-GET-FIE-001";
    public static final String CUSTOMER_SECURITY_QUESTION_CUSTOMER_NOT_FOUND__ERROR_CODE="CSQ-GET-FIE-002";
}
