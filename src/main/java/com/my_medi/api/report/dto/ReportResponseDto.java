package com.my_medi.api.report.dto;

import com.my_medi.api.report.dto.ReportPartitionRequestDto.*;
import com.my_medi.domain.member.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.my_medi.api.report.dto.AssessmentDto.*;

public class ReportResponseDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserReportDto {
        private Long id;
        private Long userId;

        private String hospitalName;
        private LocalDate checkupDate;
        private Integer round;

        private MeasurementDto measurementDto;
        private BloodPressureDto bloodPressureDto;
        private BloodTestDto bloodTestDto;
        private UrineTestDto urineTestDto;
        private ImagingTestDto imagingTestDto;
        private InterviewDto interviewDto;
        private boolean hasAdditionalTest;
        private AdditionalTestDto additionalTestDto;
    }

    @Data
    @Builder
    public static class ReportResultDto{
        private int totalDataSize;
        private int ageGroup10Yr;
        private String nickname;
        private Gender gender;

        private ObesityAssessmentDto obesityAssessmentDto;
        private HypertensionAssessmentDto hypertensionAssessmentDto;
        private AnemiaAssessmentDto anemiaAssessmentDto;
        private DiabetesAssessmentDto diabetesAssessmentDto;
        private DyslipidemiaAssessmentDto dyslipidemiaAssessmentDto;
        private KidneyDiseaseAssessmentDto kidneyDiseaseAssessmentDto;
        private LiverDiseaseAssessmentDto liverDiseaseAssessmentDto;
        private UrineProteinAssessmentDto urineProteinAssessmentDto;
    }
}
