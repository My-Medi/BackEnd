package com.my_medi.api.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloodTestDto {
    private Integer alt;
    private Boolean anemia;
    private Integer ast;
    private Double creatinine;
    private Boolean diabetes;
    private Integer egfr;
    private Integer fastingGlucose;
    private Integer gtp;
    private Integer hdl;
    private Double hemoglobin;
    private Boolean hyperlipidemia;
    private Integer ldl;
    private Integer totalCholesterol;
    private Integer triglyceride;
}
