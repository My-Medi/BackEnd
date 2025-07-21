package com.my_medi.api.report.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReportRequestDto {
    private LocalDate checkupDate;
    private Integer round;
    private MeasurementDto measurementDto;
    private BloodPressureDto bloodPressureDto;
    private BloodTestDto bloodTestDto;
    private UrineTestDto urineTestDto;
    private ImagingTestDto imagingTestDto;
    private InterviewDto interviewDto;
    private AdditionalTestDto additionalTestDto;
}
