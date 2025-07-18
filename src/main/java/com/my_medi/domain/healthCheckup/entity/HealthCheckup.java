package com.my_medi.domain.healthCheckup.entity;

import com.my_medi.domain.model.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "health_checkups",
        uniqueConstraints = @UniqueConstraint(columnNames = {"subscriber_id", "year"}))
public class HealthCheckup extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "subscriber_id", nullable = false, length = 50)
    private String subscriberId;

    @Column(name = "region_code")
    private Integer regionCode;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "age_group_5yr")
    private Integer ageGroup5yr;

    @Column(name = "height_5cm")
    private Integer height5cm;

    @Column(name = "weight_5kg")
    private Integer weight5kg;

    @Column(name = "waist_cm")
    private Double waistCm;

    @Column(name = "vision_left")
    private Double visionLeft;

    @Column(name = "vision_right")
    private Double visionRight;

    @Column(name = "hearing_left")
    private Integer hearingLeft;

    @Column(name = "hearing_right")
    private Integer hearingRight;

    @Column(name = "systolic_bp")
    private Integer systolicBp;

    @Column(name = "diastolic_bp")
    private Integer diastolicBp;

    @Column(name = "fasting_blood_sugar")
    private Integer fastingBloodSugar;

    @Column(name = "total_cholesterol")
    private Integer totalCholesterol;

    @Column(name = "triglyceride")
    private Integer triglyceride;

    @Column(name = "hdl_cholesterol")
    private Integer hdlCholesterol;

    @Column(name = "ldl_cholesterol")
    private Integer ldlCholesterol;

    @Column(name = "hemoglobin")
    private Double hemoglobin;

    @Column(name = "urine_protein")
    private Integer urineProtein;

    @Column(name = "serum_creatinine")
    private Double serumCreatinine;

    @Column(name = "ast")
    private Integer ast;

    @Column(name = "alt")
    private Integer alt;

    @Column(name = "gamma_gtp")
    private Integer gammaGtp;

    @Column(name = "smoking_status")
    private Integer smokingStatus;

    @Column(name = "drinking_status")
    private Integer drinkingStatus;

    @Column(name = "oral_exam_yn")
    private Boolean oralExamYn;

    @Column(name = "tooth_decay_yn")
    private Boolean toothDecayYn;

    @Column(name = "calculus_yn")
    private Boolean calculusYn;

}
