package com.my_medi.api.report.dto;

import com.my_medi.domain.report.enums.BloodPressureStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloodPressureDto {
    private Integer systolic;
    private Integer diastolic;
    private BloodPressureStatus bloodPressureStatus;
}
