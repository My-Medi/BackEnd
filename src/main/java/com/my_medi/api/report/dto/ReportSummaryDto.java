package com.my_medi.api.report.dto;

import com.my_medi.domain.report.enums.BmiCategory;
import com.my_medi.domain.report.enums.UrineTestStatus;
import com.my_medi.domain.report.enums.WaistType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportSummaryDto {
    private Long reportId;
    private Long userId;
    private Integer round;
    private LocalDate checkupDate;

    private ObesityDto obesity;
    private HypertensionDto hypertension;
    private DiabetesDto diabetes;
    private KidneyDto kidney;
    private LiverDto liver;
    private AnemiaDto anemia;
    private DyslipidemiaDto dyslipidemia;
    private UrineDto urine;

    @Getter
    @Builder
    public static class ObesityDto {
        private Double bmi;
        private WaistType waistType;
    }

    @Getter
    @Builder
    public static class HypertensionDto {
        private Integer systolic;
        private Integer diastolic;
    }

    @Getter
    @Builder
    public static class DiabetesDto {
        private Integer fastingGlucose;
    }

    @Getter
    @Builder
    public static class KidneyDto {
        private Double creatinine;
        private Integer egfr;
    }

    @Getter
    @Builder
    public static class LiverDto {
        private Integer ast;
        private Integer alt;
        private Integer gtp;
    }

    @Getter
    @Builder
    public static class AnemiaDto {
        private Double hemoglobin;
    }

    @Getter
    @Builder
    public static class DyslipidemiaDto {
        private Integer totalCholesterol;
        private Integer hdl;
        private Integer triglyceride;
        private Integer ldl;
    }

    @Getter
    @Builder
    public static class UrineDto {
        private UrineTestStatus urineTestStatus;
    }
}
