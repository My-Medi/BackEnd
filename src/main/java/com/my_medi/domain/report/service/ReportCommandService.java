package com.my_medi.domain.report.service;

import com.my_medi.api.report.dto.ReportRequestDto;

public interface ReportCommandService {

    //TODO write Report DTO 생성해서 argument로 추가하기
    Long writeHealthReport(Long userId, Integer round, ReportRequestDto reportRequestDto);

    //TODO edit Report DTO 생성해서 argument로 추가하기
    Long editHealthReportByRound(Long userId, Integer round, ReportRequestDto reportRequestDto);
}
