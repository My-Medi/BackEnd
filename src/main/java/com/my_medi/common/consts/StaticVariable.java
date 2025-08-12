package com.my_medi.common.consts;

public class StaticVariable {
    public static final String USER = "type_user";
    public static final String EXPERT = "type_expert";
    public static final String SWAGGER_JWT = "JWT";
    public static final String SWAGGER_BEARER = "Bearer";
    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String GRANT_TYPE = "authorization_code";
    public static final String REISSUE_ENDPOINT = "/api/v1/tokens/reissue";
    public static final String HEALTH_CHECK_ENDPOINT = "/api/v1/test/health-check";
    public static final String CREATED_DATE = "createdDate";
    public static final String ADVICE_ID = "id";

    //JWT
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; // 30분
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24 * 7; // 7일

    //HealthReportData JSON 필드 키
    public static final String CHECKUP_DATE = "checkupDate";
    public static final String ROUND = "round";
    public static final String MEASUREMENT = "measurement";
    public static final String BLOOD_PRESSURE = "bloodPressure";
    public static final String BLOOD_TEST = "bloodTest";
    public static final String URINE_TEST = "urineTest";
    public static final String IMAGING_TEST = "imagingTest";
    public static final String INTERVIEW = "interview";
    public static final String ADDITIONAL_TEST = "additionalTest";
    //total cholesterol average
    public static final Integer TOTAL_CHOLESTEROL_20 = 172;
    public static final Integer TOTAL_CHOLESTEROL_30 = 189;
    public static final Integer TOTAL_CHOLESTEROL_40 = 202;
    public static final Integer TOTAL_CHOLESTEROL_50 = 218;
    public static final Integer TOTAL_CHOLESTEROL_60 = 236;

    //Triglyceride average
    public static final Integer TRIGLYCERIDE_20 = 92;
    public static final Integer TRIGLYCERIDE_30 = 158;
    public static final Integer TRIGLYCERIDE_40 = 142;
    public static final Integer TRIGLYCERIDE_50 = 162;
    public static final Integer TRIGLYCERIDE_60 = 180;

    //HDL average
    public static final Integer HDL_20 = 60;
    public static final Integer HDL_30 = 54;
    public static final Integer HDL_40 = 48;
    public static final Integer HDL_50 = 43;
    public static final Integer HDL_60 = 38;

    //LDL average
    public static final Integer LDL_20 = 98;
    public static final Integer LDL_30 = 115;
    public static final Integer LDL_40 = 132;
    public static final Integer LDL_50 = 143;
    public static final Integer LDL_60 = 155;

    public static final Double CREATININE_AVERAGE = 0.8;
    public static final Integer E_GFR_AVERAGE = 78;
}

