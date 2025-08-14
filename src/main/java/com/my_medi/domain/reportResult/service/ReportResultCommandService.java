package com.my_medi.domain.reportResult.service;

import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.reportResult.entity.ReportResult;

public interface ReportResultCommandService {

    Long calculateReportResult(Long reportId, String birthDate, Gender gender);

    void removeReportResult(Long reportId);
}
