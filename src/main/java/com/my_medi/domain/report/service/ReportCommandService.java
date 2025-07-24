package com.my_medi.domain.report.service;

import com.my_medi.api.report.dto.EditReportRequestDto;
import com.my_medi.api.report.dto.WriteReportRequestDto;

public interface ReportCommandService {
    Long writeHealthReport(WriteReportRequestDto writeReportRequestDto);

    Long editHealthReportByRound(EditReportRequestDto editReportRequestDto);
}
