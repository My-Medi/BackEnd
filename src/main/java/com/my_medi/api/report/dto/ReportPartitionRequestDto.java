package com.my_medi.api.report.dto;

import com.my_medi.domain.report.entity.B8Hepatitis;
import com.my_medi.domain.report.entity.ElderlyFunctionTest;
import com.my_medi.domain.report.enums.*;
import com.my_medi.domain.report.enums.additionalTest.BoneDensityStatus;
import com.my_medi.domain.report.enums.additionalTest.CognitiveImpairmentStatus;
import com.my_medi.domain.report.enums.additionalTest.DepressionStatus;
import com.my_medi.domain.report.enums.additionalTest.ElderlyPhysicalFunctionStatus;
import com.my_medi.domain.report.enums.bloodTest.*;
import com.my_medi.domain.report.enums.interview.LifestyleHabitsStatus;
import com.my_medi.domain.report.enums.interview.PositiveNegativeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReportPartitionRequestDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdditionalTestDto {
        private B8Hepatitis b8Hepatitis;

        private DepressionStatus depression;

        private CognitiveImpairmentStatus cognitiveImpairment;

        private BoneDensityStatus boneDensityStatus;

        private ElderlyPhysicalFunctionStatus elderlyPhysicalFunctionStatus;

        private ElderlyFunctionTest elderlyFunctionTest;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BloodPressureDto {
        private Integer systolic;
        private Integer diastolic;
        private BloodPressureStatus bloodPressureStatus;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BloodTestDto {
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

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImagingTestDto {
        private ImagingTestStatus imagingTestStatus;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InterviewDto {
        private PositiveNegativeStatus hasPastDisease;

        private PositiveNegativeStatus onMedication;

        private LifestyleHabitsStatus lifestyleHabitsStatus;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MeasurementDto {
        private Double height;
        private Double weight;
        private Double bmi;
        private BmiCategory bmiCategory;
        private Double waist;
        private WaistType waistType;
        private String vision;
        private HearingStatus hearingLeft;
        private HearingStatus hearingRight;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UrineTestDto {
        private UrineTestStatus urineTestStatus;
    }
}
