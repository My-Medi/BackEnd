package com.my_medi.domain.report.service;

import com.my_medi.api.healthCheckup.dto.ComparingHealthCheckupResponseDto;
import com.my_medi.api.report.dto.ReportResponseDto;
import com.my_medi.api.report.dto.ReportResponseDto.ReportResultDto;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.user.entity.User;

public interface ReportQueryService {
    Report getReportByRound(Long userId, Integer round);

    ReportResultDto compareReport(User user, Integer round);

    Report getLatestReportByUserId(Long userId);

    long getReportCountByUser(User user);
}
