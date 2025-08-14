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
        private HealthStatus bmiHealthStatus;
        private Double bmi;
        private HealthStatus waistHealthStatus;
        private WaistType waistType;
    }

    @Getter
    @Builder
    public static class HypertensionDto {
        private HealthStatus systolicHealthStatus;
        private Integer systolic;
        private HealthStatus diastolicHealthStatus;
        private Integer diastolic;
    }

    @Getter
    @Builder
    public static class DiabetesDto {
        private HealthStatus fastingGlucoseHealthStatus;
        private Integer fastingGlucose;
    }

    @Getter
    @Builder
    public static class KidneyDto {
        private HealthStatus creatinineHealthStatus;
        private Double creatinine;
        private HealthStatus egfrHealthStatus;
        private Integer egfr;
    }

    @Getter
    @Builder
    public static class LiverDto {
        private HealthStatus astHealthStatus;
        private Integer ast;
        private HealthStatus altHealthStatus;
        private Integer alt;
        private HealthStatus gtpHealthStatus;
        private Integer gtp;
    }

    @Getter
    @Builder
    public static class AnemiaDto {
        private HealthStatus hemoglobinHealthStatus;
        private Double hemoglobin;
    }

    @Getter
    @Builder
    public static class DyslipidemiaDto {
        private HealthStatus totalCholesterolHealthStatus;
        private Integer totalCholesterol;
        private HealthStatus HdlHealthStatus;
        private Integer hdl;
        private HealthStatus triglycerideHealthStatus;
        private Integer triglyceride;
        private HealthStatus ldlHealthStatus;
        private Integer ldl;
    }

    @Getter
    @Builder
    public static class UrineDto {
        private HealthStatus urineProteinHealthStatus;
        private UrineTestStatus urineTestStatus;
    }
}
