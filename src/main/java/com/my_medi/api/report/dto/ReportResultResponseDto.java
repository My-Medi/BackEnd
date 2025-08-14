package com.my_medi.api.report.dto;

import com.my_medi.domain.healthCheckup.entity.HealthCheckup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ReportResultResponseDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserReportResultDto{
        private int totalScore;
        private HealthStatus healthStatus;
    }
}
