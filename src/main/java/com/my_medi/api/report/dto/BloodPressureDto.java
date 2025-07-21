package com.my_medi.api.report.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BloodPressureDto {
    private Integer systolic;
    private Integer diastolic;
    private Boolean highRisk;
}
