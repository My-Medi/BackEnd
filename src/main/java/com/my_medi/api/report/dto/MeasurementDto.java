package com.my_medi.api.report.dto;

import com.my_medi.domain.report.entity.BmiCategory;
import com.my_medi.domain.report.entity.WaistType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDto {
    private Double height;
    private Double weight;
    private Double bmi;
    private BmiCategory bmiCategory;
    private Double waist;
    private WaistType waistType;
    private String vision;
    private String hearingLeft;
    private String hearingRight;
}
