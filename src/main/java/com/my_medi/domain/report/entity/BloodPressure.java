package com.my_medi.domain.report.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class BloodPressure {
    private Integer systolic;   // 수축기
    private Integer diastolic;  // 이완기
    private Boolean highRisk;   // 유질환자 여부
}
