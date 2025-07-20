package com.my_medi.api.report.dto;

import com.my_medi.domain.report.entity.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

public class ReportResponseDto {
    @Data
    @Builder
    public static class UserReportDto {
        private Long id;
        private Long userId;

        private LocalDate checkupDate;
        private Integer round;

        private Measurement measurement;
        private BloodPressure bloodPressure;
        private BloodTest bloodTest;
        private UrineTest urineTest;
        private ImagingTest imagingTest;
        private Interview interview;
        private AdditionalTest additionalTest;
    }
}
