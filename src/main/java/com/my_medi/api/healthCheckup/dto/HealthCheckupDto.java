package com.my_medi.api.healthCheckup.dto;

import lombok.Data;

@Data
public class HealthCheckupDto {
    private String year;
    private String subscriberId;
    private String regionCode;
    private String gender;
    private String ageGroup5yr;
    private String height5cm;
    private String weight5kg;
    private String waistCm;
    private String visionLeft;
    private String visionRight;
    private String hearingLeft;
    private String hearingRight;
    private String systolicBp;
    private String diastolicBp;
    private String fastingBloodSugar;
    private String totalCholesterol;
    private String triglyceride;
    private String hdlCholesterol;
    private String ldlCholesterol;
    private String hemoglobin;
    private String urineProtein;
    private String serumCreatinine;
    private String ast;
    private String alt;
    private String gammaGtp;
    private String smokingStatus;
    private String drinkingStatus;
    private String oralExamYn;
    private String toothDecayYn;
    private String calculusYn;
}
