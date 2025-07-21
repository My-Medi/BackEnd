package com.my_medi.domain.report.service;

import com.my_medi.domain.report.entity.Report;

import java.util.List;

public interface ReportQueryService {
    Report getReportByRound(Long userId, Integer round);
}
