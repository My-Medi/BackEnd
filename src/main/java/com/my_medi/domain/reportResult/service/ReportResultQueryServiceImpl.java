package com.my_medi.domain.reportResult.service;

import com.my_medi.domain.report.exception.ReportHandler;
import com.my_medi.domain.reportResult.entity.ReportResult;
import com.my_medi.domain.reportResult.repository.ReportResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportResultQueryServiceImpl implements ReportResultQueryService{

    private final ReportResultRepository reportResultRepository;
    @Override
    public ReportResult getResultByReport(Long reportId) {
        return reportResultRepository.findByReportId(reportId)
                .orElseThrow(() -> ReportHandler.NOT_FOUND);
    }
}
