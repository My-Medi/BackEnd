package com.my_medi.domain.report.entity;

import jakarta.persistence.Embeddable;

@Embeddable
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

    private String creatinine; // ex: 0.9
    private Integer egfr;

    private String ast;
    private String alt;
    private String gtp;
}