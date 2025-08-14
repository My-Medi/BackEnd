package com.my_medi.domain.reportResult.service;

import com.my_medi.domain.member.entity.Gender;
import com.my_medi.domain.report.entity.Report;

public interface ReportResultCommandService {

    Long calculateReportResult(Report report, String birthDate, Gender gender);
}
