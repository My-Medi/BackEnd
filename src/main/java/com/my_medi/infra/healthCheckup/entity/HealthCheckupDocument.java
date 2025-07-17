package com.my_medi.infra.healthCheckup.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@Document(collection = "health_checkups")
@CompoundIndex(def = "{'subscriberId': 1, 'year': 1}", unique = true)
public class HealthCheckupDocument {

    @Id
    private String id;

    @Field("year")
    private int year;

    @Field("subscriberId")
    private String subscriberId;

    @Field("regionCode")
    private int regionCode;

    @Field("gender")
    private String gender;

    @Field("ageGroup5yr")
    private int ageGroup5yr;

    @Field("height5cm")
    private int height5cm;

    @Field("weight5kg")
    private int weight5kg;

    @Field("waistCm")
    private double waistCm;

    @Field("visionLeft")
    private double visionLeft;

    @Field("visionRight")
    private double visionRight;

    @Field("hearingLeft")
    private int hearingLeft;

    @Field("hearingRight")
    private int hearingRight;

    @Field("systolicBp")
    private int systolicBp;

    @Field("diastolicBp")
    private int diastolicBp;

    @Field("fastingBloodSugar")
    private int fastingBloodSugar;

    @Field("totalCholesterol")
    private int totalCholesterol;

    @Field("triglyceride")
    private int triglyceride;

    @Field("hdlCholesterol")
    private int hdlCholesterol;

    @Field("ldlCholesterol")
    private int ldlCholesterol;

    @Field("hemoglobin")
    private double hemoglobin;

    @Field("urineProtein")
    private int urineProtein;

    @Field("serumCreatinine")
    private double serumCreatinine;

    @Field("ast")
    private int ast;

    @Field("alt")
    private int alt;

    @Field("gammaGtp")
    private int gammaGtp;

    @Field("smokingStatus")
    private int smokingStatus;

    @Field("drinkingStatus")
    private int drinkingStatus;

    @Field("oralExamYn")
    private boolean oralExamYn;

    @Field("toothDecayYn")
    private boolean toothDecayYn;

    @Field("calculusYn")
    private boolean calculusYn;

}
