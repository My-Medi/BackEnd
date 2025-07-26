package com.my_medi.domain.report.service;

import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.user.entity.User;

import java.util.List;

public interface ReportQueryService {
    Report getReportByRound(Long userId, Integer round);

    Report compareReport(User user, Integer round);
}
