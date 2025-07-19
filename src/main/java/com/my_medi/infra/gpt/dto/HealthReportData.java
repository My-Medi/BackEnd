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
    private InterviewData interview;
    private AdditionalTestData additionalTest;

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
        private String glucose;
    }

    @Data
    @Builder
    public static class ImagingTestData {
        private String chestXray;
        private String pastMedicalHistory;
        private String currentMedication;
    }

    @Data
    @Builder
    public static class InterviewData {
        private String smoking;
        private String drinking;
        private String exercise;
        private String familyHistory;
    }

    @Data
    @Builder
    public static class AdditionalTestData {
        private String hepatitisB;
        private String depression;
        private String cognitiveFunction;
        private String osteoporosis;
        private String additionalNotes;
    }
}
