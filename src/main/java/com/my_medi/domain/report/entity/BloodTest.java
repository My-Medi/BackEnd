package com.my_medi.domain.report.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloodTest {
    private Double hemoglobin;
    private Boolean anemia;

    private Integer fastingGlucose;
    private Boolean diabetes;

    private Integer totalCholesterol;
    private Integer hdl;
    private Integer triglyceride;
    private Integer ldl;
    private Boolean hyperlipidemia;

    private Double creatinine; // ex: 0.9
    private Integer egfr;

    private Integer ast;
    private Integer alt;
    private Integer gtp;
}