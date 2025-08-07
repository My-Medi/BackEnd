package com.my_medi.domain.report.service;

import com.my_medi.api.report.dto.ComparingReportResponseDto;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.user.entity.User;

public interface ReportQueryService {
    Report getReportByRound(Long userId, Integer round);

    ComparingReportResponseDto compareReport(User user, Integer round);

    Report getLatestReportByUserId(Long userId);

    long getReportCountByUser(User user);
}
