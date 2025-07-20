package com.my_medi.domain.report.service;

import com.my_medi.domain.report.entity.Report;
import com.my_medi.domain.report.exception.ReportHandler;
import com.my_medi.domain.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportQueryServiceImpl implements ReportQueryService{
    private final ReportRepository reportRepository;

    @Override
    public Report getReportByRound(Long userId, Integer round) {
        return reportRepository.findByUserIdAndRound(userId, round)
                .orElseThrow(() -> ReportHandler.NOT_FOUND);
    }
}
