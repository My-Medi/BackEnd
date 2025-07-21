package com.my_medi.api.report.dto;

import com.my_medi.domain.report.entity.BmiCategory;
import com.my_medi.domain.report.entity.WaistType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeasurementDto {
    private Integer height;
    private Integer weight;
    private Double bmi;
    private BmiCategory bmiCategory;
    private Double waist;
    private WaistType waistType;
    private String vision;
    private String hearingLeft;
    private String hearingRight;
}
