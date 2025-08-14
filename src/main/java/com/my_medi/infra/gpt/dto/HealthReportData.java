package com.my_medi.infra.gpt.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class HealthReportData {
    private LocalDate checkupDate;
    private Integer round;
    private MeasurementData measurement;
    private BloodPressureData bloodPressure;
    private BloodTestData bloodTest;
    private UrineTestData urineTest;
    private ImagingTestData imagingTest;

    @Data
    @Builder
    public static class MeasurementData {
        private Double height;
        private Double weight;
        private Double bmi;
        private Double waistCircumference;
        private String vision;
        private String hearing;
    }

    @Data
    @Builder
    public static class BloodPressureData {
        private Integer systolic;
        private Integer diastolic;
        private String status;
    }

    @Data
    @Builder
    public static class BloodTestData {
        private Double hemoglobin;
        private Double glucose;
        private Double totalCholesterol;
        private Double hdlCholesterol;
        private Double ldlCholesterol;
        private Double triglycerides;
        private Double creatinine;
        private Double egfr;
        private Integer ast;
        private Integer alt;
        private Integer gammaGtp;
    }

    @Data
    @Builder
    public static class UrineTestData {
        private String protein;
    }

    @Data
    @Builder
    public static class ImagingTestData {
        private String chestXray;
    }
}
