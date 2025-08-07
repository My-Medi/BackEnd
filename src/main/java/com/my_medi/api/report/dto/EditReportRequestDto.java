package com.my_medi.api.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditReportRequestDto {
    private String hospitalName;
    private LocalDate checkupDate;
    private MeasurementDto measurementDto;
    private BloodPressureDto bloodPressureDto;
    private BloodTestDto bloodTestDto;
    private UrineTestDto urineTestDto;
    private ImagingTestDto imagingTestDto;
    private InterviewDto interviewDto;
    private Boolean hasAdditionalTest;
    private AdditionalTestDto additionalTestDto;
}
