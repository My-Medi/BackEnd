package com.my_medi.domain.report.service;

import com.my_medi.domain.user.entity.User;
import com.my_medi.api.report.dto.EditReportRequestDto;
import com.my_medi.api.report.dto.WriteReportRequestDto;

public interface ReportCommandService {
    Long writeHealthReport(User user, WriteReportRequestDto writeReportRequestDto);

    Long editHealthReportByRound(User user, EditReportRequestDto editReportRequestDto);
}
