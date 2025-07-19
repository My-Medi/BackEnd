package com.my_medi.domain.report.service;

public interface ReportCommandService {

    //TODO write Report DTO 생성해서 argument로 추가하기
    Long writeHealthReport(Long userId);

    //TODO edit Report DTO 생성해서 argument로 추가하기
    Long editHealthReportByRound(Long userId, Integer round);
}
