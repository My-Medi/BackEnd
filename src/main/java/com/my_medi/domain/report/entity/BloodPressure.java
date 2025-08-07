package com.my_medi.domain.report.entity;

import com.my_medi.domain.report.enums.BloodPressureStatus;
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
public class BloodPressure {
    private Integer systolic;   // 수축기
    private Integer diastolic;  // 이완기
    @Enumerated(EnumType.STRING)
    private BloodPressureStatus bloodPressureStatus;
}
