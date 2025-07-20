package com.my_medi.api.report.mapper;

import com.my_medi.api.report.dto.ReportResponseDto.UserReportDto;
import com.my_medi.domain.report.entity.Report;

public class ReportConverter {
    public static UserReportDto toUserReportDto(Report report) {
        return UserReportDto.builder()
                .id(report.getId())
                .userId(report.getUser().getId())
                .checkupDate(report.getCheckupDate())
                .round(report.getRound())
                .measurement(report.getMeasurement())
                .bloodPressure(report.getBloodPressure())
                .bloodTest(report.getBloodTest())
                .urineTest(report.getUrineTest())
                .imagingTest(report.getImagingTest())
                .interview(report.getInterview())
                .additionalTest(report.getAdditionalTest())
                .build();
    }
}
