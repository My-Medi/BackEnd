package com.my_medi.domain.report.service;

import com.my_medi.domain.report.entity.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportQueryServiceImpl implements ReportQueryService{
    @Override
    public Report getReportByRound(Long userId, Integer round) {
        return null;
    }
}
