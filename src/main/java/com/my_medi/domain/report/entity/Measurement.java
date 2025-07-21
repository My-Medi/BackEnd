package com.my_medi.domain.report.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {
    private Integer height; // cm
    private Integer weight; // kg
    private Double bmi;
    @Enumerated(EnumType.STRING)
    private BmiCategory bmiCategory;
    private Double waist; // 허리둘레
    @Enumerated(EnumType.STRING)
    private WaistType waistType;
    private String vision;       // 예: 0.9/0.8
    private String hearingLeft;
    private String hearingRight;
}