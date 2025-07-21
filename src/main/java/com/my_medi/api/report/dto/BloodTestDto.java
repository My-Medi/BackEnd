package com.my_medi.api.report.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BloodTestDto {
    private String alt;
    private Boolean anemia;
    private String ast;
    private String creatinine;
    private Boolean diabetes;
    private Integer egfr;
    private Integer fastingGlucose;
    private String gtp;
    private Integer hdl;
    private Double hemoglobin;
    private Boolean hyperlipidemia;
    private Integer ldl;
    private Integer totalCholesterol;
    private Integer triglyceride;
}
