package com.my_medi.domain.report.entity;

import com.my_medi.domain.report.enums.bloodTest.*;
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
public class BloodTest {
    private Double hemoglobin;
    @Enumerated(EnumType.STRING)
    private HemoglobinStatus hemoglobinStatus;
    private Integer fastingGlucose;
    @Enumerated(EnumType.STRING)
    private FastingGlucoseStatus fastingClucoseType;
    private Integer totalCholesterol;
    private Integer hdl;
    private Integer triglyceride;
    private Integer ldl;
    @Enumerated(EnumType.STRING)
    private CholesterolStatus cholesterolStatus;
    private Double creatinine;
    private Integer egfr;
    @Enumerated(EnumType.STRING)
    private RenalFunctionStatus renalFunctionStatus;
    private Integer ast;
    private Integer alt;
    private Integer gtp;
    @Enumerated(EnumType.STRING)
    private LiverFunctionStatus liverFunctionStatus;
}