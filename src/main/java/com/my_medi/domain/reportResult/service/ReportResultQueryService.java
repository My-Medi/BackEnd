package com.my_medi.domain.reportResult.service;

import com.my_medi.domain.reportResult.entity.ReportResult;

public interface ReportResultQueryService {

    ReportResult getResultByReport(Long reportId);
}
