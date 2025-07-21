package com.my_medi.domain.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportCommandServiceImpl implements ReportCommandService{
    @Override
    public Long writeHealthReport(Long userId) {
        return null;
    }

    @Override
    public Long editHealthReportByRound(Long userId, Integer round) {
        return null;
    }
}
