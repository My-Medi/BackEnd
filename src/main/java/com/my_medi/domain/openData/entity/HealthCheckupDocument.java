package com.my_medi.domain.openData.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "health_checkups")
public class HealthCheckupDocument {

    @Id
    private String id;

    private int year;
    private String subscriberId;
    private int regionCode;
    private String gender;
    private int ageGroup5yr;
    private int height5cm;
    private int weight5kg;
    private double waistCm;
    private double visionLeft;
    private double visionRight;
    private int hearingLeft;
    private int hearingRight;
    private int systolicBp;
    private int diastolicBp;
    private int fastingBloodSugar;
    private int totalCholesterol;
    private int triglyceride;
    private int hdlCholesterol;
    private int ldlCholesterol;
    private double hemoglobin;
    private int urineProtein;
    private double serumCreatinine;
    private int ast;
    private int alt;
    private int gammaGtp;
    private int smokingStatus;
    private int drinkingStatus;
    private boolean oralExamYn;
    private boolean toothDecayYn;
    private boolean calculusYn;

}
