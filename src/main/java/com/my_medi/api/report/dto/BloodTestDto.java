package com.my_medi.api.report.dto;

import com.my_medi.domain.report.enums.bloodTest.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloodTestDto {
    private Double hemoglobin;
    private HemoglobinStatus hemoglobinStatus;

    private Integer fastingGlucose;
    private FastingGlucoseStatus fastingGlucoseType;

    private Integer totalCholesterol;
    private Integer hdl;
    private Integer triglyceride;
    private Integer ldl;
    private CholesterolStatus cholesterolStatus;

    private Double creatinine;
    private Integer egfr;
    private RenalFunctionStatus renalFunctionStatus;

    private Integer ast;
    private Integer alt;
    private Integer gtp;
    private LiverFunctionStatus liverFunctionStatus;
}
