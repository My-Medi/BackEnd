package com.my_medi.domain.report.entity;

import com.my_medi.domain.report.enums.bloodTest.HearingStatus;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.my_medi.domain.report.enums.BmiCategory;
import com.my_medi.domain.report.enums.WaistType;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {
    private Double height; // cm
    private Double weight; // kg
    private Double bmi;
    @Enumerated(EnumType.STRING)
    private BmiCategory bmiCategory;
    private Double waist; // 허리둘레
    @Enumerated(EnumType.STRING)
    private WaistType waistType;
    private String vision;       // 예: 0.9/0.8
    @Enumerated(EnumType.STRING)
    private HearingStatus hearingLeft;
    @Enumerated(EnumType.STRING)
    private HearingStatus hearingRight;
}