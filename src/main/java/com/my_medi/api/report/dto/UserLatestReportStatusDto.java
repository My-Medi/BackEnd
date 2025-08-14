package com.my_medi.api.report.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLatestReportStatusDto {
    private final Long userId;
    private final Long reportId;
    private final HealthStatus totalHealthStatus;
}
